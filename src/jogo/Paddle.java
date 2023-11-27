package jogo;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Paddle extends Sprite {
    private int dx;

    public Paddle() {
        initPaddle();
    }

    private void initPaddle() {
        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {
        var ii = new ImageIcon("src/resources/paddle.png");
        setImageObject(ii.getImage());
    }

    void move() {
        setPositionX(getPositionX() + dx);

        if (getPositionX() <= 0) {
            setPositionX(0);
        }

        if (getPositionX() >= Commons.WIDTH - getImageWidth()) {
            setPositionX(Commons.WIDTH - getImageWidth());
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -Commons.velocidadePaddle;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = Commons.velocidadePaddle;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {
        setPositionX(Commons.INIT_PADDLE_X);
        setPositionY(Commons.INIT_PADDLE_Y);
    }
}
