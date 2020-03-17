package island;

public class Ball {
    // 定义小球的运动方向

    public static final int LEFT_UP = 0;
    public static final int LEFT_DOWN = 1;
    public static final int RIGHT_UP = 2;
    public static final int RIGHT_DOWN = 3;
    // 定义小球当前的运动方向
    private int f = LEFT_UP;
    // 定义小球的坐标
    private int x, y;
    // 定义小球的半径
    private int r;
    // 定义小球的半径
    private double dig = 0;

    private boolean flag = true;

    public Ball() {
        x = (int) (600);
        y = (int) (300);
        r = 10;
        dig = Math.random() * 6;
        int ran = (int) (Math.random() * 2);
        if (ran == 0) {
            f = LEFT_UP;
        } else if (ran == 1) {
            f = RIGHT_UP;
        }
    }

    public Ball(int x, int y, int r, int f) {
        super();
        this.x = x;
        this.y = y;
        this.r = r;
        this.f = f;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
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
    //小球移动

    public void ballMove() {
        int x1 = (int) (300 * Math.cos(dig + (12.0d / 300.0d))) + 290;
        int y1 = (int) (300 * Math.sin(dig + (12.0d / 300.0d))) + 290;
        int x2 = (int) (300 * Math.cos(dig - (12.0d / 300.0d))) + 290;
        int y2 = (int) (300 * Math.sin(dig - (12.0d / 300.0d))) + 290;

        if ((BallJpanel.dx - x1) * (BallJpanel.dx - x1) + (BallJpanel.dy - y1) * (BallJpanel.dy - y1) <= (BallJpanel.dx - x2) * (BallJpanel.dx - x2) + (BallJpanel.dy - y2) * (BallJpanel.dy - y2)) {
            dig += (12.0d / 300.0d);
            x = x1;
            y = y1;
        } else {
            dig -= (12.0d / 300.0d);
            x = x2;
            y = y2;
        }

        if (BallJpanel.up && !BallJpanel.down && !BallJpanel.left && !BallJpanel.right) {
            BallJpanel.dy -= 3;
        } else if (!BallJpanel.up && BallJpanel.down && !BallJpanel.left && !BallJpanel.right) {
            BallJpanel.dy += 3;
        } else if (!BallJpanel.up && !BallJpanel.down && BallJpanel.left && !BallJpanel.right) {
            BallJpanel.dx -= 3;
        } else if (!BallJpanel.up && !BallJpanel.down && !BallJpanel.left && BallJpanel.right) {
            BallJpanel.dx += 3;
        } else if (BallJpanel.up && !BallJpanel.down && BallJpanel.left && !BallJpanel.right) {
            BallJpanel.dy -= 2;
            BallJpanel.dx -= 2;
        } else if (BallJpanel.up && !BallJpanel.down && !BallJpanel.left && BallJpanel.right) {
            BallJpanel.dy -= 2;
            BallJpanel.dx += 2;
        } else if (!BallJpanel.up && BallJpanel.down && BallJpanel.left && !BallJpanel.right) {
            BallJpanel.dx -= 2;
            BallJpanel.dy += 2;
        } else if (!BallJpanel.up && BallJpanel.down && !BallJpanel.left && BallJpanel.right) {
            BallJpanel.dy += 2;
            BallJpanel.dx += 2;
        }

    }

    // 小球越界处理
    public void ballBorder() {
        // 小球碰到挡板                
        if ((x - BallJpanel.dx) * (x - BallJpanel.dx) + (y - BallJpanel.dy) * (y - BallJpanel.dy) < 100) {
            BallJpanel.gameover();
        }
    }

    //小球碰撞检测
    public boolean isCrash(Ball ball) {
        int lx = Math.abs(x + r - (ball.x + ball.r));
        int ly = Math.abs(y + r - (ball.y + ball.r));
        boolean h = lx <= (r + ball.r) && ly <= (r + ball.r);
        return h;
    }

    //小球碰撞反弹
    public void ballCrash(Ball another) {
        if (isCrash(another)) {
            x++;
            y++;
            int temp = this.f;
            this.f = another.f;
            another.f = temp;
        }
    }

    // 重置小球的位置
    public void reset() {
        x = (int) (Math.random() * 800);
        y = (int) (Math.random() * 500);
        int ran = (int) (Math.random() * 2);
        if (ran == 0) {
            f = LEFT_UP;
        } else if (ran == 1) {
            f = RIGHT_UP;
        }
    }
}
