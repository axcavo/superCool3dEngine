package superCool3dEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

public class Window {
    public static final int DEFAULT_WIDTH = 480;
    public static final int DEFAULT_HEIGHT = 270;

    private long id;
    private Renderable renderable;
    private final int initialWidth;
    private final int initialHeight;
    private final boolean isResizable;

    // Private constructor to force use of the Builder
    private Window(Builder builder) {
    	super();
        this.renderable = builder.renderable;
        this.initialWidth = builder.initialWidth;
        this.initialHeight = builder.initialHeight;
        this.isResizable = builder.isResizable;
    }

    /**
     * Initializes the OpenGL magic to create the window.
     */
    private void init() {

        // Initialize GLFW.
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Set window hints.
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, isResizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        // Create the window.
        id = GLFW.glfwCreateWindow(initialWidth, initialHeight, "test", MemoryUtil.NULL, MemoryUtil.NULL);
        if (id == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the window");
        }

        // Final configurations.
        GLFW.glfwMakeContextCurrent(id);
        GLFW.glfwShowWindow(id);
    }

    /**
     * Run's the window.
     */
    public void run() {
        init();
        loop();
    }

    /**
     * Contains window rendering loop logic.
     */
    private void loop() {
        GL.createCapabilities();
        while (!GLFW.glfwWindowShouldClose(id)) {
            // Render renderable in the window.
            renderable.render();

            // Magic OpenGL thingy
            GLFW.glfwSwapBuffers(id);
            GLFW.glfwPollEvents();
        }
        terminate();
    }

    /**
     * Safely terminates the window process.
     */
    private void terminate() {
        GLFW.glfwDestroyWindow(id);
        GLFW.glfwTerminate();
    }
    
    public long getId() {
        return id;
    }

    /**
     * Builder class for constructing windows ;D.
     */
    public static class Builder {
        private Renderable renderable;
        private int initialWidth = DEFAULT_WIDTH;
        private int initialHeight = DEFAULT_HEIGHT;
        private boolean isResizable = false;

        public Builder(Renderable renderable) {
            this.renderable = renderable;
        }

        public Builder setInitialWidth(int width) {
            this.initialWidth = width;
            return this;
        }

        public Builder setInitialHeight(int height) {
            this.initialHeight = height;
            return this;
        }

        public Builder setResizable(boolean resizable) {
            this.isResizable = resizable;
            return this;
        }

        public Window build() {
            return new Window(this);
        }
    }
}
