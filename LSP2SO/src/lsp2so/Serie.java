package lsp2so;

import java.util.Random;

/**
 *
 * @author matteosancio
 */
public class Serie {

    String id;
    String type;
    int duration;
    int priority;
    int counterForUppingPriority;
    boolean introQuality = false;
    boolean inicioQuality = false;
    boolean cierreQuality = false;
    boolean creditosQuality = false;
    boolean quality = false;
    static boolean shaggy = false;
    static boolean summer = false;

    public Serie() {

    }

    public void setQuality() {

        Random rand = new Random();

        if (!introQuality) {
            double introValue = rand.nextDouble();
            if (introValue <= 0.75) {
                introQuality = true;
            }
        }

        if (!inicioQuality) {
            double inicioValue = rand.nextDouble();
            if (inicioValue <= 0.84) {
                inicioQuality = true;
            }
        }

        if (!cierreQuality) {
            double cierreValue = rand.nextDouble();
            if (cierreValue <= 0.8) {
                cierreQuality = true;
            }
        }

        if (!creditosQuality) {
            double creditosValue = rand.nextDouble();
            if (creditosValue <= 0.85) {
                creditosQuality = true;
            }
        }

        if (introQuality && inicioQuality && cierreQuality && creditosQuality) {
            quality = true;
        }

    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDurationAndPriority() {
        Random rand = new Random();
        double duration = rand.nextDouble();
        //Se coloca la prioridad dependiendo de la duracion
        if (duration <= 0.33) {
            this.duration = (int) duration * 100;
            this.priority = 3;
        } else if (duration >= 0.67) {
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
            Interfaz.idHelperVelma++;
            this.id = "V" + Integer.toString(Interfaz.idHelperVelma);

        } else {
            this.type = "RICK&MORTY";
            Interfaz.idHelperRM++;
            this.id = "R" + Integer.toString(Interfaz.idHelperRM);
        }
    }

    public void setType(int n) {

        if (n < 0.5) {
            this.type = "VELMA";
            Interfaz.idHelperVelma++;
            this.id = "V" + Integer.toString(Interfaz.idHelperVelma);

        } else {
            this.type = "RICK&MORTY";
            Interfaz.idHelperRM++;
            this.id = "R" + Integer.toString(Interfaz.idHelperRM);
        }
    }

    public void setDataForNewSeries() {
        setDurationAndPriority();
        setIdAndType();
        setQuality();
        
        if (this.shaggy && "VELMA".equals(this.type)) {
            setDataForNewSeriesVelma();
        }
        else if (this.summer && "RICK&MORTY".equals(this.type)) {
            setDataForNewSeriesRM();
        }

    }

    public void setDataForNewSeriesVelma() {
        //Si agregas a shaggy entonces todas las nuevas series van a la cola de prioridad 1
        this.priority = 1;
        this.type = "VELMA";
        setQuality();
    }

    public void setDataForNewSeriesRM() {
        //Si agregas a Summer entonces todas las nuevas series son de calidads
        setDurationAndPriority();
        this.type = "RICK & MORTY";
        this.quality = true;
    }

    public void uppingCounter() {
        if (counterForUppingPriority < 8) {
            this.counterForUppingPriority++;
        }
    }

    public void resettingCounter() {
        this.counterForUppingPriority = 0;
    }
}
