package superCool3dEngine;

import org.lwjgl.opengl.GL11;

import superCool3dEngine.Transformable.Axis;
import superCool3dEngine.shaders.Shader;

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
		float[] vertices = { 
				-0.5f, -0.5f, 0.0f, // Bottom left
				0.5f, -0.5f, 0.0f, // Bottom right
				0.0f, 0.5f, 0.0f // Top
		};
		Triangle triangle = new Triangle(vertices);
		
		triangle.getShader().getColor().setRed(red);
		triangle.getShader().getColor().setGreen(green);
		triangle.getShader().getColor().setBlue(blue);
		
		red += 0.001f;
		blue += 0.001f;
		green += 0.001f;
		
		triangle.rotate(Axis.X, angle);
		triangle.rotate(Axis.Y, angle);
		triangle.rotate(Axis.Z, angle);
		triangle.render();
		
		angle += 0.005;
	}

}
