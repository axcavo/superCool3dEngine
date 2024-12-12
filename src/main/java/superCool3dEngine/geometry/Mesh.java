package superCool3dEngine.geometry;

import org.lwjgl.opengl.GL30;

public class Mesh {
	private int vao, vbo;
	private float[] vertices;
	
	public Mesh(float[] vertices) {
		super();
		this.vertices = vertices;
		setupMesh();
	}
	
	private void setupMesh() {
		bindVertexArray();
		bindBuffer();
		
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertices, GL30.GL_STATIC_DRAW);
		GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 3 * Float.BYTES, 0);
		GL30.glEnableVertexAttribArray(0);
		
		cleanBindings();
	}
	
	private void bindVertexArray() {
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
	}
	
	private void bindBuffer() {
		vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
	}
	
	private void cleanBindings() {
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	public float[] getVertices() {
		return vertices;
	}
	
	public int getVao() {
		return vao;
	}
}
