package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class Gui extends JFrame implements Setup {
    public static final int WIDTH_FRAME = 715;
    public static final int HEIGTH_FRAME = 740;

    public Gui() {
        initalizeContainer();
        initalizeComponents();
        registerListener();
    }

    @Override
    public void initalizeContainer() {
        setIconImage(ImageStore.IMG_LOGO);
        setTitle("Cờ Chiếm Thành");
        setLayout(new CardLayout());
        setResizable(true);
        setSize(WIDTH_FRAME, HEIGTH_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initalizeComponents() {
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}
