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
    static String message = "";
    static String messageQ1 = "";
    static String messageQ2 = "";
    static String messageQ3 = "";
    static String messageQReinforce = "";
    static String messageQWinners = "";
    static String messageQFight = "";

    int counterForMessage = 1;
    JTextArea textAreaActionLog;
    JTextArea textAreaQ1;
    JTextArea textAreaQ2;
    JTextArea textAreaQ3;
    JTextArea textAreaQReinforce;
    JTextArea textAreaQWinners;
    JTextArea textAreaQFight;

    public Administrador(int tiempo, Queue q1, Queue q2, Queue q3, Queue qReinforce, Queue toFight) {
        this.duracionDiaEnSegundos = tiempo;
    }

    public void updatingQueueMessages() {
        //Funcion que actualiza todos los counters de cada serie. Se utiliza cuando el Administrador empieza a trabajar

        //Actualizando q1
        if (!Interfaz.q1.isEmpty() || !Interfaz.q2.isEmpty() || !Interfaz.q3.isEmpty()) {
            Serie serie = new Serie();
            Serie serieAux = new Serie();
            Nodo aux = new Nodo();
            Nodo frontAux = new Nodo();

            messageQ1 = "";
            messageQ2 = "";
            messageQ3 = "";
            messageQReinforce = "";
            messageQWinners = "";
            messageQFight = "";

            aux = Interfaz.q1.getFront();
            frontAux = Interfaz.q1.getRear();

            if (!Interfaz.q1.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();

                    messageQ1 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ1.setText(messageQ1);
                    aux = aux.getpNext();
                }
            }

            //Actualizando q2
            aux = Interfaz.q2.getFront();
            frontAux = Interfaz.q2.getRear();
            if (!Interfaz.q2.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();

                    messageQ2 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ2.setText(messageQ2);
                    aux = aux.getpNext();
                }
            }

            //Actualizando q3
            aux = Interfaz.q3.getFront();
            frontAux = Interfaz.q3.getRear();
            if (!Interfaz.q3.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();

                    messageQ3 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ3.setText(messageQ3);
                    aux = aux.getpNext();
                }
            }

            //Actualizando qReinforce
            aux = Interfaz.qReinforce.getFront();
            frontAux = Interfaz.qReinforce.getRear();
            if (!Interfaz.qReinforce.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();

                    messageQReinforce += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQReinforce.setText(messageQReinforce);
                    aux = aux.getpNext();
                }
            }

