package superCool3dEngine;

import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.opengl.GL11;

import superCool3dEngine.shaders.Shader;

public class Main {

	public static void main(String[] args) {
		Window window = new Window.Builder(new WindowTest())
				.setResizable(true)
				.build();
		window.run();

	}

}

class WindowTest implements Renderable {

	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		Shader shader = new Shader("./src/main/java/superCool3dEngine/shaders/vertex/basic.vert", "./src/main/java/superCool3dEngine/shaders/fragment/basic.frag");
		Triangle triangle = new Triangle(shader);
		triangle.render();
	}

}
