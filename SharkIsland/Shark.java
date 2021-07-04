package SharkIsland;

import java.awt.Color;

public class Shark {

    //鯊魚座標
    private int x = Parameter.start + Parameter.out;
    private int y = 2 * Parameter.start + Parameter.out;
    //鯊魚大小
    private int r = 10;
    /**
     * 鯊魚軌道半徑，預設為島的大小
     */
    private int orbitRadius = Parameter.start;
    //鯊魚角度座標
    private double dig = 0;
    //鯊魚顏色
    private Color color = Color.black;
    //鯊魚前進方向
    private boolean back = false;
    //鯊魚速度
    public double speed = Parameter.sharkSpeed;

    /**
     * 隨意位置，島的邊緣開始
     */
    public Shark() {
        setDig(Math.random() * 6);
    }

    /**
     * @param dig 角座標
     * @param r 鯊魚大小
     * @param orbitRadius 鯊魚軌道半徑
     */
    public Shark(int dig, int r, int orbitRadius) {
        super();
        this.r = r;
        setDig(dig);
        this.orbitRadius = orbitRadius;
    }

    //鯊魚運動
    public void sharkMove() {
        double sp = 3d;
        //逆時針移動方案
        int x1 = (int) (orbitRadius * Math.cos(dig + (speed * sp / orbitRadius))) + orbitRadius - this.r + Parameter.out;
        int y1 = (int) (orbitRadius * Math.sin(dig + (speed * sp / orbitRadius))) + orbitRadius - this.r + Parameter.out;
        //順時針移動方案
        int x2 = (int) (orbitRadius * Math.cos(dig - (speed * sp / orbitRadius))) + orbitRadius - this.r + Parameter.out;
        int y2 = (int) (orbitRadius * Math.sin(dig - (speed * sp / orbitRadius))) + orbitRadius - this.r + Parameter.out;
        //選擇離people近的方案
        int rand = (int) (Math.random() * 40 - 20);
        int rand2 = (int) (Math.random() * 40 - 20);//參考點抖動處理，避免直徑移動不追蹤的問題
        int x = People.x + rand;
        int y = People.y + rand2;
        if ((x - x1) * (x - x1) + (y - y1) * (y - y1)
                <= (x - x2) * (x - x2) + (y - y2) * (y - y2)) {
//      if(back){//轉向
            dig += (speed * sp / orbitRadius);
        } else {
            dig -= (speed * sp / orbitRadius);
        }
        setDig(dig);
    }

    //鯊魚撞到人
    public void isCrashPeople() {
        if ((x - People.x) * (x - People.x) + (y - People.y) * (y - People.y) < 100) {
            Parameter.gameover = true;//失敗判定
            this.color = Parameter.nextColor(this.color);//變色
            People.color = Parameter.nextColor(People.color);//人物變色
            back = !back;//轉向
        }
    }

    //鯊魚是否撞到鯊魚
    public boolean isCrash(Shark shark) {
        int lx = Math.abs(x + r - (shark.x + shark.r));
        int ly = Math.abs(y + r - (shark.y + shark.r));
        boolean h = lx <= (r + shark.r) && ly <= (r + shark.r);
        return h;
    }

    //鯊魚撞到鯊魚
    public void sharkCrash(Shark shark) {
        if (isCrash(shark)) {
            this.color = Parameter.nextColor(this.color);//變色
            back = !back;//轉向
        }
    }

    //重置鯊魚
    public void reset() {
        //角度座標隨機
        dig = Math.random() * 3.14d;
        x = (int) (Math.cos(dig) * this.orbitRadius) + this.orbitRadius - this.r + Parameter.out;
        y = (int) (Math.sin(dig) * this.orbitRadius) + this.orbitRadius - this.r + Parameter.out;
    }

    //依角座標生成直角坐標
    public void setDig(double dig) {
        this.dig = dig;
        x = (int) (Math.cos(dig) * this.orbitRadius) + this.orbitRadius - this.r + Parameter.out;
        y = (int) (Math.sin(dig) * this.orbitRadius) + this.orbitRadius - this.r + Parameter.out;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
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
