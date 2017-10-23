package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.GameManager;
import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class GamePanel extends BasePanel implements Runnable {
    private GameManager gameManager;


    public GamePanel() {
        super();
        gameManager = new GameManager();
        startGame();
    }

    @Override
    public void initalizeContainer() {
        setLayout(null);
        setBackground(Color.RED);
    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initalizeComponents() {
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
    public void run() {
        while (true) {
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
