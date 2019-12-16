package com.kentaurus.counting.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.kentaurus.counting.util.PropertiesCache;

/**
 * Clase que representa el panel que contiene las tablas con los resultados de
 * cada simulación.
 * 
 * @author Cristhian Castillo.
 *
 */
public class PanelTableResult extends JPanel {

	/**
	 * Version de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apnel tab para almacenar cada tabla con los resultados de una simulación.
	 */
	private JTabbedPane tabs;

	/**
	 * Numero de resultados.
	 */
	private int resultNumber;

	/**
	 * Construye el panel para almacenar los resultados de cada simulación.
	 */
	public PanelTableResult() {
		this.resultNumber = 0;
		this.setLayout(new BorderLayout());
		this.tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(tabs, BorderLayout.CENTER);
	}

	/**
	 * Agrega un componente al conjunto de tablas con los resultados.
	 * 
	 * @param component Tabla con los resultados de una simulación.
	 * @return Id del resultado generado.
	 */
	public String addResult(JComponent component) {
		String titleResult = "";
		try {
			if (component != null) {
				resultNumber++;
				titleResult = "Resultado " + resultNumber;
				tabs.addTab(titleResult, null, component, "Resultado " + resultNumber);
				int count = tabs.getTabCount();
				tabs.setSelectedIndex(count - 1);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					PropertiesCache.getInstance().getProperty("title.add.result"), JOptionPane.ERROR_MESSAGE);
		}
		return titleResult;
	}

}