//            //Actualizando qFight
//            if (!Interfaz.qFight.isEmpty()) {
//                aux = Interfaz.qFight.getFront();
//                frontAux = Interfaz.qFight.getRear();
//                for (int i = 0; i < 3; i++) {
//                    serie = (Serie) aux.getInfo();
//                    serieAux = (Serie) aux.getpNext().getInfo();
//                    messageQFight += serie.id + "---" + "Is fighting" + "---" + serieAux.id + "\n";
//                    aux = aux.getpNext();
//                }
//                textAreaQFight.setText(messageQFight);
//
//            }
            //Escribiendo los winners en el WinnersTextArea
            aux = Interfaz.qWinners.getFront();
            frontAux = Interfaz.qWinners.getRear();
            if (!Interfaz.qWinners.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    messageQWinners += serie.id + "//" + serie.type + "//" + serie.priority + "\n";
                    textAreaQWinners.setText(messageQWinners);
                    aux = aux.getpNext();
                }
            }

        }
    }

    public void uppingCountersAndUpdatingQueuesTextForEachNode() {
        //Funcion que actualiza todos los counters de cada serie. Se utiliza cuando el Administrador empieza a trabajar

        //Actualizando q1
        if (!Interfaz.q1.isEmpty() || !Interfaz.q2.isEmpty() || !Interfaz.q3.isEmpty()) {
            Serie serie = new Serie();
            Nodo aux = new Nodo();
            Nodo frontAux = new Nodo();

            aux = Interfaz.q1.getFront();
            frontAux = Interfaz.q1.getRear();
            messageQ1 = "";
            messageQ2 = "";
            messageQ3 = "";
            messageQReinforce = "";
            messageQWinners = "";
            messageQFight = "";

            if (!Interfaz.q1.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    serie.uppingCounter();
                    messageQ1 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ1.setText(messageQ1);
                    aux = aux.getpNext();
                }
            }

            //Actualizando q2
            aux = Interfaz.q2.getFront();
            frontAux = Interfaz.q2.getRear();
            if (!Interfaz.q2.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    serie.uppingCounter();
                    messageQ2 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ2.setText(messageQ2);
                    aux = aux.getpNext();
                }
            }

            //Actualizando q3
            aux = Interfaz.q3.getFront();
            frontAux = Interfaz.q3.getRear();
            if (!Interfaz.q3.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    serie.uppingCounter();
                    messageQ3 += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQ3.setText(messageQ3);
                    aux = aux.getpNext();
                }
            }

            //Actualizando qReinforce
            aux = Interfaz.qReinforce.getFront();
            frontAux = Interfaz.qReinforce.getRear();
            if (!Interfaz.qReinforce.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    serie.uppingCounter();
                    messageQReinforce += serie.id + "---" + serie.type + "---" + "q" + serie.priority + "\n";
                    textAreaQReinforce.setText(messageQReinforce);
                    aux = aux.getpNext();
                }
            }

            //Escribiendo los winners en el WinnersTextArea
            aux = Interfaz.qWinners.getFront();
            frontAux = Interfaz.qWinners.getRear();
            if (!Interfaz.qWinners.isEmpty()) {
                while (aux != frontAux.getpNext()) {
                    serie = (Serie) aux.getInfo();
                    messageQWinners += serie.id + "//" + serie.type + "//" + serie.priority + "\n";
                    textAreaQWinners.setText(messageQWinners);
                    aux = aux.getpNext();
                }
            }
        } else {
        }
    }

    public void checkingQ1() {
        Serie serie = new Serie();
        if (!Interfaz.q1.isEmpty()) {
            //La serie pasa a ser revisada, se resetea su contador, y se coloca como prioridad 0
            serie = (Serie) Interfaz.q1.dequeue();
            serie.resettingCounter();
            serie.setPriority(0);
            message += serie.id + "---" + "(Fight!) q1 -> qFight\n";
            Interfaz.qFight.enqueue(serie);
//            updatingQueueMessages();
        }
    }

    public void checkingQ2() {
        if (!Interfaz.q2.isEmpty()) {
            Serie serie = new Serie();
            serie = Interfaz.q2.dequeue();
            if (!Interfaz.q1.isEmpty()) {
                //No hay posibilidades de Preempt
                //Se chequea si su contador es 8, si lo es, se sube su prioridad y se resetea el contador
                if (serie.counterForUppingPriority == 8) {
                    //Se sube de prioridad
                    serie.setPriority(1);
                    serie.resettingCounter();
                    message += serie.id + "---" + "(UppingPriority) q2 -> q1\n";
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
                message += serie.id + "---" + "(Preempt) q2 -> Fight!\n";
                Interfaz.qFight.enqueue(serie);
            }
//            updatingQueueMessages();
        }
    }

    public void checkingQ3() {
        if (!Interfaz.q3.isEmpty()) {
            Serie serie = new Serie();
            serie = Interfaz.q3.dequeue();
            if (!Interfaz.q1.isEmpty() || !Interfaz.q2.isEmpty()) {
                //No hay posibilidades de Preempt
                if (serie.counterForUppingPriority == 8) {
                    //Se sube de prioridad
                    serie.setPriority(2);
                    serie.resettingCounter();
                    message += serie.id + "---" + "(UppingPriority) q3 -> q2\n";
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
//            updatingQueueMessages();
        }
    }

    public void checkingQReinforce() {
        Serie serie = new Serie();
        Random generator = new Random();
        //Para reforzamiento: se saca una serie de la cola y se reinicia la calidad. Luego tiene 40% de chances de que salga directamente a la cola lista para revision (q1)
        if (!Interfaz.qReinforce.isEmpty()) {
            //Si hay elementos en Reinforce
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
                        message += serie.id + "---" + "(Reinforce) qReinforce -> qFight\n";
                        Interfaz.qFight.enqueue(serie);
                        break;
                    case 1:
                        message += serie.id + "---" + "(Reinforce) qReinforce -> q1\n";
                        Interfaz.q1.enqueue(serie);
                        break;
                    case 2:
                        message += serie.id + "---" + "(Reinforce) qReinforce -> q2\n";
                        Interfaz.q2.enqueue(serie);
                        break;
                    case 3:
                        message += serie.id + "---" + "(Reinforce) qReinforce -> q3\n";
                        Interfaz.q3.enqueue(serie);
                        break;
                    default:
                        break;
                }
            }
//            updatingQueueMessages();
        }
    }

    public void checkingForNewSeries() {
        Serie newSeries = new Serie();
        if (Interfaz.counterForNewSeries % 2 == 0 && Interfaz.counterForNewSeries > 0) {
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
                    case 0:
                        Interfaz.qFight.enqueue(newSeries);
                        break;
                    default:
                        break;

                }
                message += newSeries.id + "---" + "(Creation)  -> q" + newSeries.getPriority() + "\n";
            }

        }
