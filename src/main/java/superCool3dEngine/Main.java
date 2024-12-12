package superCool3dEngine;

import org.lwjgl.opengl.GL11;

import superCool3dEngine.Transformable.Axis;
import superCool3dEngine.shaders.ShaderTemp;

public class Main {

	public static void main(String[] args) {
		Window window = new Window.Builder(new WindowTest()).setResizable(true).build();
		window.run();
	}

}

class WindowTest implements Renderable {
	float angle = 0;
	float red = 0.0f;
	float green = 0.0f;
	float blue = 0.0f;

	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		float[] verticesFrontFace = {
				// Front face
				-0.5f, -0.5f, 0.5f, // Bottom left
				0.5f, -0.5f, 0.5f, // Bottom right
				-0.5f, 0.5f, 0.5f, // Top left
				0.5f, -0.5f, 0.5f, // Bottom right
				0.5f, 0.5f, 0.5f, // Top right
				-0.5f, 0.5f, 0.5f, // Top left
		};
		float[] verticesBackFace = {
				// Back face
				-0.5f, -0.5f, -0.5f, // Bottom left
				-0.5f, 0.5f, -0.5f, // Top left
				0.5f, -0.5f, -0.5f, // Bottom right
				0.5f, -0.5f, -0.5f, // Bottom right
				-0.5f, 0.5f, -0.5f, // Top left
				0.5f, 0.5f, -0.5f, // Top right
		};

		float[] verticesLeftFace = {
				// Left face
				-0.5f, -0.5f, -0.5f, // Bottom left
				-0.5f, -0.5f, 0.5f, // Bottom right
				-0.5f, 0.5f, -0.5f, // Top left
				-0.5f, -0.5f, 0.5f, // Bottom right
				-0.5f, 0.5f, 0.5f, // Top right
				-0.5f, 0.5f, -0.5f, // Top left
		};
		
		float[] verticesRightFace = {
				// Right face
				0.5f, -0.5f, -0.5f, // Bottom left
				0.5f, 0.5f, -0.5f, // Top left
				0.5f, -0.5f, 0.5f, // Bottom right
				0.5f, -0.5f, 0.5f, // Bottom right
				0.5f, 0.5f, -0.5f, // Top left
				0.5f, 0.5f, 0.5f, // Top right
		};
		float[] verticesTopFace = {
				// Top face
				-0.5f, 0.5f, -0.5f, // Bottom left
				-0.5f, 0.5f, 0.5f, // Bottom right
				0.5f, 0.5f, -0.5f, // Top left
				-0.5f, 0.5f, 0.5f, // Bottom right
				0.5f, 0.5f, 0.5f, // Top right
				0.5f, 0.5f, -0.5f, // Top left
		};
		float[] verticesBottomFace = {
				// Bottom face
			    -0.5f, -0.5f, -0.5f, // Bottom left
			     0.5f, -0.5f, -0.5f, // Bottom right
			    -0.5f, -0.5f,  0.5f, // Top left
			     0.5f, -0.5f, -0.5f, // Bottom right
			     0.5f, -0.5f,  0.5f, // Top right
			    -0.5f, -0.5f,  0.5f  // Top left
		};
		// Define color for each face
		Color frontFaceColor = new Color(new float[] { 0.96f, 0.156f, 0.568f, 0.8f });
		Color backFaceColor = new Color(new float[] { 0.2f, 0.8f, 0.1f, 0.8f });
		Color leftFaceColor = new Color(new float[] { 0.2f, 0.5f, 0.8f, 0.8f });
		Color rightFaceColor = new Color(new float[] { 0.8f, 0.8f, 0.2f, 0.8f });
		Color topFaceColor = new Color(new float[] { 0.8f, 0.2f, 0.8f, 0.8f });
		Color bottomFaceColor = new Color(new float[] { 0.2f, 0.8f, 0.8f, 0.8f });

		// Create figures for each face
		Figure frontFaceFigure = new Figure(verticesFrontFace, frontFaceColor);
		Figure backFaceFigure = new Figure(verticesBackFace, backFaceColor);
		Figure leftFaceFigure = new Figure(verticesLeftFace, leftFaceColor);
		Figure rightFaceFigure = new Figure(verticesRightFace, rightFaceColor);
		Figure topFaceFigure = new Figure(verticesTopFace, topFaceColor);
		Figure bottomFaceFigure = new Figure(verticesBottomFace, bottomFaceColor);

		// Apply transformations
		frontFaceFigure.rotate(Axis.X, angle);
		frontFaceFigure.rotate(Axis.Y, angle);
		frontFaceFigure.rotate(Axis.Z, angle);

		backFaceFigure.rotate(Axis.X, angle);
		backFaceFigure.rotate(Axis.Y, angle);
		backFaceFigure.rotate(Axis.Z, angle);

		leftFaceFigure.rotate(Axis.X, angle);
		leftFaceFigure.rotate(Axis.Y, angle);
		leftFaceFigure.rotate(Axis.Z, angle);

		rightFaceFigure.rotate(Axis.X, angle);
		rightFaceFigure.rotate(Axis.Y, angle);
		rightFaceFigure.rotate(Axis.Z, angle);

		topFaceFigure.rotate(Axis.X, angle);
		topFaceFigure.rotate(Axis.Y, angle);
		topFaceFigure.rotate(Axis.Z, angle);

		bottomFaceFigure.rotate(Axis.X, angle);
		bottomFaceFigure.rotate(Axis.Y, angle);
		bottomFaceFigure.rotate(Axis.Z, angle);

		// Render each face of the cube
		frontFaceFigure.render();
		backFaceFigure.render();
		leftFaceFigure.render();
		rightFaceFigure.render();
		topFaceFigure.render();
		bottomFaceFigure.render();


		angle += 0.005;
	}

}
