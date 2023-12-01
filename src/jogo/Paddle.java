package jogo;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Paddle extends Sprite {
    private int dx;
    private int velocidadePaddle = 3;

    public Paddle() {
        initPaddle();
    }
    // Tem que ser chamado o init paddle passando I como argumento
    private void initPaddle() {
        loadImage(1);
        getImageDimensions();
        resetState();
    }

    public void setVelocidadePaddle(int velocidade) {
        velocidadePaddle = velocidade;

    }

    public void move() {
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
            dx = -velocidadePaddle;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = velocidadePaddle;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void loadImage(int i) {
        if (i == 1) {
            var ii = new ImageIcon("src/resources/paddle.png");
        }else{
            var ii = new ImageIcon("src/resources/testinho-2.png");

        }
        setImageObject(ii.getImage());
    }

    private void resetState() {
        setPositionX(Commons.INIT_PADDLE_X);
        setPositionY(Commons.INIT_PADDLE_Y);
    }
}
