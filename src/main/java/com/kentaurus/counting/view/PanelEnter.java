package com.kentaurus.counting.view;

import com.kentaurus.counting.constants.CommonConstants;
import com.kentaurus.counting.controller.CountingController;
import com.kentaurus.counting.model.Table;
import com.kentaurus.counting.util.PropertiesCache;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

/**
 * Clase que representa el panel donde se configura la muestra.
 *
 * @author Cristhian Castillo.
 */
public class PanelEnter extends JPanel implements ActionListener, ChangeListener {

    /**
     * Versión de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Etiqueta numero inicial.
     */
    private JLabel lblEnterNumber;

    /**
     * Etiqueta numero final.
     */
    private JLabel lblEndNumber;

    /**
     * Etiqueta numero de rondas.
     */
    private JLabel lblRoundsGame;

    /**
     * Etiqueta numero de cifras.
     */
    private JLabel lblCountNumber;

    /**
     * Etiqueta seleccionar archivo.
     */
    private JLabel lblFile;

    /**
     * Etiqueta temperatura.
     */
    private JLabel lblTempeture;

    /**
     * Etiqueta iteraciones con diferente temperatura.
     */
    private JLabel lblIteDifTempeture;

    /**
     * Etiqueta iteraciones con la misma temperatura.
     */
    private JLabel lblIteSemtempeture;

    /**
     * Etiqueta constante para disminuir la temperatura.
     */
    private JLabel lblConstant;

    /**
     * Etiqueta para idicar el algoritmo.
     */
    private JLabel lblSimulate;

    /**
     * Campo de texto para la temperatura.
     */
    private JTextField txtTempeture;

    /**
     * Campo de texto para el numero de iteraciones con temperatura diferente.
     */
    private JTextField txtIteDifTempeture;

    /**
     * Campo de texto para el numero de iteraciones con temperatura igual.
     */
    private JTextField txtIteSemTempeture;

    /**
     * Campo de texto para la constante de disminución de temperatura.
     */
    private JTextField txtConstant;

    /**
     * Campo de texto numero inicial.
     */
    private JTextField txtEnterNumber;

    /**
     * Campo de texto numero final.
     */
    private JTextField txtEndNumber;

    /**
     * Campo de texto numero de rondas.
     */
    private JTextField txtRoundsGame;

    /**
     * Campo de texto cantidad de cifras.
     */
    private JTextField txtCountNumber;

    /**
     * Campo de texto ruta del archivo.
     */
    private JTextField txtFile;

    /**
     * Boton para seleccionar el archivo.
     */
    private JButton btnSelectFile;

    /**
     * Boton para ejecytar la muestra.
     */
    private JButton btnExecute;

    /**
     * Check para validar si se debe realizar una simulacion.
     */
    private JCheckBox checkSimulation;

    /**
     * Nombre del archivo.
     */
    private String fileName;

    /**
     * Controlador de la aplicación.
     */
    private CountingController ctrl;

    /**
     * Construye el panel para parametrizar la muestra.
     *
     * @param ctrl Controlador de la aplicación.
     * @throws ParseException
     */
    public PanelEnter(CountingController ctrl) throws ParseException {
        this.ctrl = ctrl;
        this.setBorder(new TitledBorder(PropertiesCache.TITLE_PANEL_ENTER));
        this.setLayout(new BorderLayout());

        JPanel pnlNorthLeft = new JPanel();
        GroupLayout groupNorthLeft = new GroupLayout(pnlNorthLeft);
        pnlNorthLeft.setLayout(groupNorthLeft);

        JPanel pnlNorthRight = new JPanel();
        pnlNorthRight.setBorder(new TitledBorder(PropertiesCache.TITLE_PANEL_SIMULATED));
        GroupLayout groupNorthRight = new GroupLayout(pnlNorthRight);
        pnlNorthRight.setLayout(groupNorthRight);

        this.lblEnterNumber = new JLabel(PropertiesCache.LABEL_ENTER_NUMBER);
        this.lblEndNumber = new JLabel(PropertiesCache.LABEL_END_NUMBER);
        this.lblRoundsGame = new JLabel(PropertiesCache.LABEL_ROUNDS_GAME);
        this.lblCountNumber = new JLabel(PropertiesCache.LABEL_COUNT_NUMBER);

        this.lblTempeture = new JLabel(PropertiesCache.LABEL_TEMPERATURE);
        this.lblIteDifTempeture = new JLabel(PropertiesCache.LABEL_ITE_DIFF_TEMPERATURE);
        this.lblIteSemtempeture = new JLabel(PropertiesCache.LABEL_ITE_EQUALS_TEMPERATURE);
        this.lblConstant = new JLabel(PropertiesCache.LABEL_CONSTANT);
        this.lblSimulate = new JLabel(PropertiesCache.LABEL_SIMULATE);

        this.lblFile = new JLabel(PropertiesCache.LABEL_FILE);

        this.txtEnterNumber = new JTextField("0");
        this.txtEnterNumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtEnterNumber.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtEndNumber = new JTextField("9");
        this.txtEndNumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtEndNumber.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtRoundsGame = new JTextField("5000");
        this.txtRoundsGame.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtRoundsGame.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtCountNumber = new JTextField("5");
        this.txtCountNumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtCountNumber.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtTempeture = new JTextField("5000");
        this.txtTempeture.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtTempeture.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtIteDifTempeture = new JTextField("50");
        this.txtIteDifTempeture.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtIteDifTempeture.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtIteSemTempeture = new JTextField("10");
        this.txtIteSemTempeture.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                        || txtIteSemTempeture.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtConstant = new JTextField(10);
        this.txtConstant.setText("0.95");
        this.txtConstant.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtConstant.getText().length() == CommonConstants.NUMBER_MAX) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.txtFile = new JTextField();
        this.txtFile.setEditable(false);

