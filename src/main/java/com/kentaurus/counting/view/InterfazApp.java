package com.kentaurus.counting.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JFrame;

import com.kentaurus.counting.controller.CountingController;
import com.kentaurus.counting.util.PropertiesCache;

/**
 * Clase que representa la ventana principal de la aplicación.
 * 
 * @author Cristhian Castillo.
 *
 */
public class InterfazApp extends JFrame {

	/**
	 * Versión de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel donde se ingresan los parametros para realizar una muestra.
	 */
	private PanelEnter pnlEnter;

	/**
	 * Panel donde se encuentran las tablas de resultados.
	 */
	private PanelResult pnlResult;

	/**
	 * Panel donde se muestra el status de los procesos.
	 */
	private PanelProcessStatus pnlProcessStatus;

	/**
	 * Controlador de la aplicación.
	 */
	private CountingController ctrl;

	/**
	 * Construye la ventana principal de la aplicación.
	 * 
	 * @throws ParseException
	 */
	public InterfazApp() throws ParseException {
		this.setTitle(PropertiesCache.TITLE_APPLICATION);
		this.setLayout(new BorderLayout());
		this.ctrl = new CountingController();
		this.pnlEnter = new PanelEnter(this.ctrl);
		this.pnlResult = new PanelResult(this.ctrl);
		this.pnlProcessStatus = new PanelProcessStatus();
		this.add(pnlEnter, BorderLayout.NORTH);
		this.add(pnlResult, BorderLayout.CENTER);
		this.add(pnlProcessStatus, BorderLayout.SOUTH);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.ctrl.conectStatus(pnlProcessStatus);
		// this.setResizable(false);
	}
}