//        updatingQueueMessages();
    }

    public void updateActionLog() {
        //TODO para que se actualice hay que utilizar setJTextArea(actionLog) en la interfaz
        textAreaActionLog.setText(message);
    }

    public JTextArea getTextAreaQFight() {
        return textAreaQFight;
    }

    public void setTextAreaQFight(JTextArea textAreaQFight) {
        this.textAreaQFight = textAreaQFight;
    }

    public void setTextAreaActionLog(JTextArea textAreaActionLog) {
        this.textAreaActionLog = textAreaActionLog;
    }

    public JTextArea getTextAreaQWinners() {
        return textAreaQWinners;
    }

    public void setTextAreaQWinners(JTextArea textAreaQWinners) {
        this.textAreaQWinners = textAreaQWinners;
    }

    public JTextArea getTextAreaQ1() {
        return textAreaQ1;
    }

    public void setTextAreaQ1(JTextArea textAreaQ1) {
        this.textAreaQ1 = textAreaQ1;
    }

    public JTextArea getTextAreaQ2() {
        return textAreaQ2;
    }

    public void setTextAreaQ2(JTextArea textAreaQ2) {
        this.textAreaQ2 = textAreaQ2;
    }

    public JTextArea getTextAreaQ3() {
        return textAreaQ3;
    }

    public void setTextAreaQ3(JTextArea textAreaQ3) {
        this.textAreaQ3 = textAreaQ3;
    }

    public JTextArea getTextAreaQReinforce() {
        return textAreaQReinforce;
    }

    public void setTextAreaQReinforce(JTextArea textAreaQReinforce) {
        this.textAreaQReinforce = textAreaQReinforce;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Serie newSeries = new Serie();

                Thread.sleep(duracionDiaEnSegundos * 1000);
                //Se adquiere permisos en el mutex
                Interfaz.mutex.acquire();
                //Se entra en la sección critica
                message += "-------" + counterForMessage + "-------\n";
                //Se actualizan los contadores de todas las series almacenadas en todas las colas
                uppingCountersAndUpdatingQueuesTextForEachNode();

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

                //Actualizamos el action log y las interfaces de las colas
                updateActionLog();
//                updatingQueueMessages();

                //Suelta el mutex
                Interfaz.mutex.release();
                counterForMessage++;
            }
        } catch (Exception e) {
        }
    }

}
