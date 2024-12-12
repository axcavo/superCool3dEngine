package superCool3dEngine.shaders;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;

import superCool3dEngine.Color;
import superCool3dEngine.Transformable;

public class TransformableShader extends Shader implements Transformable {
	private final Matrix4f modelMatrix;
	private final Color color;

	public TransformableShader(String vertexPath, String fragmentPath, Color color) {
		super(vertexPath, fragmentPath);
		modelMatrix = new Matrix4f();
		this.color = color;
	}
	
	public void setUniformColor() {
		int redLoc = GL30.glGetUniformLocation(getProgramId(), "red");
        int greenLoc = GL30.glGetUniformLocation(getProgramId(), "green");
        int blueLoc = GL30.glGetUniformLocation(getProgramId(), "blue");
        int alphaLoc = GL30.glGetUniformLocation(getProgramId(), "alpha");
		
		GL30.glUniform1f(redLoc, color.getRed());
		GL30.glUniform1f(greenLoc, color.getGreen());
		GL30.glUniform1f(blueLoc, color.getBlue());
		GL30.glUniform1f(alphaLoc, color.getAlpha());
	}
	
	public Matrix4f getModelMatrix() {
		return modelMatrix;
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
