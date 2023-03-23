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
        jScrollPane1 = new javax.swing.JScrollPane();
        qFightTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        q1TextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        q2TextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        q3TextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        qReinforceTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        processorStatusTextField = new javax.swing.JTextField();
        modificarTiempo = new javax.swing.JButton();
        statisticsButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        actionLogTextArea = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        qWinnersTextArea = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        flamitas = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agregarVariable.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        agregarVariable.setForeground(new java.awt.Color(204, 204, 204));
        agregarVariable.setText("CREAR SERIE");
        agregarVariable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarVariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarVariableActionPerformed(evt);
            }
        });
        jPanel1.add(agregarVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, 50));

        iniciarSimulacion.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        iniciarSimulacion.setForeground(new java.awt.Color(255, 255, 255));
        iniciarSimulacion.setText("INICIAR");
        iniciarSimulacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iniciarSimulacion.setRolloverEnabled(true);
        iniciarSimulacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/Fight 21.png"))); // NOI18N
        iniciarSimulacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iniciarSimulacionMouseEntered(evt);
            }
        });
        iniciarSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSimulacionActionPerformed(evt);
            }
        });
        jPanel1.add(iniciarSimulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 190, 60));

        qFightTextArea.setBackground(new java.awt.Color(0, 0, 0));
        qFightTextArea.setColumns(20);
        qFightTextArea.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        qFightTextArea.setForeground(new java.awt.Color(255, 255, 255));
        qFightTextArea.setRows(5);
        jScrollPane1.setViewportView(qFightTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 290, 170));

        q1TextArea.setBackground(new java.awt.Color(0, 0, 0));
        q1TextArea.setColumns(20);
        q1TextArea.setForeground(new java.awt.Color(204, 255, 255));
        q1TextArea.setRows(5);
        jScrollPane2.setViewportView(q1TextArea);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 250, 130));

        q2TextArea.setBackground(new java.awt.Color(0, 0, 0));
        q2TextArea.setColumns(20);
        q2TextArea.setForeground(new java.awt.Color(204, 255, 204));
        q2TextArea.setRows(5);
        jScrollPane3.setViewportView(q2TextArea);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 250, 130));

        q3TextArea.setBackground(new java.awt.Color(0, 0, 0));
        q3TextArea.setColumns(20);
        q3TextArea.setForeground(new java.awt.Color(255, 204, 255));
        q3TextArea.setRows(5);
        jScrollPane4.setViewportView(q3TextArea);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 250, 130));

        qReinforceTextArea.setBackground(new java.awt.Color(0, 0, 0));
        qReinforceTextArea.setColumns(20);
        qReinforceTextArea.setForeground(new java.awt.Color(255, 153, 153));
        qReinforceTextArea.setRows(5);
        jScrollPane5.setViewportView(qReinforceTextArea);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 550, 250, 130));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("CPU STATUS:");
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, -1, -1));

        processorStatusTextField.setBackground(new java.awt.Color(0, 0, 0));
        processorStatusTextField.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        processorStatusTextField.setForeground(new java.awt.Color(255, 255, 255));
        processorStatusTextField.setBorder(null);
        processorStatusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processorStatusTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(processorStatusTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 120, 20));

        modificarTiempo.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        modificarTiempo.setForeground(new java.awt.Color(204, 204, 204));
        modificarTiempo.setText("AJUSTES");
        modificarTiempo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarTiempoActionPerformed(evt);
            }
        });
        jPanel1.add(modificarTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 150, 50));

        statisticsButton.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        statisticsButton.setForeground(new java.awt.Color(204, 204, 204));
        statisticsButton.setText("ESTADISTICAS");
        statisticsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(statisticsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 150, 50));

        actionLogTextArea.setBackground(new java.awt.Color(0, 0, 0));
        actionLogTextArea.setColumns(20);
        actionLogTextArea.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        actionLogTextArea.setForeground(java.awt.SystemColor.desktop);
        actionLogTextArea.setRows(5);
        actionLogTextArea.setBorder(null);
        actionLogTextArea.setCaretColor(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(actionLogTextArea);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 230, 610));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 153, 153));
        jLabel11.setText("REF");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 600, 50, -1));

        qWinnersTextArea.setBackground(new java.awt.Color(0, 0, 0));
        qWinnersTextArea.setColumns(20);
        qWinnersTextArea.setForeground(new java.awt.Color(255, 255, 51));
        qWinnersTextArea.setRows(5);
        qWinnersTextArea.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                qWinnersTextAreaComponentAdded(evt);
            }
        });
        jScrollPane7.setViewportView(qWinnersTextArea);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 260, 310));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/HBOMaxPlusLogo.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 309, -1));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 54)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BATTLE SIMULATOR");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMixingCutoutShape(null);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 440, 140));
        jLabel1.getAccessibleContext().setAccessibleName("VELMA VS. RICK AND MORTY BATTLE SIMULATOR");

        jLabel13.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("VELMA VS. RICK AND MORTY");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setMixingCutoutShape(null);
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, -20, 440, 140));

        flamitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/flames_on_a_transparent_background__by_prussiaart_das4dm5-fullview.png"))); // NOI18N
        flamitas.setPreferredSize(new java.awt.Dimension(311, 160));
        jPanel1.add(flamitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, -30, 300, 220));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("LOG:");
        jLabel14.setOpaque(true);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 50, -1));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("GANADORES:");
        jLabel12.setOpaque(true);
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 170, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("BATALLANDO:");
        jLabel15.setOpaque(true);
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 170, -1));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("COLAS:");
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 90, -1));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 255, 255));
        jLabel17.setText("1");
        jLabel17.setOpaque(true);
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 20, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 255, 204));
        jLabel18.setText("2");
        jLabel18.setOpaque(true);
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 20, -1));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 255));
        jLabel19.setText("3");
        jLabel19.setOpaque(true);
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 20, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        String nombre = JOptionPane.showInputDialog(null, "Desea crear una serie Velma o Rick & Morty?: ");
        Serie s = new Serie();
        if (!"Velma".equals(nombre) && !"Rick & Morty".equals(nombre)) {
            JOptionPane.showMessageDialog(null, "Por favor coloque el nombre de la serie deseada.");
        } else {
            if ("Velma".equals(nombre)) {
                s.setDataForNewSeriesVelma();
                JOptionPane.showMessageDialog(null, "Velma creada.");

            } else if ("Rick & Morty".equals(nombre)) {
                s.setDataForNewSeriesRM();
                JOptionPane.showMessageDialog(null, "Rick & Morty creada.");
            }
            switch (s.getPriority()) {
                case 1:
                    Interfaz.q1.enQueue(s);
                    break;
                case 2:
                    Interfaz.q2.enQueue(s);
                    break;
                case 3:
                    Interfaz.q3.enQueue(s);
                    break;
                default:
                    break;
            }
            //custom

        }

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
            int inputShaggy;
            int inputSummer;
            String inputSecondsAsDays = JOptionPane.showInputDialog(null, "Tiempo (en segundos) que dura un dia: ");
            int countTemp;
            try {
                countTemp = Integer.parseInt(inputSecondsAsDays);
                if (countTemp < 0) {
                    throw new IllegalArgumentException();

                } else {
                    duracionDeDiaEnSegundos = countTemp;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error, ¡por favor ingrese valores correctos!");
                break;
            }

            try {
                inputShaggy = Integer.parseInt(JOptionPane.showInputDialog(null, "Quieres ingresar a Shaggy? (1) para si o (2) para no. Shaggy hace que todas las series nuevas del tipo Velma vayan a la cola de prioridad"));
                inputSummer = Integer.parseInt(JOptionPane.showInputDialog(null, "Quieres ingresar a Summer? (1) para si o (2) para no. Summer hace que todas las series nuevas del tipo R&M sean de calidad asegurada"));
                if (inputShaggy == 1) {
                    Serie.shaggy = true;
                    JOptionPane.showMessageDialog(null, "Listo, Shaggy mandado");
                }
                if (inputSummer == 1) {
                    Serie.summer = true;
                    JOptionPane.showMessageDialog(null, "Listo, Summer mandado");
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error, ¡por favor ingrese valores correctos!");
                break;
            }
        }


    }//GEN-LAST:event_modificarTiempoActionPerformed

    private void iniciarSimulacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarSimulacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_iniciarSimulacionMouseEntered

    private void qWinnersTextAreaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_qWinnersTextAreaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_qWinnersTextAreaComponentAdded

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
    private javax.swing.JLabel flamitas;
    private javax.swing.JButton iniciarSimulacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
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
