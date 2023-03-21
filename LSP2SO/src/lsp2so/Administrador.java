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

import java.util.Random;
import javax.swing.JTextArea;
import lsp2so.Queue;

/**
 *
 * @author matteosancio
 */
public class Administrador extends Thread {
    
    int duracionDiaEnSegundos = 1;
    int cycleCount; //TODO: agregar que cada 4 series revisadas el administrador agrega una nueva serie a la cola de su nivel correspondiente
    static String message;
    int counterForMessage = 1;
    int counterForNewSeries;
    JTextArea textArea;
    
    public Administrador(int tiempo, Queue q1, Queue q2, Queue q3, Queue qReinforce, Queue toFight) {
        this.duracionDiaEnSegundos = tiempo;
    }
    
    public void uppingCountersForEachNode() {
        //Funcion que actualiza todos los counters de cada serie. Se utiliza cuando el Administrador empieza a trabajar

        //Actualizando q1
        Serie serie = new Serie();
        Nodo aux = new Nodo();
        Nodo lastNode = new Nodo();
        aux = Interfaz.q1.getFront();
        lastNode = Interfaz.q2.getRear();
        
        if (!Interfaz.q1.isEmpty()) {
            while (aux != lastNode) {
                serie = (Serie) aux.getInfo();
                serie.uppingCounter();
                aux = aux.getpNext();
            }
        }

        //Actualizando q2
        aux = Interfaz.q2.getFront();
        lastNode = Interfaz.q2.getRear();
        if (!Interfaz.q2.isEmpty()) {
            while (aux != lastNode) {
                serie = (Serie) aux.getInfo();
                serie.uppingCounter();
                aux = aux.getpNext();
            }
        }

        //Actualizando q3
        aux = Interfaz.q3.getFront();
        lastNode = Interfaz.q3.getRear();
        if (!Interfaz.q3.isEmpty()) {
            while (aux != lastNode) {
                serie = (Serie) aux.getInfo();
                serie.uppingCounter();
                aux = aux.getpNext();
            }
        }

        //Actualizando qReinforce
        aux = Interfaz.qReinforce.getFront();
        lastNode = Interfaz.qReinforce.getRear();
        if (!Interfaz.qReinforce.isEmpty()) {
            while (aux != lastNode) {
                serie = (Serie) aux.getInfo();
                serie.uppingCounter();
                aux = aux.getpNext();
            }
        }
        
    }
    
    public void checkingQ1() {
        Serie serie = new Serie();
        if (!Interfaz.q1.isEmpty()) {
            //La serie pasa a ser revisada, se resetea su contador, y se coloca como prioridad 0
            serie = (Serie) Interfaz.q1.dequeue();
            serie.resettingCounter();
            serie.setPriority(0);
            message += serie.id + "---" + "(Fight!) q1 -> qFight/n";
            Interfaz.qFight.enqueue(serie);
        }
    }
    
    public void checkingQ2() {
        Serie serie = new Serie();
        serie = Interfaz.q2.dequeue();
        if (!Interfaz.q1.isEmpty()) {
            //No hay posibilidades de Preempt
            //Se chequea si su contador es 8, si lo es, se sube su prioridad y se resetea el contador
            if (serie.counterForUppingPriority == 8) {
                //Se sube de prioridad
                serie.setPriority(1);
                serie.resettingCounter();
                message += serie.id + "---" + "(UppingPriority) q2 -> q1/n";
                Interfaz.q1.enqueue(serie);
                
            } else {
                //Se sube el contador y se vuelve a encolar
                serie.uppingCounter();
                Interfaz.q2.enqueue(serie);
            }
        } else {
            //Existe preempt
            serie.setPriority(0);
            serie.resettingCounter();
            message += serie.id + "---" + "(Preempt) q2 -> Fight!/n";
            Interfaz.qFight.enqueue(serie);
        }
        
    }
    
