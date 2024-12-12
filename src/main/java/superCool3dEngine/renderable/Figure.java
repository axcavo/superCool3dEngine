package superCool3dEngine.renderable;

import org.lwjgl.opengl.GL30;

import superCool3dEngine.Color;
import superCool3dEngine.Transformable;
import superCool3dEngine.Transformable.Axis;
import superCool3dEngine.geometry.Mesh;
import superCool3dEngine.shaders.TransformableShader;

public class Figure implements Renderable, Transformable{
	private static final String VERTEX_PATH = "./src/main/java/superCool3dEngine/shaders/vertex/transformation.vert";
	private static final String FRAGMENT_PATH = "./src/main/java/superCool3dEngine/shaders/fragment/basic.frag";
	private Mesh mesh;
	private TransformableShader shader;
	
	public Figure(float[] vertices, Color color) {
		super();
		assert vertices.length % 3 == 0;
		
		mesh = new Mesh(vertices);
		shader = new TransformableShader(VERTEX_PATH, FRAGMENT_PATH, color);
	}
	
	private void setUniformModel() {
		int modelLoc = GL30.glGetUniformLocation(shader.getProgramId(), "model");
        GL30.glUniformMatrix4fv(modelLoc, false, shader.getModelMatrix().get(new float[16]));
	}

	@Override
	public void translate(float x, float y, float z) {
		shader.translate(x, y, z);
		
	}

	@Override
	public void rotate(Axis axis, float angle) {
		shader.rotate(axis, angle);
		
	}

	@Override
	public void render() {
		shader.use();
		shader.setUniformColor();
		setUniformModel();
		
		GL30.glBindVertexArray(mesh.getVao());
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, mesh.getVertices().length);
		GL30.glBindVertexArray(0);
	}

}
