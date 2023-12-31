package jogo;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * A classe `Board` representa o painel principal do jogo Breakout, que estende
 * `JPanel`.
 * É responsável por gerenciar a lógica do jogo, desenhar objetos na tela e
 * lidar com eventos do teclado.
 */
public class Board extends JPanel implements Commons {

    // Timer para controlar o ciclo do jogo
    private Timer timer;

    // Mensagem exibida ao final do jogo
    private String message = "Game Over";

    // Objetos do jogo
    private Ball ball;
    private Ball ball2;
    private Paddle paddle;

    // Estado do jogo
    private boolean inGame = true;

    // Indica se há duas bolas em jogo
    private boolean twoBalls = false;

    // Imagem de fundo
    private Image backgroundImage;

    // Objetos de áudio
    private Musica backgroundMusica;
    private Musica gameoverMusica;
    private Musica victoryMusica;
    private Clip colisionAudio;

    // Velocidade inicial da bola
    private int velocidadeBolinha = 1;

    // Contadores de jogo
    private int blocosDestruidos = 0;
    private int pontos = 0;
    // Referência ao JFrame principal
    private JFrame frame;

    
    // Gerador de números aleatórios
    private Random gerador = new Random();

    // Lista encadeada de tijolos
    private ListaEncadeada<Brick> bricksList;

