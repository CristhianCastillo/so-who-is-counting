package com.kentaurus.counting.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.kentaurus.counting.controller.CountingController;

/**
 * Panel que se usa para mostrar los resultados generados por la simulación.
 * 
 * @author Cristhian Castillo.
 *
 */
public class PanelResult extends JPanel {

	/**
	 * Version de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tab para el panel de resultados.
	 */
	private JTabbedPane tabs;

	/**
	 * Split panel para separar una tabla de resultdos de otra.
	 */
	private JSplitPane splitPanel;

	/**
	 * Panel que contiene las tablas generadas en cada simulación.
	 */
	private PanelTableResult pnlResultTable;

	/**
	 * Panel que contiene la tabla con los totales de cada simulación.
	 */
	private PanelTableTotalResult pnlTotalResultTable;

	/**
	 * Panel que contiene los resultados de la simulación.
	 */
	private PanelResultSimulated pnlResultSimulated;

	/**
	 * Controlador de la aplicación.
	 */
	private CountingController ctrl;

	/**
	 * Construye el panel para mostrar los resultados generados de la simulación.
	 * 
	 * @param ctrl Constrolador de la aplicación.
	 */
	public PanelResult(CountingController ctrl) {
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.pnlResultTable = new PanelTableResult();
		this.pnlTotalResultTable = new PanelTableTotalResult();
		this.pnlResultSimulated = new PanelResultSimulated();

		this.splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, this.pnlResultTable,
				this.pnlTotalResultTable);
		this.splitPanel.setOneTouchExpandable(true);
		this.splitPanel.setDividerLocation(300);

		this.tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Valor Esperado", null, splitPanel, "Valor Esperado");
		tabs.addTab("Simulated Annealig", null, pnlResultSimulated, "Simulated Annealig");

		this.add(tabs, BorderLayout.CENTER);
		this.ctrl.conect(pnlResultTable, pnlTotalResultTable, pnlResultSimulated);
	}

}
