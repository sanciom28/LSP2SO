/*
Series (Procesos): Para efectos de la simulación, sólo debe tomar en cuenta que
cada serie tiene un ID entero único asociado y alguno de los tres niveles de]
prioridad expuestos, cada uno de estos niveles con su propia cola.

Se agruparán bajo las siguientes reglas:

a. Nivel 1: Prioridad alta. Si hay alguna dentro del sistema, se le debe pasar a
la arena de la Inteligencia Artificial sobre los de otra prioridad. Esta prioridad
es para las que tengan una duración de capítulo mayor a los 90 minutos.

b. Nivel 2: Prioridad media. Puede pasar a la arena de la Inteligencia Artificial
siempre y cuando no haya ninguna serie de nivel 1. Esta prioridad es para las
series que tengan una duración de capítulo de entre 60-90 minutos.

c. Nivel 3: Prioridad baja. Puede pasar a la arena de la Inteligencia Artificial
si no hay series de nivel 1 o nivel 2 encolados. Esta prioridad es para las series
que tengan una duración de capítulo menor a los 60 minutos.

Para efectos del refuerzo, todas las series (sin importar su prioridad) son
colocadas en una misma cola y son despachadas de esta de una en una. Por cada vez
ue se actualicen las colas, existe un 40% de probabilidades de que la primera serie
en esta cola salga, y sea colocada en la cola de las series listas para la revisión.

Las series también tienen asociadas un pequeño contador que muestra cuántas series
distintas a ellas ya han pasado por la Inteligencia Artificial. Cuando a la serie
le toca el turno de ser revisada, este contador se pasa a cero. Por otra parte,
cuando el contador llega a ocho, la serie es subida al siguiente nivel de prioridad
y el contador se actualiza a cero nuevamente. Este funcionamiento solo aplica para
series de nivel 2 y nivel 3.

Ejemplo: Si la serie es de nivel 3, esta pasaría a ser de nivel 2.

En cuanto a la asignación de duración, se desea que se haga de manera libre, basada
en los siguientes porcentajes de calidad de las partes de la misma:

a. Intro: 75% de probabilidad de ser de calidad.
b. Inicio: 84% de probabilidad de ser de calidad.
c. Cierre: 80% de probabilidad de ser de calidad.
d. Créditos: 85% de probabilidad de ser de calidad.

(Extra)
Nota: Las características de cada serie serán las indicadas por el enunciado
anterior y sus respectivas series asignadas.
 */
package lsp2so;

import java.util.Random;

/**
 *
 * @author matteosancio
 */
public class Serie {

    String id;
    String type;
    int idHelperVelma = 1;
    int idHelperRM = 1;
    int duration;
    int priority;
    int counterForUppingPriority;
    boolean introQuality = false;
    boolean inicioQuality = false;
    boolean cierreQuality = false;
    boolean creditosQuality = false;
    boolean quality = false;

    public Serie() {

    }

    public void setQuality() {
        Random rand = new Random();

        double introValue = rand.nextDouble();
        double inicioValue = rand.nextDouble();
        double cierreValue = rand.nextDouble();
        double creditosValue = rand.nextDouble();

        //setValues
        if (introValue <= 0.75) {
            introQuality = true;
        }
        if (inicioValue <= 0.84) {
            inicioQuality = true;
        }
        if (cierreValue <= 0.80) {
            cierreQuality = true;
        }
        if (creditosValue <= 0.85) {
            creditosQuality = true;
        }

        if (introQuality && inicioQuality && cierreQuality && creditosQuality) {
            quality = true;
        }

    }

    public void setDurationAndPriority() {
        Random rand = new Random();
        double duration = rand.nextDouble();
        //setDuration
        if (duration < 60) {
            this.duration = (int) duration * 100;
            this.priority = 3;
        } else if (duration > 90) {
            this.duration = (int) duration * 100;
            this.priority = 1;
        } else {
            this.duration = (int) duration * 100;
            this.priority = 2;
        }
    }

    public void setIdAndType() {
        Random rand = new Random();
        double generator = rand.nextDouble();

        if (generator < 0.5) {
            this.type = "VELMA";
            this.id = "V0" + Integer.toString(this.idHelperVelma);
            this.idHelperVelma++;

        } else {
            this.type = "RICK&MORTY";
            this.id = "RM0" + Integer.toString(this.idHelperRM);
            this.idHelperRM++;
        }
    }

    public void uppingCounter() {
        this.counterForUppingPriority++;
    }

    public void resettingCounter() {
        this.counterForUppingPriority = 0;
    }
}
