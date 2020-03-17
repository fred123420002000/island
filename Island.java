package island;
public class Island {
	// 定义小球的坐标
	private int x, y;
	// 定义小球的半径
	private int r;

	public Island() {
		x = (int) (0);
		y = (int) (0);
		r = 300;
	}

	public Island(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}	
}