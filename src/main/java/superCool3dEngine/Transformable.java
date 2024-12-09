package superCool3dEngine;

public interface Transformable {
	public static enum Axis {
		X, Y, Z
	}

	public void translate(float x, float y, float z);

	public void rotate(Axis axis, float angle);
}
