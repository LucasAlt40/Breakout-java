package jogo;

import javax.swing.ImageIcon;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;
    private int velocidadeBolinha = 2;

    public Ball() {

        initBall();
    }

    private void initBall() {

        xdir = 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    public void updateVelocidadeBolinha(int velocidadeBolinha){
        this.velocidadeBolinha = velocidadeBolinha;
    }

    private void loadImage() {

        var ii = new ImageIcon("src/resources/ball.png");
        this.setImageObject(ii.getImage());
    }

    public void move() {
        this.setPositionX(this.getPositionX() + xdir);
        this.setPositionY(this.getPositionY() + ydir);

        if (this.getPositionX() <= 0) {
            setXDir(velocidadeBolinha);
        }

        if (this.getPositionX() >= Commons.WIDTH - this.getImageWidth()) {
            setXDir(-velocidadeBolinha);
        }

        if (this.getPositionY() <= 0) {
            setYDir(velocidadeBolinha);
        }
    }


    private void resetState() {

        this.setPositionX(Commons.INIT_BALL_X);
        this.setPositionY(Commons.INIT_BALL_Y);
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
