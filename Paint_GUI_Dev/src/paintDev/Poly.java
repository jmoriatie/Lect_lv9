package paintDev;

import java.awt.Color;

public class Poly {
	private int[] xx, yy; // 삼각형 위치배열
	private int point;
	private Color color;
	
	public Poly() {}

	public int[] getXx() {
		return xx;
	}

	public void setXx(int[] xx) {
		this.xx = xx;
	}

	public int[] getYy() {
		return yy;
	}

	public void setYy(int[] yy) {
		this.yy = yy;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
