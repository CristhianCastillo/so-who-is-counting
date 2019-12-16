package com.kentaurus.counting.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.kentaurus.counting.util.PropertiesCache;

public class PanelResultSimulated extends JPanel implements ActionListener {
	/**
	 * Version de la clase.
	 */
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------------------
	// Constants
	// -------------------------------------------------------------------------

	/**
	 * Constante para exportar el buffer de salida.
	 */
	public static final String EXPORT_FILE = "Exportar Archivo";

	/**
	 * Constante para restaurar el zoom de chart.
	 */
	public static final String RESTART_ZOOM = "Restaurar Zoom";

	// -------------------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------
	// GUI attributes
	// -------------------------------------------------------------------------

	/**
	 * Campo de texto que representa el buffer de salida de la aplicación.
	 */
	private JTextArea txtBufferOut;

	/**
	 * Boton que ejecuta la exportación del buffer de salida.
	 */
	private JButton btnExport;

	/**
	 * Boton para restaurar el zoom de chart.
	 */
	private JButton btnZoomChart;

	/**
	 * Series de la grafica.
	 */
	private XYSeries series;

	/**
	 * Chart Panel.
	 */
	private ChartPanel chartPanel;

	/**
	 * Split panel para separar una tabla de resultdos de otra.
	 */
	private JSplitPane splitPanel;

	// -------------------------------------------------------------------------
	// Builders
	// -------------------------------------------------------------------------

	/**
	 * Construye el Panel de salida.
	 */
	public PanelResultSimulated() {
		this.setLayout(new BorderLayout());
		this.series = new XYSeries("Valor esperado");
		XYSeriesCollection dataset = new XYSeriesCollection(this.series);
		JFreeChart chart = createChart(dataset);
		this.chartPanel = new ChartPanel(chart);
		this.chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.chartPanel.setBackground(Color.white);

		this.txtBufferOut = new JTextArea(10, 50);
		this.txtBufferOut.setLineWrap(true);
		this.txtBufferOut.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(txtBufferOut);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.btnZoomChart = new JButton(PropertiesCache.getInstance().getProperty("button.zoom"));
		this.btnZoomChart.setActionCommand(RESTART_ZOOM);
		this.btnZoomChart.addActionListener((ActionListener) this);

		this.btnExport = new JButton(PropertiesCache.getInstance().getProperty("button.export.file"));
		this.btnExport.setActionCommand(EXPORT_FILE);
		this.btnExport.addActionListener((ActionListener) this);

		JPanel pnlChart = new JPanel();
		pnlChart.setLayout(new BorderLayout());
		pnlChart.add(this.chartPanel, BorderLayout.CENTER);

		JPanel pnlSur = new JPanel();
		pnlSur.setLayout(new FlowLayout(FlowLayout.TRAILING));
		pnlSur.add(btnZoomChart);
		pnlSur.add(btnExport);

		this.splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, scroll, pnlChart);
		this.splitPanel.setOneTouchExpandable(true);
		this.splitPanel.setDividerLocation(300);

		this.add(this.splitPanel, BorderLayout.CENTER);
		this.add(pnlSur, BorderLayout.SOUTH);
	}

	// -------------------------------------------------------------------------
	// Functional methods
	// -------------------------------------------------------------------------

	/**
	 * Crear una grafica.
	 * 
	 * @param dataset Origen de datos para la grafica.
	 * @return Grafica creada.
	 */
	private JFreeChart createChart(final XYDataset dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(PropertiesCache.getInstance().getProperty("chart.title"),
				PropertiesCache.getInstance().getProperty("chart.x"),
				PropertiesCache.getInstance().getProperty("chart.y"), dataset, PlotOrientation.VERTICAL, true, true,
				false);
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		chart.getLegend().setFrame(BlockBorder.NONE);
		chart.setTitle(new TextTitle(PropertiesCache.getInstance().getProperty("chart.title")));
		return chart;
	}

	/**
	 * Agregar un punto a la grafica.
	 * 
	 * @param x Posición en X.
	 * @param y Posición en Y.
	 */
	public void addPoint(int x, Long y) {
		this.series.add(x, y);
	}

	/**
	 * Limpiar los puntos generados en la grafica.
	 */
	public void cleanSeries() {
		this.series.clear();
	}

	/**
	 * Escribe una linea en el buffer de salida de la aplicación.
	 * 
	 * @param linea
	 */
	public void writeOut(String linea) {
		this.txtBufferOut.append(linea + "\n");
		this.txtBufferOut.setCaretPosition(txtBufferOut.getDocument().getLength());
	}

	/**
	 * Limpia el buffer de salida de la aplicación.
	 */
	public void cleanBuffer() {
		this.txtBufferOut.setText("");
	}

	/**
	 * Manejo de los eventos de los botones.
	 * 
	 * @param e Acción que genero el evento. e != null.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase(EXPORT_FILE)) {
			try {
				if (txtBufferOut.getText().trim().equalsIgnoreCase(""))
					throw new Exception(PropertiesCache.getInstance().getProperty("message.error.export.file"));
				JFileChooser fc = new JFileChooser("./data");
				fc.setDialogTitle(PropertiesCache.getInstance().getProperty("title.file"));
				fc.setMultiSelectionEnabled(false);
				fc.showSaveDialog(this);
				File guardar = fc.getSelectedFile();
				if (guardar != null) {
					try (FileWriter save = new FileWriter(guardar + ".txt")) {
						save.write(txtBufferOut.getText());
					}
					JOptionPane.showMessageDialog(null,
							PropertiesCache.getInstance().getProperty("message.file.export"),
							PropertiesCache.getInstance().getProperty("title.dialog.file"),
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						PropertiesCache.getInstance().getProperty("title.dialog.file"), JOptionPane.ERROR_MESSAGE);
			}
		}

		if (comando.equals(RESTART_ZOOM)) {
			this.chartPanel.restoreAutoBounds();
		}
	}
}