        this.btnSelectFile = new JButton(PropertiesCache.BUTTON_SELECTED_FILE);
        this.btnSelectFile.setActionCommand(CommonConstants.FILE_SELECT);
        this.btnSelectFile.addActionListener(this);

        this.btnExecute = new JButton(PropertiesCache.BUTTON_EXECUTE);
        this.btnExecute.setActionCommand(CommonConstants.RUN_ALGORITHM);
        this.btnExecute.addActionListener(this);

        this.checkSimulation = new JCheckBox("");
        this.checkSimulation.addChangeListener(this);

        groupNorthLeft.setAutoCreateContainerGaps(true);
        groupNorthLeft.setAutoCreateGaps(true);

        groupNorthLeft.setVerticalGroup(groupNorthLeft.createSequentialGroup()
                .addGroup(
                        groupNorthLeft.createParallelGroup().addComponent(lblEnterNumber).addComponent(txtEnterNumber))
                .addGroup(groupNorthLeft.createParallelGroup().addComponent(lblEndNumber).addComponent(txtEndNumber))
                .addGroup(groupNorthLeft.createParallelGroup().addComponent(lblRoundsGame).addComponent(txtRoundsGame))
                .addGroup(
                        groupNorthLeft.createParallelGroup().addComponent(lblCountNumber).addComponent(txtCountNumber))
                .addGroup(groupNorthLeft.createParallelGroup().addComponent(lblFile).addComponent(txtFile)));

        groupNorthLeft.setHorizontalGroup(groupNorthLeft.createSequentialGroup()
                .addGroup(groupNorthLeft.createParallelGroup().addComponent(lblEnterNumber).addComponent(lblEndNumber)
                        .addComponent(lblRoundsGame).addComponent(lblCountNumber).addComponent(lblFile))

                .addGroup(groupNorthLeft.createParallelGroup().addComponent(txtEnterNumber).addComponent(txtEndNumber)
                        .addComponent(txtRoundsGame).addComponent(txtCountNumber).addComponent(txtFile)));

        groupNorthRight.setAutoCreateContainerGaps(true);
        groupNorthRight.setAutoCreateGaps(true);

        groupNorthRight.setVerticalGroup(groupNorthRight.createSequentialGroup()
                .addGroup(groupNorthRight.createParallelGroup().addComponent(lblTempeture).addComponent(txtTempeture))
                .addGroup(groupNorthRight.createParallelGroup().addComponent(lblIteDifTempeture)
                        .addComponent(txtIteDifTempeture))
                .addGroup(groupNorthRight.createParallelGroup().addComponent(lblIteSemtempeture)
                        .addComponent(txtIteSemTempeture))
                .addGroup(groupNorthRight.createParallelGroup().addComponent(lblConstant).addComponent(txtConstant))
                .addGroup(
                        groupNorthRight.createParallelGroup().addComponent(lblSimulate).addComponent(checkSimulation)));

