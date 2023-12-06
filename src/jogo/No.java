package jogo;

/**
 * A classe `No` representa um nó em uma estrutura de lista encadeada. Cada nó contém
 * um elemento do tipo genérico T e uma referência ao próximo nó na lista.
 *
 * @param <T> o tipo dos elementos armazenados no nó
 */
public class No<T> {

    // O elemento armazenado no nó
    private T elemento;

    // Referência para o próximo nó na lista
    private No<T> proximo;

    /**
     * Construtor que cria um nó com o elemento fornecido, sem referência ao próximo nó.
     *
     * @param elemento o elemento a ser armazenado no nó
     */
    public No(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    /**
     * Construtor que cria um nó com o elemento e a referência ao próximo nó fornecidos.
     *
     * @param elemento o elemento a ser armazenado no nó
     * @param proximo o próximo nó na lista
     */
    public No(T elemento, No<T> proximo) {
        this.elemento = elemento;
        this.proximo = proximo;
    }

    /**
     * Obtém o elemento armazenado no nó.
     *
     * @return o elemento armazenado no nó
     */
    public T getElemento() {
        return elemento;
    }

    /**
     * Define o elemento a ser armazenado no nó.
     *
     * @param elemento o novo elemento a ser armazenado no nó
     */
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Obtém a referência para o próximo nó na lista.
     *
     * @return a referência para o próximo nó na lista
     */
    public No<T> getProximo() {
        return proximo;
    }

    /**
     * Define a referência para o próximo nó na lista.
     *
     * @param proximo o novo próximo nó na lista
     */
    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    /**
     * Retorna uma representação em formato de string do nó.
     *
     * @return uma string representando o nó
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("No [elemento=").append(elemento).append(", proximo=").append(proximo).append("]");
        return builder.toString();
    }
}
