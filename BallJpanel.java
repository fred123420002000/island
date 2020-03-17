package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BallJpanel extends JPanel implements MouseMotionListener,
        MouseListener {

    // 定义挡板的坐标
    static int dx = 300, dy = 300;
    //成绩
    static int score = 0;
    //球速
    static int n = 1;
    //生命值
    static int hp = n;
    //游戏速度
    int speed = 10;
    //开始状态
    static final int START = 0;
    //运行状态
    static final int RUNNING = 1;
    //暂停状态
    static final int PASS = 2;
    //结束状态
    static final int OVER = 3;
    //当前的运动状态
    int state = START;
    //建立List集合存储Ball类
    List<Ball> list = new ArrayList<Ball>();
    List<Island> list2 = new ArrayList<Island>();

    static boolean up = false, down = false, left = false, right = false;

    public BallJpanel() {
        // 添加小球类
        for (int i = 0; i < hp; i++) {
            Ball ball = new Ball();
            list.add(ball);
        }
        move();
        addMouseMotionListener(this);
        addMouseListener(this);
        Island island = new Island();
        list2.add(island);

    }

    public void paint(Graphics g) {
        super.paint(g);
        //设置背景颜色
        setBackground(Color.black);
        //島
        Island island = list2.get(0);
        g.setColor(Color.yellow);
        g.fillOval(island.getX(), island.getY(), 2 * island.getR(), 2 * island.getR());
        //画出挡板
        g.setColor(Color.BLACK);
        g.fillRect(dx, dy, 20, 20);
        // 画出小球
        for (int i = 0; i < list.size(); i++) {
            Ball ball = list.get(i);
            g.setColor(Color.BLACK);
            g.fillOval(ball.getX(), ball.getY(), 2 * ball.getR(), 2 * ball.getR());
        }

        //开始时重置成绩、生命值与速度,屏幕上显示游戏开始
        if (state == START) {
            score = 0;
            hp = n;
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
            g.drawString("游戏开始", 300, 250);
        }
        //暂停时屏幕上显示游戏暂停
        if (state == PASS) {
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
            g.drawString("游戏暂停", 300, 250);
        }

        if ((dx - 300) * (dx - 300) + (dy - 300) * (dy - 300) > 90000) {
            state = OVER;
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
            g.drawString("成功", 300, 250);
        }
    }

    public void move() {
        new Thread() {
            public void run() {
                while (true) {
                    //当运行时
                    if (state == RUNNING) {
                        for (int i = 0; i < list.size(); i++) {
                            Ball ball = list.get(i);
                            // 调用ballMove方法让小球动起来
                            ball.ballMove();
                            ball.ballBorder();
                            // 小球碰撞
                            for (int j = i + 1; j < list.size(); j++) {
                                Ball ball2 = list.get(j);
                                ball.ballCrash(ball2);
                            }
                        }
                    } else if (state == START) {
                        //开始时将挡板移到屏幕中间
                        dx = 300;
                        dy = 300;
                    } else {
                        //暂停
                    }
                    // 重绘
                    repaint();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        //当运行时挡板跟着鼠标移动
        if (state == RUNNING) {
//            dx = e.getX();
//            dy = e.getY();
//            if (dx <= 0) {
//                dx = 0;
//            }
//            if (dx >= 800 - 116) {
//                dx = 684;
//            }
//            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //开始时单击鼠标运行 运行时单击鼠标暂停 暂停时单击鼠标运行
        if (state == START) {
            state = RUNNING;
        } else if (state == RUNNING) {
            state = PASS;
        } else if (state == PASS) {
            state = RUNNING;
        } else if (state == OVER) {
            state = START;
            //重置小球的坐标
            for (int i = 0; i < list.size(); i++) {
                Ball ball = list.get(i);
                ball.reset();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //鼠标进入窗口暂停变运行
        if (state == PASS) {
            state = RUNNING;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //鼠标离开窗口运行变暂停
        if (state == RUNNING) {
            state = PASS;
        }
    }

}
