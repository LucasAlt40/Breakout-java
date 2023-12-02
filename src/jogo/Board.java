package jogo;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Board extends JPanel implements Commons {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Ball ball2;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;
    private boolean twoBalls = false;
    private Image backgroundImage;
    private Clip backgroundMusic;
    private Clip colisionAudio;
    private int velocidadeBolinha = 1;
    private int blocosDestruidos = 0;
    private Random gerador = new Random();

    public Board() {
        initBoard();
        setFocusable(true);
        requestFocusInWindow();
        loadBackgroundImage();
        loadBackgroundMusic();
        loadAudioColision();
    }

    private void loadGameOverMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/gameover.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(0);
            adjustBackgroundVolume(50.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void loadVictoryMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/victory.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(0); // Toca uma vez
            adjustBackgroundVolume(50.0f); // Definindo o volume para 50%
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void adjustCollisionVolume(float volume) {
        if (colisionAudio != null && colisionAudio.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) colisionAudio.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    private void loadAudioColision() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/colisao.wav"));
            colisionAudio = AudioSystem.getClip();
            colisionAudio.open(audioInputStream);
            adjustCollisionVolume(40.0f); // Definindo o volume para 70%
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playAudioColision() {
        if (colisionAudio != null) {
            colisionAudio.setFramePosition(0); // Reinicia o áudio para o início
            colisionAudio.start(); // Inicia a reprodução
        }
    }

    private void adjustBackgroundVolume(float volume) {
        if (backgroundMusic != null && backgroundMusic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    private void loadBackgroundMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/musica.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Reproduz a música em loop
            adjustBackgroundVolume(30.0f); // Definindo o volume para 50%
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBoard() {
        bricks = new Brick[N_DE_BLOCOS];
        ball = new Ball(N_DA_BOLA);
        // Instanciamente Bola 2

        ball2 = new Ball(N_DA_BOLA + 1);

        ball.updateVelocidadeBolinha(velocidadeBolinha);
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < N_DE_LINHAS; i++) {
            for (int j = 0; j < N_DE_COLUNAS; j++) {
                bricks[k] = new Brick(j * 50 + 30, i * 21 + 50);
                k++;
            }
        }

        timer = new Timer(VELOCIDADEGERAL, new GameCycle());
        timer.start();

        setFocusable(true);
        setPreferredSize(new Dimension(Commons.LARGURA, Commons.ALTURA));
        addKeyListener(new TAdapter());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImageObject(), ball.getPositionX(), ball.getPositionY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        // Representação visual da bola 2
        if (twoBalls) {
            g2d.drawImage(ball2.getImageObject(), ball2.getPositionX(), ball2.getPositionY(),
                    ball2.getImageWidth(), ball2.getImageHeight(), this);

        }

        g2d.drawImage(paddle.getImageObject(), paddle.getPositionX(), paddle.getPositionY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < N_DE_BLOCOS; i++) {
            if (!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImageObject(), bricks[i].getPositionX(),
                        bricks[i].getPositionY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        var font = new Font("Verdana", Font.BOLD, 42);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message, (Commons.LARGURA - fontMetrics.stringWidth(message)) / 2, Commons.ALTURA - 200);
        if (message.equals("Game Over")) {
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            loadGameOverMusic();

        } else {
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            loadVictoryMusic();

        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkCollision(ball);
        // Colisão da bola 2
        if (twoBalls && ball2 != null) {
            checkCollision(ball2);
            ball2.move();
        }
        repaint();
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
    }

    private void checkCollision(Ball ball) {

        if (this.ball.getRect().getMaxY() > BORDA_INFERIOR) {
            inGame = false;
            timer.stop();
            message = "Game Over";
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            loadGameOverMusic();
        }

        for (int i = 0, j = 0; i < N_DE_BLOCOS; i++) {

            if (bricks[i].isDestroyed()) {

                j++;

            }

            if (j == N_DE_BLOCOS) {

                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-velocidadeBolinha);
                ball.setYDir(-velocidadeBolinha);

            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-velocidadeBolinha);
                ball.setYDir(-velocidadeBolinha * ball.getYDir());

            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-velocidadeBolinha);

            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(velocidadeBolinha);
                ball.setYDir(-velocidadeBolinha * ball.getYDir());

            }

            if (ballLPos > fourth) {

                ball.setXDir(velocidadeBolinha);
                ball.setYDir(-velocidadeBolinha);

            }
        }

        for (int i = 0; i < N_DE_BLOCOS; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    playAudioColision();

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setXDir(-velocidadeBolinha);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(velocidadeBolinha);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(velocidadeBolinha);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-velocidadeBolinha);
                    }

                    bricks[i].setDestroyed(true);
                    blocosDestruidos++;
                    aleatorio();
                }

                if (blocosDestruidos == 8) {

                    if (velocidadeBolinha < VELOCIDADEMAXBOLA) {
                        velocidadeBolinha++;
                    }
                    System.out.println("Velocidade da bolinha: " + velocidadeBolinha);
                    ball.updateVelocidadeBolinha(velocidadeBolinha);
                    blocosDestruidos = 0;

                }

            }
        }

    }

    private void aleatorio(){
        if (gerador.nextInt(3) + 1 == 1) {
            gerarBuffs();
        }
    }

    private void gerarBuffs() {


        switch (gerador.nextInt(4) + 1) {
            case 1:
                paddle.setVelocidadePaddle(10);

                break;
            case 2:
                paddle.setVelocidadePaddle(5);


                break;
            case 3:
                twoBalls = true;


                break;
            case 4:

                paddle.initPaddle(gerador.nextInt(2)+1);

            default:
                break;
        }

    }

}
