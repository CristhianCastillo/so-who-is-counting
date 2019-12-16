package com.kentaurus.counting.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.kentaurus.counting.constants.CommonConstants;

/**
 * Panel para los procesos de la aplicación.
 * 
 * @author Cristhian Castillo.
 *
 */
public class PanelProcessStatus extends JPanel {

	/**
	 * Versión de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Comando enviado al inicial un proeso.
	 */
	private static final String START_PROCESS = "Procesando....";

	/**
	 * Comando enviado al terminar un proceso.
	 */
	private static final String STOP_PROCESS = "Nada para procesar...";

	/**
	 * Etiqueta para almacenar los nombres de autores.
	 */
	private JLabel lblNames;

	/**
	 * Etiqueta para mostrar el status de un proceso.
	 */
	private JLabel lblStatus;

	/**
	 * Barra de procesos.
	 */
	private JProgressBar progressBar;

	/**
	 * Metodo que construye le panel.
	 */
	public PanelProcessStatus() {

		this.setLayout(new BorderLayout());

		this.lblNames = new JLabel(CommonConstants.NAMES);
		this.lblStatus = new JLabel(STOP_PROCESS);
		this.progressBar = new JProgressBar();
		this.progressBar.setEnabled(false);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlLeft.add(lblNames);

		JPanel pnlRight = new JPanel();
		pnlRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlRight.add(this.lblStatus);
		pnlRight.add(progressBar);

		this.add(pnlLeft, BorderLayout.WEST);
		this.add(pnlRight, BorderLayout.CENTER);
	}

	/**
	 * Iniciar un proceso.
	 */
	public void start() {
		this.lblStatus.setText(START_PROCESS);
		this.progressBar.setIndeterminate(true);
	}

	/**
	 * Detener un proceso.
	 */
	public void stop() {
		this.lblStatus.setText(STOP_PROCESS);
		this.progressBar.setIndeterminate(false);
	}

}
