package superCool3dEngine.shaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import superCool3dEngine.Color;

public class ShaderTemp {
	private final int programId;
	private final Color color;
	
	public ShaderTemp(String vertexPath, String fragmentPath) {
		super();
		
		String vertexSource = loadShaderSource(vertexPath);
		String fragmentSource = loadShaderSource(fragmentPath);
		
		int vertexShader = compileShader(GL20.GL_VERTEX_SHADER, vertexSource);
		int vertexFragment = compileShader(GL20.GL_FRAGMENT_SHADER, fragmentSource);
		
		programId = linkProgram(vertexShader, vertexFragment);
		color = new Color(Color.COLOR_WHITE);
		
		GL20.glDeleteShader(vertexShader);
		GL20.glDeleteShader(vertexFragment);
	}
	
	private String loadShaderSource(String filePath) {
		try {
			return Files.readString(Path.of(filePath));
		} catch (IOException e) {
			throw new RuntimeException("Failed to load shader file: " + filePath, e);
		}
	}
	
	private int compileShader(int type, String source) {
		int shaderId = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderId, source);
		GL20.glCompileShader(shaderId);
		
		if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
			String infoLog = GL20.glGetShaderInfoLog(shaderId);
            GL20.glDeleteShader(shaderId);
            throw new RuntimeException("Error compiling shader: " + infoLog);
		}
		return shaderId;
	}
	
	private int linkProgram(int vertexShader, int fragmentShader) {
		int programId = GL20.glCreateProgram();
		GL20.glAttachShader(programId, vertexShader);
		GL20.glAttachShader(programId, fragmentShader);
		GL20.glLinkProgram(programId);
		
		if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            String infoLog = GL20.glGetProgramInfoLog(programId);
            GL20.glDeleteProgram(programId);
            throw new RuntimeException("Error linking program: " + infoLog);
        }
		return programId;
	}
	
	public void setUniformColor() {
		int redLoc = GL30.glGetUniformLocation(programId, "red");
        int greenLoc = GL30.glGetUniformLocation(programId, "green");
        int blueLoc = GL30.glGetUniformLocation(programId, "blue");
        int alphaLoc = GL30.glGetUniformLocation(programId, "alpha");
		
		GL30.glUniform1f(redLoc, color.getRed());
		GL30.glUniform1f(greenLoc, color.getGreen());
		GL30.glUniform1f(blueLoc, color.getBlue());
		GL30.glUniform1f(alphaLoc, color.getAlpha());
	}
	
	public Color getColor() {
		return color;
	}
	
	public void use() {
        GL20.glUseProgram(programId);
    }

    public void delete() {
    	GL20.glDeleteProgram(programId);
    }

    public int getProgramId() {
        return programId;
    }
}
