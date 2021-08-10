package pers.rootwhois.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 游戏面板
 *
 * @author admin
 * @date 2021/8/10 12:55 下午
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length;

    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    Integer fx;
    boolean isStart = false;
    boolean isDead = false;

    int score;
    int stair = 5;


    /**
     * 定时器 监听时间 帧 执行定时操作
     */
    Timer timer = new Timer(100, this);

    int foodX;
    int foodY;
    Random random = new Random();


    public GamePanel() {
        init();

        // 获取键盘的监听事件
        setFocusable(true);
        addKeyListener(this);
        timer.start();
    }

    /**
     * 初始化
     */
    private void init() {
        length = 3;

        // 头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;

        // 第一节身体坐标
        snakeX[1] = 75;
        snakeY[1] = 100;

        // 第二节身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;

        // 默认脑袋朝右
        fx = Data.RIGHT;

        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        score = 0;
    }


    /**
     * 画板 画界面和蛇
     *
     * @param g 画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // 头部广告栏
        Data.HEADER_ICON.paintIcon(this, g, 25, 11);

        // 绘制游戏区域
        g.setColor(Color.DARK_GRAY);
        g.fillRect(25, 75, 850, 600);
        g.setColor(null);

        // 画一条静态的小蛇
        if (fx.equals(Data.RIGHT)) {
            Data.RIGHT_ICON.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals(Data.LEFT)) {
            Data.LEFT_ICON.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals(Data.UP)) {
            Data.UP_ICON.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals(Data.DOWN)) {
            Data.DOWN_ICON.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.BODY_ICON.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        Data.FOOD_ICON.paintIcon(this, g, foodX, foodY);

        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度" + length, 750, 35);
        g.drawString("分数" + score, 750, 60);
        g.setColor(null);


        // 游戏提示是否开始
        if (!isStart) {
            // 画一个文字 String
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
            g.setColor(null);
        }

        // 失败提醒
        if (isDead) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏失败，按下空格重新开始游戏", 150, 400);
            g.setColor(null);
        }

    }


    /**
     * 接收键盘的输入
     *
     * @param e 事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // 键盘按下未释放
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isDead) {
                isDead = false;
                // 重新初始化游戏
                init();
            } else {
                isStart = !isStart;
            }
            // 重新绘制界面
            repaint();
        }

        // 键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT && !fx.equals(Data.RIGHT)) {
            fx = Data.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT && !fx.equals(Data.LEFT)) {
            fx = Data.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP && !fx.equals(Data.DOWN)) {
            fx = Data.UP;
        } else if (keyCode == KeyEvent.VK_DOWN && !fx.equals(Data.UP)) {
            fx = Data.DOWN;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 释放某个键
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 按下 弹起：敲击
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果游戏处于开始状态 并且游戏没有结束
        if (isStart && !isDead) {
            // 右移
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            // 通过控制方向让头部移动
            if (fx.equals(Data.RIGHT)) {
                snakeX[0] += 25;
                // 边界判断
                if (snakeX[0] > Data.RIGHT_BORDER) {
                    snakeX[0] = Data.LEFT_BORDER;
                }
            } else if (fx.equals(Data.LEFT)) {
                snakeX[0] -= 25;
                if (snakeX[0] < Data.LEFT_BORDER) {
                    snakeX[0] = Data.RIGHT_BORDER;
                }
            } else if (fx.equals(Data.DOWN)) {
                snakeY[0] += 25;
                if (snakeY[0] > Data.DOWN_BORDER) {
                    snakeY[0] = Data.UP_BORDER;
                }
            } else if (fx.equals(Data.UP)) {
                snakeY[0] -= 25;
                if (snakeY[0] < Data.UP_BORDER) {
                    snakeY[0] = Data.DOWN_BORDER;
                }
            }

            // 如果小蛇的头和食物坐标重合
            if (foodX == snakeX[0] && foodY == snakeY[0]) {
                length++;
                // 重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);

                score += 10;

                if (length % stair == 0) {
                    timer.setDelay(timer.getDelay() - 10);
                }
            }

            // 结束判断
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isDead = true;
                }
            }

            repaint();
        }

        // 启动计时器 让时间动起来
        timer.start();
    }
}