        groupNorthRight.setHorizontalGroup(groupNorthRight.createSequentialGroup()
                .addGroup(groupNorthRight.createParallelGroup().addComponent(lblTempeture)
                        .addComponent(lblIteDifTempeture).addComponent(lblIteSemtempeture).addComponent(lblConstant)
                        .addComponent(lblSimulate))

                .addGroup(groupNorthRight.createParallelGroup().addComponent(txtTempeture)
                        .addComponent(txtIteDifTempeture).addComponent(txtIteSemTempeture).addComponent(txtConstant)
                        .addComponent(checkSimulation)));

        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlSouth.add(btnExecute);
        pnlSouth.add(btnSelectFile);

        JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new BorderLayout());
        pnlNorth.add(pnlNorthLeft, BorderLayout.CENTER);
        pnlNorth.add(pnlNorthRight, BorderLayout.EAST);

        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlSouth, BorderLayout.CENTER);
    }

    /**
     * Metodo que se encarga de escuchar los eventos generados por los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(CommonConstants.FILE_SELECT)) {
            try {
                JFileChooser fc = new JFileChooser("./data");
                fc.setDialogTitle(PropertiesCache.TITLE_DIALOG);
                fc.setMultiSelectionEnabled(false);

                int result = fc.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String pathFile = fc.getSelectedFile().getAbsolutePath();
                    this.fileName = fc.getSelectedFile().getName();
                    this.txtFile.setText(pathFile);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        PropertiesCache.BUTTON_SELECTED_FILE, JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals(CommonConstants.RUN_ALGORITHM)) {
            try {
                String strNumberEnter = this.txtEnterNumber.getText();
                String strNumberEnd = this.txtEndNumber.getText();
                String strRoundsGame = this.txtRoundsGame.getText();
                String strCountNumber = this.txtCountNumber.getText();
                String pathFile = this.txtFile.getText();
                if (strNumberEnter.trim().equals(""))
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_START_NUMBER);
                if (strNumberEnd.trim().equals(""))
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_END_NUMBER);
                if (strRoundsGame.trim().equals(""))
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_ROUNDS_NUMBER);
                if (strCountNumber.trim().equals(""))
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_COUNT_NUMBER);
                if (pathFile.trim().equals(""))
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_FILE_SELECTED);
                int numberEnter = Integer.parseInt(strNumberEnter);
                int numberEnd = Integer.parseInt(strNumberEnd);
                int roundsGame = Integer.parseInt(strRoundsGame);
                int countNumber = Integer.parseInt(strCountNumber);
                if (numberEnter >= numberEnd)
                    throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_NUMBER);
                Table table = this.ctrl.readFile(pathFile, this.fileName, numberEnter, numberEnd, countNumber);
                if (this.checkSimulation.isSelected()) {
                    String temperatureStr = this.txtTempeture.getText();
                    if (temperatureStr.trim().equals(""))
                        throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_TEMPERATURE);
                    String iteracionesDiferentesStr = this.txtIteDifTempeture.getText();
                    if (iteracionesDiferentesStr.trim().equals(""))
                        throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_ITER_DIF);
                    String iteracionesIgualesStr = this.txtIteSemTempeture.getText();
                    if (iteracionesIgualesStr.trim().equals(""))
                        throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_ITER_EQUALS);
                    String consDisminucionStr = this.txtConstant.getText();
                    if (consDisminucionStr.trim().equals(""))
                        throw new Exception(PropertiesCache.MESSAGE_ERROR_INVALID_CONSTANT);
                    int temperature = Integer.parseInt(temperatureStr);
                    int iteracionesDiferentes = Integer.parseInt(iteracionesDiferentesStr);
                    int iteracionesIguales = Integer.parseInt(iteracionesIgualesStr);
                    double consDisminucion = Double.parseDouble(consDisminucionStr);
                    Runnable r = () -> {
                        try {
                            this.ctrl.simulatedAnnealing(temperature, iteracionesDiferentes, iteracionesIguales,
                                    consDisminucion, table, roundsGame);
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                        }
                    };
                    Thread t1 = new Thread(r);
                    t1.start();
                } else {
                    Runnable r = () -> {
                        this.ctrl.generateSimulation(roundsGame, table, true);
                    };
                    Thread t1 = new Thread(r);
                    t1.start();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        PropertiesCache.BUTTON_EXECUTE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Metodo que se encarga de escuchar el cambio de estado del check para el
     * algoritmo.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            if (this.checkSimulation.isSelected()) {
            } else {

            }
        } catch (Exception e2) {

        }
    }
}
