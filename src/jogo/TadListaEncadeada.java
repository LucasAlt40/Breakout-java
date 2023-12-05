package jogo;

/**
 * A interface `TadListaEncadeada` define as operações básicas que uma lista encadeada
 * deve suportar. Uma lista encadeada é uma estrutura de dados na qual os elementos
 * são encadeados por meio de nós, e cada nó contém um elemento e uma referência ao
 * próximo nó na sequência.
 *
 * @param <T> o tipo dos elementos armazenados na lista encadeada
 */
public interface TadListaEncadeada<T> {

    /**
     * Adiciona um elemento ao final da lista encadeada.
     *
     * @param elemento o elemento a ser adicionado
     */
    void adiciona(T elemento);

    /**
     * Retorna o elemento no índice especificado na lista encadeada.
     *
     * @param index o índice do elemento desejado
     * @return o elemento no índice especificado
     * @throws IndexOutOfBoundsException se o índice estiver fora dos limites da lista
     */
    T getElemento(int index);

    /**
     * Retorna a referência para o primeiro nó na lista encadeada.
     *
     * @return a referência para o primeiro nó na lista encadeada
     */
    No<T> getInicio();

    /**
     * Remove todos os elementos da lista encadeada, deixando-a vazia.
     */
    void limpa();
}
