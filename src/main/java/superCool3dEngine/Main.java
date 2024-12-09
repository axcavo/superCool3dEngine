package superCool3dEngine;

import org.lwjgl.opengl.GL11;

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
	}

}
