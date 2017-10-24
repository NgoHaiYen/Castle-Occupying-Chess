package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.GameManager;
import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.BitSet;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class GamePanel extends BasePanel implements Runnable {
    private GameManager gameManager;
    private BitSet bitSet;


    public GamePanel() {
        super();
        startGame();
    }

    @Override
    public void initalizeContainer() {
        setLayout(null);
        setBackground(Color.RED);
    }

    @Override
    public void registerListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameManager.checkOnClick(e.getX(), e.getY());
                repaint();
            }
        };
        addMouseListener(mouseAdapter);
    }

    @Override
    public void initalizeComponents() {
        gameManager = new GameManager();
        bitSet = new BitSet();
    }

    @Override
    public void run() {
        String typeOfChessToSelect = "";

        while (true) {
//            String typeOfChessToSelect="";
            if (bitSet.get(KeyEvent.VK_O)) {
                System.out.println("o");
                typeOfChessToSelect = "O1";
            }
            if (bitSet.get(KeyEvent.VK_P)) {
                System.out.println("p");

                typeOfChessToSelect = "P1";
            }
            if (bitSet.get(KeyEvent.VK_Q)) {
                typeOfChessToSelect = "Q1";
            }
            if (bitSet.get(KeyEvent.VK_K)) {
                typeOfChessToSelect = "K1";
            }
            if (bitSet.get(KeyEvent.VK_L)) {
                typeOfChessToSelect = "L1";
            }
            if (bitSet.get(KeyEvent.VK_M)) {
                typeOfChessToSelect = "M1";
            }
            if (bitSet.get(KeyEvent.VK_SPACE)) {
                typeOfChessToSelect = "SPACE";
            }
            if (bitSet.get(KeyEvent.VK_ENTER)) {
                typeOfChessToSelect = "ENTER";
            }
            gameManager.checkType(typeOfChessToSelect);
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startGame() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(ImageStore.IMG_CHESS_BOARD, -8, 0, 714, 700, null);
        gameManager.drawAbility(graphics2D);
        gameManager.drawChess(graphics2D);
    }
}
