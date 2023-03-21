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
     *
     * @param valor
     * @return de tipo booleano para saber si un elemento se encuentra o no en
     * la cola
     */
    public boolean existe(T valor) {
        Nodo<T> aux = front;
        while (aux != null) {
            if (aux.getInfo() == valor) {
                return true;
            }
            aux = aux.getpNext();
        }

        return false;
    }

    /**
     * Encola el elemento suministrado por el usuario de tipo T
     *
     * @param x
     */
    public void enqueue(T x) {

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
     * Imnprime los elementos de la cola
     */
    public void printQueue() {
        if (!isEmpty()) {
            Nodo<T> temp = front;
            while (temp != null) {
                System.out.print(temp.getInfo() + "->");
                temp = temp.getpNext();
            }
            System.out.println("null");
        } else {
            System.out.println("Queue is empty.");
        }
    }

    /**
     *
     * @return
     */
    public int queueSize() {
        return size;
    }

    /**
     * Se desencola el primer elemento en ingresar a la cola
     *
     * @return tipo de dato T
     */
    public T dequeue() {
        if (!isEmpty()) {
            Nodo<T> aux = front;
            front = front.getpNext();
            if (front == null) {
                rear = null;
            }
            size--;
            return aux.getInfo();
        } else {
            System.out.println("queue is empty.");
        }

        return null;
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
