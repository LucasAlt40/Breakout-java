package jogo;

public interface Commons {

    int N_OF_COLUMNS = 12;
    int N_OF_ROWS = 5;
    int N_OF_BRICKS = N_OF_COLUMNS * N_OF_ROWS;
    int WIDTH = N_OF_COLUMNS * 50 + 55;
    int HEIGHT = 400;
    int BOTTOM_EDGE = HEIGHT;

    int INIT_PADDLE_X = WIDTH / 2;
    int INIT_PADDLE_Y = HEIGHT - 30;
    int INIT_BALL_X = WIDTH / 2;
    int INIT_BALL_Y = 355;    
    int PERIOD = 1;
    int velocidadePaddle = 5;
    int velocidadeBolinha = 5;
}
