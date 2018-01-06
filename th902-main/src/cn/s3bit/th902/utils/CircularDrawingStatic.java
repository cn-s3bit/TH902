package cn.s3bit.th902.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.ResourceManager;

public class CircularDrawingStatic extends Actor {
	static Mesh mesh = null;
	static ShaderProgram shaderProgram = null;
	public CircularDrawingStatic() {
	}
	public static void testRender() {
		if (mesh == null) {
			List<VertexAttribute> list = new ArrayList<>();
			final int n_vtx = 6;
			list.add(VertexAttribute.Position());
			list.add(VertexAttribute.ColorUnpacked());
			list.add(VertexAttribute.TexCoords(0));
			mesh = new Mesh(VertexDataType.VertexArray, true, n_vtx, n_vtx, list.toArray(new VertexAttribute[list.size()]));
			shaderProgram = SpriteBatch.createDefaultShader();
		}
		shaderProgram.begin();
		shaderProgram.setUniformMatrix("u_projTrans", GameMain.instance.activeStage.getCamera().projection);
		ResourceManager.barrages.get(245).bind();
		mesh.setVertices(new float[] {
				0, 0, 0, 1, 1, 1, 1, 0.5f, 0.5f,
				-100f, 100f, 0, 1, 1, 1, 1, 0, 1,
				100f, 100f, 0, 1, 1, 1, 1, 1, 1,
				100f, -100f, 0, 1, 1, 1, 1, 1, 0,
				-100f, -100f, 0, 1, 1, 1, 1, 0, 0,
				-100f, -60f, 0, 1, 1, 1, 1, 0f, 0.2f
				//-100f, 100f, 0, 1, 1, 1, 1, 0, 1
		});
		mesh.setIndices(new short[] {
				0, 1, 2, 3, 4, 5
		});
		mesh.render(shaderProgram, GL20.GL_TRIANGLE_FAN);
		shaderProgram.end();
	}
}
