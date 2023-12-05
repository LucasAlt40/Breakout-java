package jogo;

/**
 * A classe `ListaEncadeada` implementa a interface `TadListaEncadeada`,
 * representando uma lista encadeada simples que armazena elementos do tipo T.
 *
 * @param <T> o tipo dos elementos armazenados na lista
 */
public class ListaEncadeada<T> implements TadListaEncadeada<T> {

    // Referência para o primeiro nó na lista
    private No<T> inicio;

    // Referência para o último nó na lista
    private No<T> ultimo;

    // O tamanho da lista encadeada
    private int tamanho = 0;

    /**
     * Adiciona um elemento ao final da lista encadeada.
     *
     * @param elemento o elemento a ser adicionado
     */
    @Override
    public void adiciona(T elemento) {
        // Cria um novo nó com o elemento fornecido
        No<T> celula = new No<T>(elemento);

        // Se a lista estiver vazia, define o novo nó como o primeiro nó
        if (this.tamanho == 0) {
            this.inicio = celula;
        } else {
            // Caso contrário, define o novo nó como o próximo nó do último nó
            this.ultimo.setProximo(celula);
        }

        // Atualiza a referência do último nó para o novo nó
        this.ultimo = celula;

        // Incrementa o tamanho da lista
        this.tamanho++;
    }

    /**
     * Retorna o tamanho da lista encadeada.
     *
     * @return o tamanho da lista encadeada
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * Retorna o elemento no índice especificado na lista encadeada.
     *
     * @param index o índice do elemento desejado
     * @return o elemento no índice especificado
     * @throws IndexOutOfBoundsException se o índice estiver fora dos limites da lista
     */
    @Override
    public T getElemento(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice " + index + " fora dos limites");
        }

        No<T> atual = inicio;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }

        return atual.getElemento();
    }

    /**
     * Retorna a referência para o início da lista encadeada.
     *
     * @return a referência para o início da lista encadeada
     */
    @Override
    public No<T> getInicio() {
        return inicio;
    }

    /**
     * Limpa todos os elementos da lista encadeada, tornando-a vazia.
     */
    @Override
    public void limpa() {
        for (No<T> atual = this.inicio; atual != null;) {
            No<T> proximo = atual.getProximo();
            atual.setElemento(null);
            atual.setProximo(null);
            atual = proximo;
        }

        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    /**
     * Retorna uma representação em formato de string da lista encadeada.
     *
     * @return uma string representando a lista encadeada
     */
    @Override
    public String toString() {
        if (this.tamanho == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");

        No<T> atual = this.inicio;
        for (int i = 0; i < this.tamanho - 1; i++) {
            builder.append(atual.getElemento()).append(",");
            atual = atual.getProximo();
        }
        builder.append(atual.getElemento()).append("]");

        return builder.toString();
    }

}
