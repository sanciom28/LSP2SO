package lsp2so;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lsp2so.Queue;
import lsp2so.Serie;

/**
 *
 * @author ernes
 */
public class Interfaz extends javax.swing.JFrame {

    int duracionDeDiaEnSegundos = 2;
    public static int idHelperVelma = 0;
    public static int idHelperRM = 0;
    public static int counterForNewSeries = 0;
    int velmaFinalCount;
    int rmFinalCount;

    //Colas
    public static Queue<Serie> q1 = new Queue();
    public static Queue<Serie> q2 = new Queue();
    public static Queue<Serie> q3 = new Queue();
    public static Queue<Serie> qReinforce = new Queue();
    public static Queue<Serie> qFight = new Queue();
    public static Queue<Serie> qWinners = new Queue();

    Administrador tAdmin = new Administrador(duracionDeDiaEnSegundos, q1, q2, q3, qReinforce, qFight);
    Procesador tProcesador = new Procesador(duracionDeDiaEnSegundos);

    //Semaforo MUTEX para controlar el acceso compartido de Administrador y Procesador
    public static Semaphore mutex = new Semaphore(1);

    //Inicializando las variables
    public Interfaz() {
        initComponents();
    }

    public void escribir() {
        try {
            FileWriter myWriter = new FileWriter("results.txt");
            myWriter.write(Administrador.messageQWinners);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leer() {
        String helper = "";
        BufferedReader br;
        try {
            //Lectura
            br = new BufferedReader(new FileReader("results.txt"));
            while (br.ready()) {
                Serie serie = new Serie();
                String data = br.readLine();
//                Administrador.messageQWinners += data;
                String[] dataSplit = data.split("//");
                serie.id = dataSplit[1];
                serie.type = dataSplit[2];
                qWinners.enQueue(serie);
                
                helper = dataSplit[1];
                char helperChar = helper.charAt(0);
                if (helperChar == 'V') {
                    //Tenemos un ganador de Velma
                    velmaFinalCount++;
                } else if (helperChar == 'R') {
                    //Tenemos un ganador de R&M
                    rmFinalCount++;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create10NewSeriesToStart() {
        Serie newSeries1 = new Serie();
        newSeries1.setDataForNewSeries();
        Serie newSeries2 = new Serie();
        newSeries2.setDataForNewSeries();
        Serie newSeries3 = new Serie();
        newSeries3.setDataForNewSeries();
        Serie newSeries4 = new Serie();
        newSeries4.setDataForNewSeries();
        Serie newSeries5 = new Serie();
        newSeries5.setDataForNewSeries();
        Serie newSeries6 = new Serie();
        newSeries6.setDataForNewSeries();
        Serie newSeries7 = new Serie();
        newSeries7.setDataForNewSeries();
        Serie newSeries8 = new Serie();
        newSeries8.setDataForNewSeries();
        Serie newSeries9 = new Serie();
        newSeries9.setDataForNewSeries();
        Serie newSeries10 = new Serie();
        newSeries10.setDataForNewSeries();

        switch (newSeries1.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries1);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries1);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries1);
                break;
            default:
                break;
        }
        switch (newSeries2.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries2);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries2);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries2);
                break;
            default:
                break;
        }
        switch (newSeries3.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries3);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries3);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries3);
                break;
            default:
                break;
        }

        switch (newSeries4.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries4);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries4);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries4);
                break;
            default:
                break;
        }

        switch (newSeries5.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries5);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries5);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries5);
                break;
            default:
                break;
        }

        switch (newSeries6.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries6);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries6);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries6);
                break;
            default:
                break;
        }

        switch (newSeries7.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries7);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries7);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries7);
                break;
            default:
                break;
        }

        switch (newSeries8.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries8);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries8);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries8);
                break;
            default:
                break;
        }

        switch (newSeries9.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries9);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries9);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries9);
                break;
            default:
                break;
        }

        switch (newSeries10.getPriority()) {
            case 1:
                Interfaz.q1.enQueue(newSeries10);
                break;
            case 2:
                Interfaz.q2.enQueue(newSeries10);
                break;
            case 3:
                Interfaz.q3.enQueue(newSeries10);
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        agregarVariable = new javax.swing.JButton();
        iniciarSimulacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        qFightTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        q1TextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        q2TextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        q3TextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        qReinforceTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        processorStatusTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        modificarTiempo = new javax.swing.JButton();
        statisticsButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        actionLogTextArea = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        qWinnersTextArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agregarVariable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        agregarVariable.setText("Agregar Variables");
        agregarVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarVariableActionPerformed(evt);
            }
        });
        jPanel1.add(agregarVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        iniciarSimulacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        iniciarSimulacion.setText("Iniciar simulacion");
        iniciarSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSimulacionActionPerformed(evt);
            }
        });
        jPanel1.add(iniciarSimulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Inteligencia Artificial (Procesador)");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        qFightTextArea.setColumns(20);
        qFightTextArea.setRows(5);
        jScrollPane1.setViewportView(qFightTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 228, 148));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Administrador (Sistema Operativo)");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, -1, -1));

        q1TextArea.setColumns(20);
        q1TextArea.setRows(5);
        jScrollPane2.setViewportView(q1TextArea);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 180, 130));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Cola de Refuerzo");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, -1, -1));

        q2TextArea.setColumns(20);
        q2TextArea.setRows(5);
        jScrollPane3.setViewportView(q2TextArea);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 180, 130));

        q3TextArea.setColumns(20);
        q3TextArea.setRows(5);
        jScrollPane4.setViewportView(q3TextArea);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 180, 130));

        qReinforceTextArea.setColumns(20);
        qReinforceTextArea.setRows(5);
        jScrollPane5.setViewportView(qReinforceTextArea);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 150, 130));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Cola de prioridad #1");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 160, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 0));
        jLabel5.setText("Cola de prioridad #2");
        jLabel5.setOpaque(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 160, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 0));
        jLabel6.setText("Cola de prioridad #3");
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 160, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Status");
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        processorStatusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processorStatusTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(processorStatusTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 110, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Se encuentran batallando");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        modificarTiempo.setText("Modificar Tiempo");
        modificarTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarTiempoActionPerformed(evt);
            }
        });
        jPanel1.add(modificarTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));

        statisticsButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        statisticsButton.setText("¿Cuál es la mejor serie?");
        statisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(statisticsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, 210, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Inteligencia Artificial (Procesador)");
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        actionLogTextArea.setColumns(20);
        actionLogTextArea.setRows(5);
        jScrollPane6.setViewportView(actionLogTextArea);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 50, 270, 260));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/background.png"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 570));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Action Log");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, -1, -1));

        qWinnersTextArea.setColumns(20);
        qWinnersTextArea.setRows(5);
        jScrollPane7.setViewportView(qWinnersTextArea);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 202, 206));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Winners");
        jLabel12.setOpaque(true);
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarSimulacionActionPerformed
        velmaFinalCount = 0;
        rmFinalCount = 0;
        
        tAdmin.setTextAreaActionLog(actionLogTextArea);
        tAdmin.setTextAreaQ1(q1TextArea);
        tAdmin.setTextAreaQ2(q2TextArea);
        tAdmin.setTextAreaQ3(q3TextArea);
        tAdmin.setTextAreaQReinforce(qReinforceTextArea);
        tAdmin.setTextAreaQWinners(qWinnersTextArea);
        tAdmin.setTextAreaQFight(qFightTextArea);
        tAdmin.setTextProcessorStatus(processorStatusTextField);

        tProcesador.setStatusArenaTextArea(qFightTextArea);

        create10NewSeriesToStart();
        
        leer();
        tAdmin.start();
        tProcesador.start();
    }//GEN-LAST:event_iniciarSimulacionActionPerformed

    private void agregarVariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarVariableActionPerformed

    }//GEN-LAST:event_agregarVariableActionPerformed

    private void processorStatusTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processorStatusTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_processorStatusTextFieldActionPerformed

    private void statisticsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsButtonActionPerformed
        if (mutex.availablePermits() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, espere a que el programa finalice");
        } else {
            escribir();
            leer();

            if (velmaFinalCount > rmFinalCount) {
                //Velma gano
                JOptionPane.showMessageDialog(null, "Velma ha ganado con una mayoria de " + velmaFinalCount + " a " + rmFinalCount);
            } else {
                if (velmaFinalCount < rmFinalCount) {
                    //R&M gano
                    JOptionPane.showMessageDialog(null, "R&M ha ganado con una mayoria de " + rmFinalCount + " a " + velmaFinalCount);
                } else {
                    //Empataron
                    JOptionPane.showMessageDialog(null, "Velma y R&M han empatado, HBO puede utilizar cualquiera de las dos");
                }
            }
        }
    }//GEN-LAST:event_statisticsButtonActionPerformed

    private void modificarTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarTiempoActionPerformed
        while (true) {
            String inputSecondsAsDays = JOptionPane.showInputDialog(null, "Tiempo (en segundos) que dura un dia: ");
            int countTemp = 1;
            try {
                countTemp = Integer.parseInt(inputSecondsAsDays);
                if (countTemp < 0) {
                    throw new IllegalArgumentException();
                } else {
                    duracionDeDiaEnSegundos = countTemp;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error, ¡por favor ingrese valores correctos!");
            }
            break;
        }
    }//GEN-LAST:event_modificarTiempoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea actionLogTextArea;
    private javax.swing.JButton agregarVariable;
    private javax.swing.JLabel background;
    private javax.swing.JButton iniciarSimulacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton modificarTiempo;
    private javax.swing.JTextField processorStatusTextField;
    private javax.swing.JTextArea q1TextArea;
    private javax.swing.JTextArea q2TextArea;
    private javax.swing.JTextArea q3TextArea;
    private javax.swing.JTextArea qFightTextArea;
    private javax.swing.JTextArea qReinforceTextArea;
    private javax.swing.JTextArea qWinnersTextArea;
    private javax.swing.JButton statisticsButton;
    // End of variables declaration//GEN-END:variables
}
