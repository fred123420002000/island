package SharkIsland;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class IslandJpanel extends JPanel implements MouseMotionListener,
        MouseListener {

    //鯊魚列表
    List<Shark> sharklist = new ArrayList<Shark>();
    //島列表
    List<Island> islandlist = new ArrayList<Island>();

    public IslandJpanel() {
        //添加鯊魚
        for (int i = 0; i < Parameter.numberOfShark; i++) {
            Shark shark = new Shark();
            sharklist.add(shark);
        }
        Island island = new Island();//島
        islandlist.add(island);
//        island = new Island(Parameter.start+Parameter.out-Parameter.start/4, Parameter.start+Parameter.out-Parameter.start/4, Parameter.start/4, Color.GRAY);//高速度區域
//        islandlist.add(island);
        island = new Island(Parameter.start+Parameter.out-10, Parameter.start+Parameter.out-10, 10, Color.GREEN);//島中央
        islandlist.add(island);
        move();
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    public void paint(Graphics g) {
        super.paint(g);
        //設置背景
        setBackground(Color.black);
        //島與中央點
        for (int i = 0; i < islandlist.size(); i++) {
            Island island = islandlist.get(i);
            g.setColor(island.getColor());
            g.fillOval(island.getX(), island.getY(), 2 * island.getR(), 2 * island.getR());
        }
        //畫出玩家
        g.setColor(People.color);
        g.fillRect(People.x, People.y, 20, 20);
        //畫出鯊魚
        for (int i = 0; i < sharklist.size(); i++) {
            Shark shark = sharklist.get(i);
            g.setColor(shark.getColor());
            g.fillOval(shark.getX(), shark.getY(), 2 * shark.getR(), 2 * shark.getR());
        }

        //開始狀態
        if (Parameter.stage == Stage.START) {
            g.setColor(Color.red);
            g.setFont(new Font("", Font.PLAIN, 40));
            g.drawString("任意鍵開始/暫停，空白切換滑鼠控制", 300, 250);
        }
        //暫停狀態
        if (Parameter.stage == Stage.PASS) {
            g.setColor(Color.red);
            g.setFont(new Font("標楷體", Font.PLAIN, 40));
            g.drawString("遊戲暫停", 300, 250);
        }
        //成功逃脫結束
        if ((People.x - (Parameter.start+Parameter.out)) * (People.x - (Parameter.start+Parameter.out))
                + (People.y - (Parameter.start+Parameter.out)) * (People.y - (Parameter.start+Parameter.out))
                > Parameter.start * Parameter.start + 50 * Parameter.start) {
            Parameter.stage = Stage.OVER;
            g.setColor(Color.red);
            g.setFont(new Font("新細明體", Font.PLAIN, 40));
            g.drawString("成功", 300, 250);
        }
        //被抓結束
        if (Parameter.gameover) {
            Parameter.stage = Stage.OVER;
            g.setColor(Color.red);
            g.setFont(new Font("細明體", Font.PLAIN, 40));
            g.drawString("失敗", 300, 250);
        }
    }

    public void move() {
        new Thread() {//新執行緒
            public void run() {//執行緒內容
                while (true) {
                    //運行時
                    if (Parameter.stage == Stage.RUNNING) {
                        for (int i = 0; i < sharklist.size(); i++) {
                            Shark ball = sharklist.get(i);
                            //鯊魚移動
                            ball.sharkMove();
                            ball.isCrashPeople();
                            //鯊魚間的碰撞
                            for (int j = i + 1; j < sharklist.size(); j++) {
                                Shark ball2 = sharklist.get(j);
                                ball.sharkCrash(ball2);
                            }
                        }
                        //鍵盤或滑鼠移動
                        if (Parameter.isMoveByKey) {
                            People.moveByKey();
                        } else {
                            People.moveByMouse();
                        }
                    } else if (Parameter.stage == Stage.START) {
                        //玩家回到中央
                        People.x = Parameter.start+Parameter.out;
                        People.y = Parameter.start+Parameter.out;
                        Parameter.gameover = false;
                    } else {
                        //暫停或結束
                    }
                    //刷新畫面
                    repaint();
                    try {
                        Thread.sleep(Parameter.speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();//執行緒啟動
    }

    @Override
    public void mouseDragged(MouseEvent e) {//滑鼠拖曳
    }

    @Override
    public void mouseMoved(MouseEvent e) {//滑鼠移動
        //滑鼠位置
        Parameter.mouseX = e.getX();
        Parameter.mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {//滑鼠點擊
        if (Parameter.stage == Stage.OVER) {
            //重置鯊魚
            for (int i = 0; i < sharklist.size(); i++) {
                Shark ball = sharklist.get(i);
                ball.reset();
            }
            Parameter.gameover = false;
        }
        Parameter.nextStage();//狀態更新
    }

    @Override
    public void mousePressed(MouseEvent e) {//滑鼠按住
    }

    @Override
    public void mouseReleased(MouseEvent e) {//滑鼠放開
    }

    @Override
    public void mouseEntered(MouseEvent e) {//滑鼠進入=繼續        
        if (Parameter.stage == Stage.PASS) {
            Parameter.stage = Stage.RUNNING;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {//滑鼠離開=暫停        
        if (Parameter.stage == Stage.RUNNING) {
            Parameter.stage = Stage.PASS;
        }
    }

}
