/*
Administrador (Sistema Operativo): Se encarga de actualizar las colas del sistema
y de dictarle a la Inteligencia Artificial cuál de las siguientes series se debe
evisar.

Cada dos ciclos de revisión (cada cuatro series revisadas o dos pares de series),
el administrador agrega un nueva serie a la cola de su nivel correspondiente. Este
evento ocurre con una probabilidad del 70%. Se enfatiza que el Administrador opera
cada vez que la Inteligencia Artificial acaba de terminar de revisar una serie,
realiza toda la gestión descrita respecto a las colas y finalmente le indica a la
Inteligencia Artificial que puede empezar a trabajar otra vez con las series
seleccionadas.
 */
package lsp2so;

import lsp2so.Queue;

/**
 *
 * @author matteosancio
 */
public class Administrador extends Thread {

    int duracionDiaEnSegundos = 1;
    int cycleCount; //TODO: agregar que cada 4 series revisadas el administrador agrega una nueva serie a la cola de su nivel correspondiente

    public Administrador(int tiempo, Queue q1, Queue q2, Queue q3, Queue toFight) {
        this.duracionDiaEnSegundos = tiempo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Serie serie = new Serie();

                sleep(duracionDiaEnSegundos * 1000);
                //Se adquiere permisos en el mutex
                Interfaz.mutex.acquire();
                //Se entra en la sección critica y se actualizan las colas

                //Para q1
                serie = (Serie) Interfaz.q1.dequeue();

                //Para q2
                serie = (Serie) Interfaz.q2.dequeue();
                if (serie.quality) {
                    //Si la calidad es buena entonces se aumenta su contador de revision en 1 y la volvemos a encolar, cuando este contador llegue a 8 se sube la prioridad
                    if (serie.counterForUppingPriority == 8) {
                        //Se sube de prioridad
                        Interfaz.q1.enqueue(serie);
                    } else {
                        //Se sube el contador
                        serie.uppingCounter();
                        Interfaz.q2.enqueue(serie);
                    }
                } else {
                    //Si la calidad no es buena entonces se manda al reforzamiento
                    Interfaz.qReinforce.enqueue(serie);
                }
                //Para q3

                //Para reforzamiento
            }
        } catch (Exception e) {
        }
    }

    public void newSeries() {
        //Codigo para crear una neuva serie dependiendo del cycleCount, 70% de probabilidad de que esto ocurra
    }

}
