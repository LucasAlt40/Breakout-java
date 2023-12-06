package jogo;

/**
 * A interface `Commons` define constantes comuns utilizadas no jogo Breakout.
 */
public interface Commons {

    /**
     * Número de colunas na matriz de blocos.
     */
    int N_DE_COLUNAS = 12;

    /**
     * Número de linhas na matriz de blocos.
     */
    int N_DE_LINHAS = 10;

    /**
     * Número total de blocos no jogo.
     */
    int N_DE_BLOCOS = N_DE_COLUNAS * N_DE_LINHAS;

    /**
     * Largura total da tela do jogo.
     */
    int LARGURA = N_DE_COLUNAS * 50 + 55;

    /**
     * Altura total da tela do jogo.
     */
    int ALTURA = 600;

    /**
     * Borda inferior da tela estendida para melhor visualização do jogo.
     */
    int BORDA_INFERIOR = ALTURA + 100;

    /**
     * Coordenadas iniciais da raquete (Paddle).
     */
    int INICIAR_PADDLE_X = LARGURA / 2;
    int INICIAR_PADDLE_Y = ALTURA - 45;

    /**
     * Coordenadas iniciais da bola.
     */
    int INICIAR_BOLA_X = LARGURA / 2;
    int INICIAR_BOLA_Y = 450;

    /**
     * Velocidade geral do jogo.
     */
    int VELOCIDADEGERAL = 10;

    /**
     * Velocidade máxima da bola.
     */
    int VELOCIDADEMAXBOLA = 4;

    /**
     * Tipos de paddle (raquete).
     */
    int PADDLEINICIAL = 1;
    int PADDLEMEDIO = 2;

    /**
     * Número da bola (usado para distinguir múltiplas bolas).
     */
    int N_DA_BOLA = 1;
}
