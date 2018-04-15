package cn.s3bit.th902.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CircularDrawingStatic extends Actor {
	Mesh mesh = null;
	ShaderProgram shaderProgram = null;
	Texture texture = null;
	float[] initialVertices = null;
	float[] operatedVertices = null;
	public CircularDrawingStatic(Texture texture, int beginDegrees, int endDegrees) {
		this.texture = texture;
		List<VertexAttribute> list = new ArrayList<>();
		list.add(VertexAttribute.Position());
		list.add(VertexAttribute.ColorUnpacked());
		list.add(VertexAttribute.TexCoords(0));
		
		List<Float> lvert = new ArrayList<>(30);
		int n_vtx = 0;
		lvert.addAll(Arrays.asList(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0.5f, 0.5f));
		n_vtx++;
		Vector2 vert = new Vector2();
		calcAnglePoint(beginDegrees, vert);
		addVertToList(lvert, vert);
		n_vtx++;
		for (int deg = (int)(Math.ceil(beginDegrees / 45.0) * 45); deg < endDegrees / 45 * 45; deg++) {
			n_vtx++;
			calcAnglePoint(deg, vert);
			addVertToList(lvert, vert);
		}
		calcAnglePoint(endDegrees, vert);
		addVertToList(lvert, vert);
		n_vtx++;
		mesh = new Mesh(VertexDataType.VertexArray, false, n_vtx, n_vtx, list.toArray(new VertexAttribute[list.size()]));
		short[] indices = new short[n_vtx];
		for (short i = 0; i < n_vtx; i++) indices[i] = i;
		mesh.setIndices(indices);
		initialVertices = new float[lvert.size()];
		operatedVertices = new float[lvert.size()];
		for (int i = 0; i < initialVertices.length; i++) {
			initialVertices[i] = lvert.get(i);
		}
	}
	
	public void addVertToList(List<Float> list, Vector2 vert) {
		list.addAll(Arrays.asList(vert.x, vert.y, 0f, 1f, 1f, 1f, 1f, (vert.x + 1f) / 2f, (vert.y + 1f) / 2f));
	}
	
	@Override
	public float getWidth() {
		return texture.getWidth() * getScaleX();
	}
	
	@Override
	public float getHeight() {
		return texture.getHeight() * getScaleY();
	}
	
	public void calcAnglePoint(int degrees, Vector2 out) {
		degrees = degrees % 360;
		degrees = degrees < 0 ? degrees + 360 : degrees;
		if (degrees >= 45 && degrees < 135) {
			out.y = 1f;
			out.x = out.y / (float) Math.tan(degrees * MathUtils.degreesToRadians);
		} else if (degrees >= 135 && degrees < 225) {
			out.x = -1f;
			out.y = out.x * (float) Math.tan(degrees * MathUtils.degreesToRadians);
		} else if (degrees >= 225 && degrees < 315) {
			out.y = -1f;
			out.x = out.y / (float) Math.tan(degrees * MathUtils.degreesToRadians);
		} else {
			out.x = 1f;
			out.y = out.x * (float) Math.tan(degrees * MathUtils.degreesToRadians);
		}
	}
	
	Matrix4 mat4_trans = new Matrix4();
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		shaderProgram = batch.getShader();
		texture.bind();
		System.arraycopy(initialVertices, 0, operatedVertices, 0, initialVertices.length);
		for (int i=6; i<initialVertices.length; i+=9)
			operatedVertices[i] = parentAlpha * getColor().a;
		mesh.setVertices(operatedVertices);
		mat4_trans.setToRotation(0, 0, 1, getRotation());
		mesh.transform(mat4_trans);
		mat4_trans.setToTranslationAndScaling(getX(), getY(), 0, getWidth() / 2f, -getHeight() / 2f, 1);
		mesh.transform(mat4_trans);
		mesh.render(shaderProgram, GL20.GL_TRIANGLE_FAN);
	}

}
