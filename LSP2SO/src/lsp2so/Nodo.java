
package lsp2so;

public class Nodo<T> {

    //Campos de la clase
    private T info;
    private Nodo pNext;

    /**
     *
     * @return info de tipo T
     */
    public T getInfo() {
        return info;
    }

    /**
     *
     * @param info
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     *
     * @return Apuntador al siguiente nodo de tipo Nodo
     */
    public Nodo getpNext() {
        return pNext;
    }

    /**
     *
     * @param pNext
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
}
