package jogo;

public interface Commons {

    int N_DE_COLUNAS = 12;
    int N_DE_LINHAS = 5;
    int N_DE_BLOCOS = N_DE_COLUNAS * N_DE_LINHAS;
    int LARGURA = N_DE_COLUNAS * 50 + 55;
    int ALTURA = 400;
    int BORDA_INFERIOR = ALTURA;

    int INICIAR_PADDLE_X = LARGURA / 2;
    int INICIAR_PADDLE_Y = ALTURA - 45;
    int INICIAR_BOLA_X = LARGURA / 2;
    int INICIAR_BOLA_Y = 355;
    int VELOCIDADEGERAL = 10;


    int VELOCIDADEMAXBOLA = 3;
    int PADDLEINICIAL = 1;
    int PADDLEMEDIO = 2;
    int N_DA_BOLA = 1;


}
