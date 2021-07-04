package SharkIsland;

import java.awt.Color;

public class People {

    //人物起始位置
    public static int x = Parameter.start + Parameter.out;
    public static int y = Parameter.start + Parameter.out;
    //人物顏色
    public static Color color = Color.black;

    /**
     * 鍵盤移動
     */
    public static void moveByKey() {
        if (Parameter.up && !Parameter.down && !Parameter.left && !Parameter.right) {
            y -= 3;
        } else if (!Parameter.up && Parameter.down && !Parameter.left && !Parameter.right) {
            y += 3;
        } else if (!Parameter.up && !Parameter.down && Parameter.left && !Parameter.right) {
            x -= 3;
        } else if (!Parameter.up && !Parameter.down && !Parameter.left && Parameter.right) {
            x += 3;
        } else if (Parameter.up && !Parameter.down && Parameter.left && !Parameter.right) {
            y -= 2;
            x -= 2;
        } else if (Parameter.up && !Parameter.down && !Parameter.left && Parameter.right) {
            y -= 2;
            x += 2;
        } else if (!Parameter.up && Parameter.down && Parameter.left && !Parameter.right) {
            y += 2;
            x -= 2;
        } else if (!Parameter.up && Parameter.down && !Parameter.left && Parameter.right) {
            y += 2;
            x += 2;
        }
    }

    /**
     * 滑鼠追蹤移動
     */
    public static void moveByMouse() {
        double lx = x - Parameter.mouseX;
        double ly = y - Parameter.mouseY;
        double lxy = Math.sqrt(lx * lx + ly * ly);
        if (lxy != 0) {
            x -= lx * 3 / lxy;
            y -= ly * 3 / lxy;
        }
    }
}
