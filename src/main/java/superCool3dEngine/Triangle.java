package superCool3dEngine;

import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;

import superCool3dEngine.shaders.Shader;

public class Triangle implements Renderable, Transformable {
	private int vao, vbo;
	private Shader shader;
	
	private Matrix4f modelMatrix;
    
    private float[] vertices;
	
	public Triangle(Shader shader, float[] vertices) {
        this.shader = shader;
        this.vertices = vertices;
        setupMesh();
        
        modelMatrix = new Matrix4f();
        
        //modelMatrix.translate(0.0f, 0.0f, 0.0f);
       // modelMatrix.rotateX((float) Math.toRadians(100.0f));
        
    }
	
	private void setupMesh() {		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
		
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertices, GL30.GL_STATIC_DRAW);
		GL30.glUniformMatrix4fv(vao, false, vertices);
		GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 3 * Float.BYTES, 0);
		GL30.glEnableVertexAttribArray(0);
		
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void render() {
		shader.use();
		
		int modelLoc = GL30.glGetUniformLocation(shader.getProgramId(), "model");
        GL30.glUniformMatrix4fv(modelLoc, false, modelMatrix.get(new float[16]));        
		GL30.glBindVertexArray(vao);
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 3);
		GL30.glBindVertexArray(0);
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
