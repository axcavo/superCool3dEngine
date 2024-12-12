package superCool3dEngine;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;

import superCool3dEngine.shaders.ShaderTemp;

public class Triangle implements Renderable, Transformable {
	private int vao, vbo;
	private ShaderTemp shader;
	
	private Matrix4f modelMatrix;
    
    private float[] vertices;
	
	public Triangle(float[] vertices) {
        this.vertices = vertices;
        setupMesh();
        
        shader = new ShaderTemp("./src/main/java/superCool3dEngine/shaders/vertex/transformation.vert",
				"./src/main/java/superCool3dEngine/shaders/fragment/basic.frag");
        modelMatrix = new Matrix4f();
    }
	
	private void setupMesh() {		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
		
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertices, GL30.GL_STATIC_DRAW);
		//GL30.glUniformMatrix4fv(vao, false, vertices);
		GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 3 * Float.BYTES, 0);
		GL30.glEnableVertexAttribArray(0);
		
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	private void setUniformModel() {
		int modelLoc = GL30.glGetUniformLocation(shader.getProgramId(), "model");
        GL30.glUniformMatrix4fv(modelLoc, false, modelMatrix.get(new float[16]));
	}
	
	@Override
	public void render() {
		shader.use();
		setUniformModel();
		shader.setUniformColor();
		
		GL30.glBindVertexArray(vao);
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 3);
		GL30.glBindVertexArray(0);
	}
	
	public ShaderTemp getShader() {
		return shader;
	}

	@Override
	public void translate(float x, float y, float z) {
		modelMatrix.translate(x, y, z);
	}

	@Override
	public void rotate(Axis axis, float angle) {
		if (axis == Axis.X) {
			modelMatrix.rotateX(angle);
		} else if (axis == Axis.Y) {
			modelMatrix.rotateY(angle);
		} else if (axis == Axis.Z) {
			modelMatrix.rotateZ(angle);
		}
	}
}
