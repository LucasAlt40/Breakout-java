package jogo;

import javax.sound.sampled.Clip;

/**
 * A classe `Musica` representa um objeto de música no contexto do jogo.
 * Ela possui um objeto Clip que representa a música a ser reproduzida.
 */
public class Musica {

    // Objeto Clip que representa a música
    private Clip musica;

    /**
     * Obtém o objeto Clip associado a esta instância de Musica.
     *
     * @return o objeto Clip da música
     */
    public Clip getMusica() {
        return musica;
    }

    /**
     * Define o objeto Clip para esta instância de Musica.
     *
     * @param musica o objeto Clip representando a música
     */
    public void setMusica(Clip musica) {
        this.musica = musica;
    }
}
