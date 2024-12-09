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
	
	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		Shader shader = new Shader("./src/main/java/superCool3dEngine/shaders/vertex/transformation.vert",
				"./src/main/java/superCool3dEngine/shaders/fragment/basic.frag");
		
		float[] vertices = { 
				-0.5f, -0.5f, 0.0f, // Bottom left
				0.5f, -0.5f, 0.0f, // Bottom right
				0.0f, 0.5f, 0.0f // Top
		};
		Triangle triangle = new Triangle(shader, vertices);
		triangle.rotate(Axis.X, angle);
		triangle.rotate(Axis.Y, angle);
		triangle.rotate(Axis.Z, angle);
		triangle.render();
		
		angle += 0.05;
	}

}
