package tools;

public class CollisionRect {
	float x, y;
	int width, height;
	
	public CollisionRect (float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// move the Collision rectangle
	public void move (float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Boolean condition if the current rectangle is colliding with another rectangle.
	 * @param rect
	 * @return true if collision is imminent.
	 */
	public boolean collidesWith (CollisionRect rect) {
		return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
	}
}
