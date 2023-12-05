package jogo;

import javax.swing.*;
import java.awt.*;

/**
 * A classe `Breakout` representa o jogo Breakout, que estende JFrame.
 * Inclui métodos para inicialização da interface do usuário, início do jogo e execução do programa.
 */
public class Breakout extends JFrame {

    // Referência para o painel de menu
    private Menu menu;

    // Referência para o painel principal do jogo
    private Board board;

    /**
     * Construtor da classe `Breakout`. Inicializa a interface do usuário.
     */
    public Breakout() {
        initUI();
    }

    /**
     * Inicializa a interface do usuário do jogo, configurando o layout, o menu e as propriedades do JFrame.
     */
    private void initUI() {
        setLayout(new BorderLayout());

        // Inicializa o painel de menu e passa a referência do JFrame
        menu = new Menu(this);
        add(menu);

        // Configurações gerais do JFrame
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Inicia o jogo, substituindo o painel de menu pelo painel principal do jogo (Board).
     */
    public void startGame() {
        // Inicializa o painel principal do jogo
        board = new Board(this);

        // Atualiza a interface do usuário para exibir o painel do jogo
        SwingUtilities.invokeLater(() -> {
            remove(menu);
            add(board, BorderLayout.CENTER);
            revalidate();
            board.requestFocusInWindow();
        });
    }

    /**
     * Método principal que inicia o programa. Cria uma instância de `Breakout` e inicializa a interface do usuário.
     *
     * @param args argumentos da linha de comando (não utilizados neste contexto)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria e inicia o jogo Breakout
            Breakout game = new Breakout();
            game.initUI();
        });
    }
}
