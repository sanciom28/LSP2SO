package lsp2so;

import java.util.Random;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lsp2so.Administrador;
import lsp2so.Interfaz;

/**
 *
 * @author matteosancio
 */
public class Procesador extends Thread {

    int duracionDiaEnSegundos;
    int tiempo = 1000 * (4 + 1 + 10) * duracionDiaEnSegundos;
    JTextField statusTextField;
    JTextArea statusArenaTextArea;

    public JTextArea getStatusArenaTextArea() {
        return statusArenaTextArea;
    }

    public void setStatusArenaTextArea(JTextArea statusArenaTextArea) {
        this.statusArenaTextArea = statusArenaTextArea;
    }

    public Procesador(int duracionDiaEnSegundos) {
        this.duracionDiaEnSegundos = duracionDiaEnSegundos;
    }

    public JTextField getStatusTextField() {
        return statusTextField;
    }

    public void setStatusTextField(JTextField textField) {
        this.statusTextField = textField;
    }

    @Override
    public void run() {
        Serie serie1 = new Serie();
        Serie serie2 = new Serie();
        String working = "WORKING";
        String idle = "IDLE";
        while (true) {
            try {
                Interfaz.mutex.acquire();
                if (Interfaz.qFight.queueSize() >= 2) {
                    Random rand = new Random();

                    serie1 = (Serie) Interfaz.qFight.deQueue();
                    serie2 = (Serie) Interfaz.qFight.deQueue();

                    //15 segundos mientras las series "batallan"
                    statusTextField.setText(working);
                    System.out.println("working");
                    Thread.sleep(tiempo);

                    //Se colocan en el arenaTextArea las series que pelean
                    String message = serie1.id + "---" + serie1.type + "\n" + "-------------VS-------------" + "\n" + serie2.id + "---" + serie2.type + "\n";

                    statusArenaTextArea.setText(message);

                    //a decidir el ganador!!
                    //A
                    double p = rand.nextDouble();
                    if (p <= 0.4) {
                        //alguna de las dos series saldrá al mercado
                        Administrador.counterForWinners++;
                        if (p <= 0.2) {
                            Administrador.message += serie1.id + "---" + "(Winner!) qFight -> qWinners\n";
                            Interfaz.qWinners.enQueue(serie1);
                        } else {
                            Interfaz.qWinners.enQueue(serie2);
                            Administrador.message += serie2.id + "---" + "(Winner!) qFight -> qWinners\n";
                        }
                    } else {
                        if (p > 0.4 && p <= 0.67) {
                            //B
                            //Se empata, se devuelven a qFight
                            Administrador.message += serie1.id + serie2.id + "---" + "(Tie!) qFight -> qFight\n";
                            serie1.setPriority(0);
                            serie2.setPriority(0);
                            Interfaz.qFight.enQueue(serie1);
                            Interfaz.qFight.enQueue(serie2);

                        } else {
                            //Por falta de calidad, se devuelven a qReinforce
                            //C
                            Administrador.message += serie1.id + serie2.id + "---" + "(Low Quality!) qFight -> qReinforce\n";
                            serie1.setPriority(0);
                            serie2.setPriority(0);
                            Interfaz.qReinforce.enQueue(serie1);
                            Interfaz.qReinforce.enQueue(serie2);

                        }
                    }
                    //Suelta el mutex
                    statusTextField.setText(idle);
                    Interfaz.counterForNewSeries++;
                    Interfaz.mutex.release();

                } else {
                    statusTextField.setText(idle);
                    Interfaz.mutex.release();

                }
            } catch (Exception e) {
            }

        }

    }

}
