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

/**
 *
 * @author matteosancio
 */
public class Procesador extends Thread{
    
    int tiempo = (4 + 1 + 10) * 1000;
    
    public void queEmpieceLaNhaza() {
            
        Random rand = new Random();
        Serie s1 = Interfaz.qFight.dequeue();
        Serie s2 = Interfaz.qFight.dequeue();
            
        try {
            //15 segundos mientras las series "batallan"
            Thread.sleep(tiempo);
            //a decidir el ganador!!
            double p = rand.nextDouble();
            
            if (p < 0.4) {
                //alguna de las dos series saldrá al mercado
                if (p < 0.2) {
                    //TODO: escribir en el txt la serie 1
                    Interfaz.q1.enqueue(s2);
                } else {
                    //TODO: escribir en el txt la serie 2
                    Interfaz.q1.enqueue(s1);
                }
            } else if (p < 0.67) {
                //las series empatan, se encolan donde estaban
                Interfaz.q1.enqueue(s1);
                Interfaz.q1.enqueue(s2);
            } else {
                //ambas son mi**da, de vuelta a refuerzo
                Interfaz.qReinforce.enqueue(s1);
                Interfaz.qReinforce.enqueue(s2);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
