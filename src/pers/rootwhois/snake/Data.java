package pers.rootwhois.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 存放外部数据
 *
 * @author admin
 * @date 2021/8/10 12:59 下午
 */
public class Data {
    public static final Integer RIGHT = 0;
    public static final Integer LEFT = 1;
    public static final Integer UP = 2;
    public static final Integer DOWN = 3;

    public static final Integer LEFT_BORDER = 25;
    public static final Integer RIGHT_BORDER = 850;
    public static final Integer UP_BORDER = 75;
    public static final Integer DOWN_BORDER = 650;

    private static final URL HEADER_URL = Data.class.getResource("/statics/header.png");
    private static final URL BODY_URL = Data.class.getResource("/statics/body.png");
    private static final URL FOOD_URL = Data.class.getResource("/statics/food.png");
    private static final URL UP_URL = Data.class.getResource("/statics/up.png");
    private static final URL DOWN_URL = Data.class.getResource("/statics/down.png");
    private static final URL LEFT_URL = Data.class.getResource("/statics/left.png");
    private static final URL RIGHT_URL = Data.class.getResource("/statics/right.png");

    public static final ImageIcon HEADER_ICON = new ImageIcon(HEADER_URL);
    public static final ImageIcon BODY_ICON = new ImageIcon(BODY_URL);
    public static final ImageIcon FOOD_ICON = new ImageIcon(FOOD_URL);
    public static final ImageIcon UP_ICON = new ImageIcon(UP_URL);
    public static final ImageIcon DOWN_ICON = new ImageIcon(DOWN_URL);
    public static final ImageIcon LEFT_ICON = new ImageIcon(LEFT_URL);
    public static final ImageIcon RIGHT_ICON = new ImageIcon(RIGHT_URL);

}