    public void checkingQ3() {
        Serie serie = new Serie();
        serie = Interfaz.q3.dequeue();
        if (!Interfaz.q1.isEmpty() && !Interfaz.q2.isEmpty()) {
            //No hay posibilidades de Preempt
            if (serie.counterForUppingPriority == 8) {
                //Se sube de prioridad
                serie.setPriority(2);
                serie.resettingCounter();
                message += serie.id + "---" + "(UppingPriority) q3 -> q2";
                Interfaz.q2.enqueue(serie);
                
            } else {
                //Se sube el contador y se vuelve a encolar
                serie.uppingCounter();
                Interfaz.q3.enqueue(serie);
            }
            
        } else {
            //Preempt
            serie.setPriority(0);
            serie.resettingCounter();
            Interfaz.qFight.enqueue(serie);
            
        }
        
    }
    
    public void checkingQReinforce() {
        Serie serie = new Serie();
        Random generator = new Random();
        //Para reforzamiento: se saca una serie de la cola y se reinicia la calidad. Luego tiene 40% de chances de que salga directamente a la cola lista para revision (q1)
        serie = Interfaz.qReinforce.dequeue();
        serie.setQuality();
        //si la serie ahora sí es de calidad:
        double rand = generator.nextDouble();
        
        if (rand <= 0.40) {
            //Va para q1
            serie.setPriority(1);
            Interfaz.q1.enqueue(serie);
        } else {
            //Regresa a su cola de prioridad previa
            switch (serie.getPriority()) {
                case 0:
                    message += serie.id + "---" + "(Reinforce) qReinforce -> qFight/n";
                    Interfaz.qFight.enqueue(serie);
                    break;
                case 1:
                    message += serie.id + "---" + "(Reinforce) qReinforce -> q1/n";
                    Interfaz.q1.enqueue(serie);
                    break;
                case 2:
                    message += serie.id + "---" + "(Reinforce) qReinforce -> q2/n";
                    Interfaz.q2.enqueue(serie);
                    break;
                case 3:
                    message += serie.id + "---" + "(Reinforce) qReinforce -> q3/n";
                    Interfaz.q3.enqueue(serie);
                    break;
                default:
                    break;
            }
        }
        
    }
    
    public void checkingForNewSeries() {
        //TODO: HAY QUE ACTUALIZAR EL COUNTERFORNEWSERIES EN LA INTELIGENCIA ARTIFICIAL, CADA 2 COMBATES SE DEBE DE CREAR UNA NUEVA SERIE, EL PROCESADOR SE ENCARGA DE ESTO ACTUALIZAR EL CONTADOR, EL ADMIN SOLO DE CREAR LA SERIE
        Serie newSeries = new Serie();
        if (counterForNewSeries == 4) {
            Random rand = new Random();
            
            if (rand.nextDouble() <= 0.70) {
                newSeries.setDataForNewSeries();
                switch (newSeries.getPriority()) {
                    case 1:
                        Interfaz.q1.enqueue(newSeries);
                        break;
                    case 2:
                        Interfaz.q2.enqueue(newSeries);
                        break;
                    case 3:
                        Interfaz.q3.enqueue(newSeries);
                        break;
                    default:
                        break;
                    
                }
                message += newSeries.id + "---" + "(Creation)  -> q" + newSeries.getPriority() + "/n";
            }
            
        }
    }
    
    public void updateActionLog() {
        //TODO para que se actualice hay que utilizar setJTextArea(actionLog) en la interfaz
        textArea.setText(message);
    }
    
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Serie newSeries = new Serie();
                
                sleep(duracionDiaEnSegundos * 1000);
                //Se adquiere permisos en el mutex
                Interfaz.mutex.acquire();
                //Se entra en la sección critica
                message = "-------" + counterForMessage + "-------";
                //Se actualizan los contadores de todas las series almacenadas en todas las colas
                uppingCountersForEachNode();

                //Para q1
                checkingQ1();

                //Para q2
                checkingQ2();

                //Para q3
                checkingQ3();

                //Para qReinforce
                checkingQReinforce();

                //Chequeamos si hay que generar una nueva serie
                checkingForNewSeries();

                //Actualizamos el action log
                updateActionLog();

                //Suelta el mutex
                Interfaz.mutex.release();
                counterForMessage++;
            }
        } catch (Exception e) {
        }
    }
    
}
