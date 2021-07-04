package SharkIsland;

import java.awt.Color;

public class Island {

    //島的座標
    private int x, y;
    //島的半徑
    private int r;
    //島的顏色
    public Color color = Color.YELLOW;

    /**
     * 島的初始狀態座標(0,0) 半徑300
     */
    public Island() {
        x = (int) (0+Parameter.out);
        y = (int) (0+Parameter.out);
        r = Parameter.start;
    }

    /**
     * 
     * @param x 座標
     * @param y 座標
     * @param r 大小
     * @param color 顏色
     */
    public Island(int x, int y, int r, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
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
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
