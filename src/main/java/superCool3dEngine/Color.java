package superCool3dEngine;

public class Color {
	public final static float[] COLOR_WHITE = {1.0f, 1.0f, 1.0f, 1.0f};
	private float red, green, blue, alpha;

	public Color(float[] rgba) {
		super();

		assert rgba.length == 4;

		red = rgba[0];
		green = rgba[1];
		blue = rgba[2];
		alpha = rgba[3];
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

}
