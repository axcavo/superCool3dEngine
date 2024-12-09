package superCool3dEngine;

import org.lwjgl.opengl.GL30;

import superCool3dEngine.shaders.Shader;

public class Triangle implements Renderable {
	private int vao, vbo;
	private Shader shader;
	
	public Triangle(Shader shader) {
        this.shader = shader;
        setupMesh();
    }
	
	private void setupMesh() {
		float[] vertices = { 
				-0.5f, -0.5f, 0.0f, // Bottom left
				0.5f, -0.5f, 0.0f, // Bottom right
				0.0f, 0.5f, 0.0f // Top
		};
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
		
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertices, GL30.GL_STATIC_DRAW);
		GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 3 * Float.BYTES, 0);
		GL30.glEnableVertexAttribArray(0);
		
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void render() {
		shader.use();
		GL30.glBindVertexArray(vao);
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 3);
		GL30.glBindVertexArray(0);
	}
}
