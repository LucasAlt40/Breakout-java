package jogo;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;

    public Brick(int x, int y) {
        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) {

        this.setPositionX(x);
        this.setPositionY(y);

        destroyed = false;

        loadImage();
        getImageDimensions();
    }
    
    private void loadImage() {
        
        var ii = new ImageIcon("src/resources/brick.png");
        this.setImageObject(ii.getImage());
    }

    public boolean isDestroyed() {
        
        return destroyed;
    }

    public void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}
