/**
 * 
 */
package cn.s3bit.th902.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Obsidianss
 * Drawing Lasers
 */
public class LaserLikeDrawing extends Actor {
	Mesh mesh = null;
	ShaderProgram shaderProgram = null;
	Texture texture = null;
	float[] initialVertices = null;
	float[] operatedVertices = null;
	List<Float> vertices = new ArrayList<>();
	public LaserLikeDrawing(Texture texture, int max_vertices) {
		this.texture = texture;
		List<VertexAttribute> list = new ArrayList<>();
		list.add(VertexAttribute.Position());
		list.add(VertexAttribute.ColorUnpacked());
		list.add(VertexAttribute.TexCoords(0));

		mesh = new Mesh(VertexDataType.VertexArray, false, max_vertices, max_vertices, list.toArray(new VertexAttribute[list.size()]));
	}
	
	private void _addVertToList(List<Float> list, Vector2 vert, float texX, float texY) {
		list.addAll(Arrays.asList(vert.x, vert.y, 0f, 1f, 1f, 1f, 1f, texX, texY));
	}
	
	@Override
	public float getWidth() {
		_applyTransform(1f);
		return mesh.calculateBoundingBox().getWidth();
	}
	
	@Override
	public float getHeight() {
		_applyTransform(1f);
		return mesh.calculateBoundingBox().getHeight();
	}
	
	Matrix4 mat4_trans = new Matrix4();
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		shaderProgram = batch.getShader();
		texture.bind();
		_applyTransform(parentAlpha);
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		mesh.render(shaderProgram, GL20.GL_TRIANGLES);
	}
	private List<Vector2> mPoints;
	private List<Float> mTexturePos;
	
	public void setLaserPoints(List<Vector2> points, List<Float> texturePos) {
		if (points.size() != texturePos.size()) {
			throw new IllegalArgumentException("Size of points and texturePos must be the same!");
		}
		mPoints = new ArrayList<>(points);
		mTexturePos = new ArrayList<>(texturePos);
		vertices.clear();
		for (int i = 0; i < points.size() - 1; i++)
			_appendSegment(
				points.get(i),
				points.get(i + 1),
				texturePos.get(i),
				texturePos.get(i + 1)
			);
		
		if (initialVertices == null || initialVertices.length != vertices.size()) {
			initialVertices = new float[vertices.size()];
			operatedVertices = new float[vertices.size()];
			short[] indices = new short[initialVertices.length / 9];
			for (short i = 0; i < indices.length; i++) indices[i] = i;
			mesh.setIndices(indices);
		}
		
		for (int i = 0; i < initialVertices.length; i++) {
			initialVertices[i] = vertices.get(i);
		}
	}
	
	private void _applyTransform(final float parentAlpha) {
		System.arraycopy(initialVertices, 0, operatedVertices, 0, initialVertices.length);
		for (int i=6; i<initialVertices.length; i+=9)
			operatedVertices[i] = parentAlpha * getColor().a;
		mesh.setVertices(operatedVertices);
		mat4_trans.setToRotation(0, 0, 1, getRotation());
		mesh.transform(mat4_trans);
		mat4_trans.setToTranslationAndScaling(getX(), getY(), 0, 1, 1, 1);
		mesh.transform(mat4_trans);
		
		if (getScaleY() != mOldScaleY)
			resetVerts();
	}
	
	private float mOldScaleY;
	
	public void resetVerts() {
		setLaserPoints(mPoints, mTexturePos);
	}
	
	static Vector2 vct2_tmp1 = new Vector2();
	static Vector2 vct2_tmp2 = new Vector2();
	private void _appendSegment(Vector2 start, Vector2 end, float textureStart, float textureEnd) {
		vct2_tmp2.set(end).sub(start).rotate90(1).nor().scl(texture.getHeight() / 2f);
		vct2_tmp1.set(start).sub(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureStart, 0f);
		vct2_tmp1.set(end).sub(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureEnd, 0f);
		vct2_tmp1.set(start).add(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureStart, 1f);
		
		vct2_tmp1.set(end).sub(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureEnd, 0f);
		vct2_tmp1.set(end).add(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureEnd, 1f);
		vct2_tmp1.set(start).add(vct2_tmp2);
		_addVertToList(vertices, vct2_tmp1, textureStart, 1f);
		
		mOldScaleY = getScaleY();
	}

}
