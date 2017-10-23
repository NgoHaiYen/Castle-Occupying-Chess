package com.thanhozin.cochiemthanh.manager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class ImageStore {
    public static final Image IMG_CHESS_BOARD = getImage("/res/drawable/banco.png");

    public static final Image IMG_WHILE_K_1 = getImage("/res/drawable/t_k_1.png");
    public static final Image IMG_WHILE_L_1 = getImage("/res/drawable/t_l_1.png");
    public static final Image IMG_WHILE_M_1 = getImage("/res/drawable/t_m_1.png");
    public static final Image IMG_BLACK_O_1 = getImage("/res/drawable/d_o_1.png");
    public static final Image IMG_BLACK_P_1 = getImage("/res/drawable/d_p_1.png");
    public static final Image IMG_BLACK_Q_1 = getImage("/res/drawable/d_q_1.png");

    public static final Image IMG_ABILITY = getImage("/res/drawable/o_chon.png");
    public static final Image IMG_LOGO = getImage("/res/drawable/logo.png");

    private static Image getImage(String path) {
        return new ImageIcon(ImageStore.class.getResource(path)).getImage();
    }
}
