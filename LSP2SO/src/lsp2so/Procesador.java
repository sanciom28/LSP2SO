/*
Inteligencia Artificial (Procesador): Su función es recibir las series, procesar
sus características y determinar cuál de las dos debería salir al mercado. Con
el fin de poder evaluar cada disputa entre series, con la rigurosidad que amerita,
se tomará el terminal de cédula más alto de cada desarrollador + el terminal de
cédula más bajo de cada desarrollador + 10 segundos para decidir.

Puede ocurrir lo siguiente luego de cada disputa:

a. Determinar un ganador del combate de series: Lanza al mercado la ganadora y
la perdedora se desecha.

b. Llegar a un empate del combate de series: Se deberán volver a encolar las series
para que posteriormente vuelvan a batallar. No necesariamente deben batallar entre
ellas mismas después.

c. Determinar que no puede haber un ganador: Ocurre cuando la Inteligencia Artificial
determina que no puede haber una ganadora por la falta de calidad de ambas series.

Se deberán enviar ambas series a una cola de refuerzo, en sus respectivas plantas,
desde las cuales pueden pasar a batallar directamente.

La propuesta de los Project Managers (PMs) fue llevar a cabo una batalla entre los
personajes principales de la serie Rick & Morty para decidir aleatoriamente quién
sería la serie ganadora de la disputa.

Inicialmente, se les pedirá a las series que compitan con las siguientes prioridades
aleatorias:

a. 40% de salir alguna al mercado.
b. 27% de empatar.
c. 33% de requerir refuerzo.

Debido al talento demostrado por los desarrolladores en el proyecto anterior, se
decidió permitirles el poder añadir la aleatoriedad con la que cual se decide que
serie saldrá al mercado.

Como sugerencia se les da la opción de agregar variables de la serie Rick & Morty
para competir, sin perder aleatoriedad, ni perder nunca los porcentajes iniciales
de ganar alguno o de empatar. Esta libertad les podría representar un aumento de
su salario en un 10% (Extra).
 */
package lsp2so;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import lsp2so.Administrador;

/**
 *
 * @author matteosancio
 */
public class Procesador extends Thread {

    int tiempo = (4 + 1 + 10) * 1000;

     
    @Override
    public void run() {
        Serie serie1 = new Serie();
        Serie serie2 = new Serie();

        if (Interfaz.qFight.queueSize() >= 2) {
            Random rand = new Random();
            try {
                //Se adquiere permisos en el mutex
                Interfaz.mutex.acquire();

                serie1 = (Serie) Interfaz.qFight.dequeue();
                serie2 = (Serie) Interfaz.qFight.dequeue();

                //15 segundos mientras las series "batallan"
                Thread.sleep(tiempo);

                //a decidir el ganador!!
                //A
                double p = rand.nextDouble();
                if (p <= 0.4) {
                    //alguna de las dos series saldrá al mercado
                    if (p <= 0.2) {
                        Administrador.message += serie1.id + "---" + "(Winner!) qFight -> qWinners/n";
                        Interfaz.qWinners.enqueue(serie1);
                    } else {
                        Interfaz.qWinners.enqueue(serie2);
                        Administrador.message += serie2.id + "---" + "(Winner!) qFight -> qWinners/n";
                    }
                } else if (p <= 0.67) {
                    //B
                    //Se empata, se devuelven a qFight
                    Administrador.message += serie1.id + serie2.id + "---" + "(Tie!) qFight -> qFight/n";
                    serie1.setPriority(0);
                    serie2.setPriority(0);
                    Interfaz.qFight.enqueue(serie1);
                    Interfaz.qFight.enqueue(serie2);
                } else {
                    //Por falta de calidad, se devuelven a qReinforce
                    //C
                    Administrador.message += serie1.id + serie2.id + "---" + "(Low Quality!) qFight -> qReinforce/n";
                    serie1.setPriority(0);
                    serie2.setPriority(0);
                    Interfaz.qReinforce.enqueue(serie1);
                }
                //Suelta el mutex
                Interfaz.mutex.release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            Administrador.message += "(Failed to battle)/n";
        }

    }

}
