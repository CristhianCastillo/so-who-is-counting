package com.kentaurus.counting.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.kentaurus.counting.constants.CommonConstants;
import com.kentaurus.counting.util.PropertiesCache;

/**
 * Clase que representa el panel que almacena los resultados totales de cada
 * muestra.
 * 
 * @author Cristhian Castillo.
 *
 */
public class PanelTableTotalResult extends JPanel {

	/**
	 * Version de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tabla que contiene los resultados totales de las simulaciones.
	 */
	private JTable tableResult;

	/**
	 * Modelo por default de la tabla.
	 */
	private DefaultTableModel model;

	/***
	 * Construye el panel para los resultados totales.
	 */
	public PanelTableTotalResult() {
		this.setLayout(new BorderLayout());
		this.model = new DefaultTableModel(CommonConstants.COLUMNS_TABLE_TOTAL_RESULT, 0);
		this.tableResult = new JTable(model);
		this.tableResult.setColumnSelectionAllowed(false);
		this.tableResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tableResult.setFont(new Font("Arial", Font.BOLD, 12));
		this.tableResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.tableResult.getTableHeader().setReorderingAllowed(false);

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.tableResult);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
	}

	/**
	 * Agrega un registro con los valores totales de una simulación.
	 * 
	 * @param value Valor esperado de la muestra realizada.
	 * @param id    Identificación de la muestra realizada.
	 * @param time  Tiempo de ejecución de la muestra.
	 * @param name  Nombre de la tabla utilizada como estrategia.
	 */
	public void addResultTotal(long value, String id, String time, String name) {
		try {
			Object[] result = new Object[4];
			result[0] = id;
			result[1] = value;
			result[2] = time;
			result[3] = name;
			model.addRow(result);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					PropertiesCache.getInstance().getProperty("title.add.total.result"), JOptionPane.ERROR_MESSAGE);
		}
	}

}