    /**
     * Construtor da classe `Board`. Inicializa objetos e configurações do jogo.
     *
     * @param frame Referência ao JFrame principal
     */
    public Board(JFrame frame) {
        this.backgroundMusica = new Musica();
        this.gameoverMusica = new Musica();
        this.victoryMusica = new Musica();
        this.frame = frame;
        initBoard();
        setFocusable(true);
        requestFocusInWindow();
        carregaMusica("src/resources/musica.wav", this.backgroundMusica);
        loadBackgroundImage();
        loadAudioColision();
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

        // Desenha a pontuação na tela
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Verdana", Font.BOLD, 24));
        String pontuacao = "Pontuação: " + pontos;
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int larguraTexto = fontMetrics.stringWidth(pontuacao);
        g2d.drawString(pontuacao, (Commons.LARGURA - larguraTexto) / 2, 40);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Carrega um arquivo de áudio e configura a música associada.
     *
     * @param caminho Caminho do arquivo de áudio
     * @param musica  Objeto Musica para armazenar o Clip de áudio
     */
    private void carregaMusica(String caminho, Musica musica) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminho));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(0);
            ajustaVolume(clip, 50.0f);
            musica.setMusica(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajusta o volume do Clip de áudio.
     *
     * @param clip   Clip de áudio
     * @param volume Volume desejado (0 a 100)
     */
    private void ajustaVolume(Clip clip, float volume) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    /**
     * Ajusta o volume do áudio de colisão.
     *
     * @param volume Volume desejado (0 a 100)
     */
    private void adjustCollisionVolume(float volume) {
        if (colisionAudio != null && colisionAudio.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) colisionAudio.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    /**
     * Carrega o áudio de colisão do arquivo.
     */
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

    /**
     * Reproduz o áudio de colisão.
     */
    private void playAudioColision() {
        if (colisionAudio != null) {
            colisionAudio.setFramePosition(0); // Reinicia o áudio para o início
            colisionAudio.start(); // Inicia a reprodução
        }
    }

    /**
     * Carrega a imagem de fundo do arquivo.
     */
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa objetos e configurações do jogo.
     */
    private void initBoard() {
        bricksList = new ListaEncadeada<>();
        ball = new Ball(N_DA_BOLA);
        ball2 = new Ball(N_DA_BOLA + 1);

        ball.updateVelocidadeBolinha(velocidadeBolinha);
        paddle = new Paddle();

        for (int i = 0; i < N_DE_LINHAS; i++) {
            for (int j = 0; j < N_DE_COLUNAS; j++) {
                bricksList.adiciona(new Brick(j * 50 + 30, i * 21 + 50));
            }
        }

        timer = new Timer(VELOCIDADEGERAL, new GameCycle());
        timer.start();

        setFocusable(true);
        setPreferredSize(new Dimension(Commons.LARGURA, Commons.ALTURA));
        addKeyListener(new TAdapter());
    }

    /**
     * Desenha os objetos do jogo na tela.
     *
     * @param g2d Contexto gráfico 2D
     */
    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImageObject(), ball.getPositionX(), ball.getPositionY(), ball.getImageWidth(),
                ball.getImageHeight(), this);

        if (twoBalls) {
            g2d.drawImage(ball2.getImageObject(), ball2.getPositionX(), ball2.getPositionY(), ball2.getImageWidth(),
                    ball2.getImageHeight(), this);

        }

        g2d.drawImage(paddle.getImageObject(), paddle.getPositionX(), paddle.getPositionY(), paddle.getImageWidth(),
                paddle.getImageHeight(), this);

        No<Brick> currentBrick = bricksList.getInicio();
        while (currentBrick != null) {
            Brick current = currentBrick.getElemento();
            if (!current.isDestroyed()) {
                g2d.drawImage(current.getImageObject(), current.getPositionX(), current.getPositionY(),
                        current.getImageWidth(), current.getImageHeight(), this);
            }
            currentBrick = currentBrick.getProximo();
        }
    }

    /**
     * Exibe a mensagem de fim de jogo na tela.
     *
     * @param g2d Contexto gráfico 2D
     */
    private void gameFinished(Graphics2D g2d) {
        var font = new Font("Verdana", Font.BOLD, 42);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message, (Commons.LARGURA - fontMetrics.stringWidth(message)) / 2, Commons.ALTURA - 200);
        if (message.equals("Game Over")) {
            if (backgroundMusica != null) {
                backgroundMusica.getMusica().stop();
            }
            carregaMusica("src/resources/gameover.wav", gameoverMusica);
        } else {
            if (backgroundMusica != null) {
                backgroundMusica.getMusica().stop();
            }
            carregaMusica("src/resources/victory.wav", victoryMusica);

        }
    }

    /**
     * Manipulador de eventos de teclado.
     */
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
            if (!inGame) {
                ((Breakout) frame).startGame();
            }

        }
    }

    /**
     * Ciclo principal do jogo, controlado pelo Timer.
     */
    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    /**
     * Executa o ciclo do jogo, atualizando a posição dos objetos.
     */
    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkCollision(ball);
        if (twoBalls && ball2 != null) {
            checkCollision(ball2);
            ball2.move();
        }
        repaint();
    }

    /**
     * Encerra o jogo.
     */
    private void stopGame() {
        inGame = false;
        timer.stop();
    }

    /**
     * Verifica as colisões e atualiza o estado do jogo.
     *
     * @param ball Bola para verificar colisões
     */
    private void checkCollision(Ball ball) {
        if (this.ball.getRect().getMaxY() > BORDA_INFERIOR) {
            inGame = false;
            timer.stop();
            message = "Game Over";
            if (backgroundMusica.getMusica() != null) {
                backgroundMusica.getMusica().stop();
            }
            carregaMusica("src/resources/gameover.wav", gameoverMusica);
        }

        int j = 0;
        for (int i = 0; i < bricksList.getTamanho(); i++) {
            Brick currentBrick = bricksList.getElemento(i);
            if (currentBrick != null && currentBrick.isDestroyed()) {
                j++;
            }
        }

        if (j == bricksList.getTamanho()) {
            message = "Victory";
            stopGame();
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

        for (int i = 0; i < bricksList.getTamanho(); i++) {
            Brick currentBrick = bricksList.getElemento(i);
            if (currentBrick != null && !currentBrick.isDestroyed()
                    && ball.getRect().intersects(currentBrick.getRect())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!currentBrick.isDestroyed()) {
                    playAudioColision();
                    pontos += 10;

                    if (currentBrick.getRect().contains(pointRight)) {
                        ball.setXDir(-velocidadeBolinha);
                    } else if (currentBrick.getRect().contains(pointLeft)) {
                        ball.setXDir(velocidadeBolinha);
                    }

                    if (currentBrick.getRect().contains(pointTop)) {
                        ball.setYDir(velocidadeBolinha);
                    } else if (currentBrick.getRect().contains(pointBottom)) {
                        ball.setYDir(-velocidadeBolinha);
                    }

                    currentBrick.setDestroyed(true);
                    blocosDestruidos++;
                    aleatorio();

                    if (blocosDestruidos == 4) {
                        if (velocidadeBolinha < VELOCIDADEMAXBOLA) {
                            velocidadeBolinha++;
                        }
                        ball.updateVelocidadeBolinha(velocidadeBolinha);
                        blocosDestruidos = 0;
                    }
                }
            }
        }
    }

    /**
     * Gera efeitos aleatórios no jogo, como buffs.
     */
    private void aleatorio() {
        if (gerador.nextInt(3) + 1 == 1) {
            gerarBuffs();
        }
    }

    /**
     * Gera buffs aleatórios com base em um número aleatório.
     */
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
                paddle.initPaddle(gerador.nextInt(2) + 1);
            default:
                break;
        }

    }

}
