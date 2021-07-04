package SharkIsland;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import SharkIsland.Parameter;

public class IslandStart implements KeyListener {

    public static void main(String[] args) {
        IslandStart bf = new IslandStart();
        bf.init();
    }
    //建立視窗對象
    JFrame frame = new JFrame();
    //建立畫布對象
    IslandJpanel ijp = new IslandJpanel();

    //初始化視窗屬性
    public void init() {
        //設置標題
        frame.setTitle("鯊魚島");
        //設置視窗位置，大小
        frame.setBounds(200, 100, 2 * Parameter.start + 2*Parameter.out, 2 * Parameter.start + 2*Parameter.out);
        //設置默認關閉方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //加入畫布class到視窗中
        frame.add(ijp);
        //設置窗口可見
        frame.setVisible(true);
        //添加鍵盤監聽器
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {//按著不放
    }

    @Override
    public void keyReleased(KeyEvent e) {//放開按鍵
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Parameter.up = false;
                break;
            case KeyEvent.VK_DOWN:
                Parameter.down = false;
                break;
            case KeyEvent.VK_LEFT:
                Parameter.left = false;
                break;
            case KeyEvent.VK_RIGHT:
                Parameter.right = false;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {//按下按鍵
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Parameter.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Parameter.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Parameter.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Parameter.right = true;
        } else if (e.getKeyCode() == Parameter.moveControl) {//按下對應的移動控制鍵
            Parameter.isMoveByKey = !Parameter.isMoveByKey;//改變控制方式
        } else {//其他按鍵
            //狀態切換
            Parameter.nextStage();
            //在consol印出KeyCode
            System.err.println("KeyCode = " + e.getKeyCode());
        }
    }
}
