package pers.rootwhois.snake;

import javax.swing.*;
import java.awt.*;

/**
 * 启动类
 *
 * @author admin
 * @date 2021/8/10 12:50 下午
 */
public class StartGames {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("贪吃蛇小游戏");

        jFrame.add(new GamePanel());

        jFrame.setBounds(100, 100, 900, 720);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

    }
}
