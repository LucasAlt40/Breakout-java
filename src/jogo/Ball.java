package jogo;

import javax.swing.ImageIcon;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;
    private int velocidadeBolinha = 1;

    public Ball(int N_DA_BOLA) {

        initBall(N_DA_BOLA);
    }

    private void initBall(int N_DA_BOLA) {

        xdir = 1;
        ydir = -1;

        loadImage(N_DA_BOLA);
        getImageDimensions();
        resetState();
    }

    public void updateVelocidadeBolinha(int velocidadeBolinha) {
        if (this.velocidadeBolinha < Commons.VELOCIDADEMAXBOLA) {
            this.velocidadeBolinha = velocidadeBolinha;

        }

    }

    private void loadImage(int N_DA_BOLA) {
        if (N_DA_BOLA == 1) {
            var ii = new ImageIcon("src/resources/ball.png");
            this.setImageObject(ii.getImage());
        } else {
            var ii = new ImageIcon("src/resources/ball 2.png");
            this.setImageObject(ii.getImage());

        }
    }

    public void move() {
        this.setPositionX(this.getPositionX() + xdir);
        this.setPositionY(this.getPositionY() + ydir);

        if (this.getPositionX() <= 0) {
            setXDir(velocidadeBolinha);
        }

        if (this.getPositionX() >= Commons.LARGURA - this.getImageWidth()) {
            setXDir(-velocidadeBolinha);
        }

        if (this.getPositionY() <= 0) {
            setYDir(velocidadeBolinha);
        }
    }

    private void resetState() {

        this.setPositionX(Commons.INICIAR_BOLA_X);
        this.setPositionY(Commons.INICIAR_BOLA_Y);
    }

    public void setXDir(int x) {

        xdir = x;
    }

    public void setYDir(int y) {

        ydir = y;
    }

    public int getYDir() {

        return ydir;
    }

}
