package island;

import static island.BallJpanel.dy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Ballj implements KeyListener {
    // 建立一个窗体对象

    JFrame frame = new JFrame();

    // 建立画布对象
    BallJpanel jp = new BallJpanel();
    // 自定义方法为窗体设置属性

    public void init() {
        // 设置标题
        frame.setTitle("鱷魚島");
        // 设置窗体大小、位置
        frame.setBounds(200, 100, 615, 635);
        // 设置默认关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 将画布类对象添加到窗体中
        frame.add(jp);
        // 设置窗口可见
        frame.setVisible(true);
        frame.addKeyListener(this);

    }

    public static void main(String[] args) {
        Ballj bf = new Ballj();
        bf.init();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                jp.up = false;
                break;
            case KeyEvent.VK_DOWN:
                jp.down = false;
                break;
            case KeyEvent.VK_LEFT:
                jp.left = false;
                break;
            case KeyEvent.VK_RIGHT:
                jp.right = false;
                break;
        }
    }

    @Override//键被按下
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                jp.up = true;
                break;
            case KeyEvent.VK_DOWN:
                jp.down = true;
                break;
            case KeyEvent.VK_LEFT:
                jp.left = true;
                break;
            case KeyEvent.VK_RIGHT:
                jp.right = true;
                break;
        }
    }
}
