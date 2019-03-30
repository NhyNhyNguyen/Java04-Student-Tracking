/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author qphan
 */
public class ImageUtils {

    public static final String LOCK_PATH = "/images/icon_lock_screen.jpg";
    public static final String LOGO_BKIT_PATH = "/images/icon_logo_bkcit.png";
    public static final String LOGO_TEAMVIETDEV_PATH = "/images/icon_logo_bkit.png";
    public static final String MENU0_WHITE_PATH = "/images/icon_menu0_white_24dp.png";
    public static final String MENU1_WHITE_PATH = "/images/icon_menu0_white_24dp.png";
    public static final String MENU2_WHITE_PATH = "/images/icon_menu0_white_24dp.png";
    public static final String MENU3_WHITE_PATH = "/images/icon_menu0_white_24dp.png";
    public static final String MENU4_WHITE_PATH = "/images/icon_menu0_white_24dp.png";

    public static final String TRANGCHU1_WHITE_PATH = "/images/icon_trangchu1_white_24dp.png";
    public static final String TRANGCHU2_WHITE_PATH = "/images/icon_trangchu2_white_24dp.png";
    public static final String TRANGCHU3_WHITE_PATH = "/icon_trangchu3_white_24dp.png";

    public static final String UNGDUNG_WHITE_PATH = "/images/icon_ung_dung_white_24dp.png";

    public static ImageIcon createIcon(final String path) {
        return new ImageIcon(ImageUtils.class.getResource(path));
    }

    public static Icon createIcon(final String path, final int width, final int height) {
        final Image image = new ImageIcon(path)
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

}
