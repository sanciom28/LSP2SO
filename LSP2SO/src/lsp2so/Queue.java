/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lsp2so;

public class Queue<T> {

    // Campos de la clase
    private Nodo<T> front;
    private Nodo<T> rear;
    private int size;

    /**
     * Constructor de la clase
     */
    public Queue() {

        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     *
     * @return de tipo booleano para saber si la cola esta vacia
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Encola el elemento suministrado por el usuario de tipo T
     *
     * @param x
     */
    public void enQueue(T x) {

        Nodo<T> node = new Nodo<>();
        node.setInfo(x);
        if (isEmpty()) {
            front = node;
        } else {
            Nodo<T> aux = front;
            while (aux != rear) {
                aux = aux.getpNext();
            }
            rear.setpNext(node);

        }
        rear = node;
        size++;

    }

    /**
     * Se desencola el primer elemento en ingresar a la cola
     *
     * @return tipo de dato T
     */
    public T deQueue() {
        if (!isEmpty()) {
            Nodo<T> aux = front;
            front = front.getpNext();
            if (front == null) {
                rear = null;
            }
            size--;
            return aux.getInfo();
        } else {
            //Queue is empty
        }

        return null;
    }

    /**
     *
     * @return
     */
    public int queueSize() {
        return size;
    }

    public Nodo<T> getFront() {
        return front;
    }

    public void setFront(Nodo<T> front) {
        this.front = front;
    }

    public Nodo<T> getRear() {
        return rear;
    }

    public void setRear(Nodo<T> rear) {
        this.rear = rear;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
