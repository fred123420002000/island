package SharkIsland;

import SharkIsland.Stage;
import java.awt.Color;
import java.awt.event.KeyEvent;

//參數集合，通用方法
public class Parameter {
///////////////////自由控制參數///////////////////////////////

    //島的尺寸
    public static int start = 300;
    //額外空間尺寸
    public static int out = 200;
    //刷新延遲時間
    public static int speed = 10;
    //有多少鯊魚
    public static int numberOfShark = 1;
    //鍵盤移動控制鍵，按下後 滑鼠<->鍵盤控制切換 
    //KeyEvent.VK_SPACE是空白
    public static int moveControl = KeyEvent.VK_SPACE;
    //鯊魚速度是幾倍
    public static double sharkSpeed = 4d;
////////////////////全局監控變數//////////////////////////////////////
    //是否鍵盤移動與預設
    public static boolean isMoveByKey = true;
    //遊戲狀態
    public static Stage stage = Stage.START;
    //是否被抓到
    public static boolean gameover = false;
    //滑鼠位置紀錄與預設
    public static int mouseX = start;
    public static int mouseY = start;
    //方向鍵狀態紀錄與預設
    public static boolean up = false;
    public static boolean down = false;
    public static boolean left = false;
    public static boolean right = false;
///////////////////共通功能函數///////////////////////////////////

    /**
     * 下一個狀態
     */
    public static void nextStage() {
        switch (stage) {
            case START:
                stage = Stage.RUNNING;
                break;
            case RUNNING:
                stage = Stage.PASS;
                break;
            case PASS:
                stage = Stage.RUNNING;
                break;
            case OVER:
                stage = Stage.START;
                break;
        }
    }

    /**
     * 變色
     */
    public static Color nextColor(Color color) {
        if (color == Color.BLACK) {
            return Color.BLUE;
        } else if (color == Color.BLUE) {
            return Color.CYAN;
        } else if (color == Color.CYAN) {
            return Color.DARK_GRAY;
        } else if (color == Color.DARK_GRAY) {
            return Color.GRAY;
        } else if (color == Color.GRAY) {
            return Color.GREEN;
        } else if (color == Color.GREEN) {
            return Color.LIGHT_GRAY;
        } else if (color == Color.LIGHT_GRAY) {
            return Color.MAGENTA;
        } else if (color == Color.MAGENTA) {
            return Color.ORANGE;
        } else if (color == Color.ORANGE) {
            return Color.PINK;
        } else if (color == Color.PINK) {
            return Color.RED;
        } else if (color == Color.RED) {
            return Color.WHITE;
        } else if (color == Color.WHITE) {
            return Color.YELLOW;
        } else {
            return Color.BLACK;
        }
    }
}
