package com.kentaurus.counting.controller;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.kentaurus.counting.constants.CommonConstants;
import com.kentaurus.counting.model.ElementList;
import com.kentaurus.counting.model.ElementTable;
import com.kentaurus.counting.model.Table;
import com.kentaurus.counting.util.PropertiesCache;
import com.kentaurus.counting.view.PanelProcessStatus;
import com.kentaurus.counting.view.PanelResultSimulated;
import com.kentaurus.counting.view.PanelTableResult;
import com.kentaurus.counting.view.PanelTableTotalResult;

/**
 * Clase que representa el controlador de la aplicación.
 * 
 * @author Cristhian Eduardo Castillo Erazo
 *
 */
public class CountingController {

	/**
	 * Panel que contiene la tabla de los resultados de una muestra.
	 */
	private PanelTableResult pnlTableResult;

	/**
	 * Panel que contiene los resultados del total de las muestras realizadas.
	 */
	private PanelTableTotalResult pnlTableTotalResult;

	/**
	 * Panel que muestra los resultados del algoritmo.
	 */
	private PanelResultSimulated pnlResultSimulated;

	/**
	 * Panel que muestra los procesos que se estan ejecutando.
	 */
	private PanelProcessStatus pnlProcess;

	/**
	 * Lector del archivo que contiene la tabla de estrategia.
	 */
	private FileReader fileReader;

	/**
	 * Buffer que contiene los datos encontrados en el archivo de la tabla de
	 * estrategia.
	 */
	private BufferedReader bufferReader;

	/**
	 * Cadena que contiene la lectura de una linea del archivo.
	 */
	private String line;

	/**
	 * Nombre del archivo seleccionado.
	 */
	private String nameFile;

	/**
	 * Construye una instancia del controlador de la aplicación.
	 */
	public CountingController() {
	}

	/**
	 * Metodo que se encarga de establcer una comunicanción entre el panel que
	 * almacena los resultados de cada muestra realizada y el panle que contiene los
	 * resultados totales de cada muestra.
	 * 
	 * @param pnlTableResult      Panel donde se encuentran las tables de cada
	 *                            muestra generada
	 * @param pnlTableTotalResult Panel qie contiene los resultados totales de cada
	 *                            muestra realizada.
	 */
	public void conect(PanelTableResult pnlTableResult, PanelTableTotalResult pnlTableTotalResult,
			PanelResultSimulated pnlResultSimulated) {
		this.pnlTableResult = pnlTableResult;
		this.pnlTableTotalResult = pnlTableTotalResult;
		this.pnlResultSimulated = pnlResultSimulated;
	}

	/**
	 * Metodo que se encarga de establecer una comunicación entre el panel de
	 * procesos con el controlador.
	 * 
	 * @param pnlProcess Panel de procesos.
	 */
	public void conectStatus(PanelProcessStatus pnlProcess) {
		this.pnlProcess = pnlProcess;
	}

	/**
	 * Metodo que se encarga de validar si el archivo seleccionado es valido de
	 * acuerdo a la parametrización ingresada por el usuario.
	 * 
	 * @param pathURL        Ruta del archivo seleccionado.
	 * @param rowsExperiment Cantidad de numeros que dispone la ruleta de juego.
	 * @return True: el archivo seleccionado es valido. False: el archivo
	 *         seleccionado no es valido.
	 * @throws Exception Se genera cuando no se puede realizar la lectura del
	 *                   archivo-
	 */
	public boolean isFileLinesValid(String pathURL, int rowsExperiment) throws Exception {
		try {
			Path path = Paths.get(pathURL);
			long linesReal = Files.lines(path).count();
			return rowsExperiment == linesReal ? true : false;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de leer el archivo.
	 * 
	 * @param path        Ruta del archivo seleccionado.
	 * @param fileName    Nombre del archivo.
	 * @param numberEnter Primer numero que puede aparecer en la ruleta.
	 * @param numberEnd   Ultimo numero que puede aparecer en la ruleta.
	 * @param countNumber Cantidad de cifras que deben tener los numeros generados.
	 * @throws Exception Se genera cuando no se puede leer el archivo, o se produce
	 *                   un error en la logica del algorito.
	 */
	public Table readFile(String path, String fileName, int numberEnter, int numberEnd, int countNumber)
			throws Exception {
		Table tableResult = null;
		try {
			this.nameFile = fileName;
			// Validacion Numero de posiciones
			int linesExpect = (numberEnd - numberEnter) + 1;
			if (!isFileLinesValid(path, linesExpect))
				throw new Exception(PropertiesCache.getInstance().getProperty("message.error.invalid.file"));

			tableResult = new Table(numberEnter, numberEnd, countNumber);
			this.fileReader = new FileReader(path);
			this.bufferReader = new BufferedReader(fileReader);
			int countRow = 0;
			while ((this.line = bufferReader.readLine()) != null) {
				countRow++;
				String[] valuesSplit = this.line.split(CommonConstants.DELIMITS);
				// Validacion de a fila inicial
				if ((valuesSplit.length - 1) != countNumber)
					throw new Exception(String
							.format(PropertiesCache.getInstance().getProperty("message.error.invalid.row"), countRow));
				// Separar valor observado
				String strValueObservate = valuesSplit[0];
				int valueObservate = 0;
				try {
					valueObservate = Integer.parseInt(strValueObservate);
				} catch (Exception ex) {
					throw new Exception(String.format(
							PropertiesCache.getInstance().getProperty("message.error.invalid.number"), countRow));
				}

				// Separar lista
				List<ElementList> listElementsTemp = new ArrayList<>();
				for (int i = 1; i < valuesSplit.length; i++) {
					ElementList elementTemp = new ElementList();
					elementTemp.setTurn(i);
					int element = 0;
					try {
						element = Integer.parseInt(valuesSplit[i]);
					} catch (Exception ex) {
						throw new Exception(String.format(PropertiesCache.getInstance().getProperty("title.aplication"),
								i, countRow));
					}
					elementTemp.setPosition(element);
					listElementsTemp.add(elementTemp);
				}

				ElementTable elementTableTemp = new ElementTable();
				elementTableTemp.setObservedNumber(valueObservate);
				elementTableTemp.setNumberLocationList(listElementsTemp);
				tableResult.addElement(elementTableTemp);
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return tableResult;
	}

	/**
	 * Metodo que se encarga de generar numeros aleatorios para una muestra. Agrega
	 * los resultados a una tabla que sera agregada al panel donde se encuentran,
	 * todos los resultados generados de cada muestra.
	 * 
	 * @param tableResult Tabla que contiene la solución actual.
	 * @param printResult Debe imprimir el resultado en pantalla.
	 * @param rounds      Cantidad de numeros que se deben generar en la muestra.
	 * @return Valor esperado con la matriz ingresada.
	 */
	public long generateSimulation(int rounds, Table tableResult, boolean printResult) {
		long totalTime;
		long timeStart = System.currentTimeMillis();
		long valueFinal = 0;
		DefaultTableModel tableModel = new DefaultTableModel(CommonConstants.COLUMNS_TABLE_RESULT, 0);
		for (int i = 0; i < rounds; i++) {
			int numberGenerate = tableResult.generateNumber();
			Object[] objectTemp = new Object[2];
			objectTemp[0] = i + 1;
			objectTemp[1] = numberGenerate;
			tableModel.addRow(objectTemp);
			valueFinal += numberGenerate;
		}

		valueFinal = valueFinal / rounds;
		long timeEnd = System.currentTimeMillis();
		totalTime = timeEnd - timeStart;

		if (printResult) {
			JTable resultTable = new JTable();
			resultTable.setColumnSelectionAllowed(false);
			resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			resultTable.setFont(new Font("Arial", Font.BOLD, 12));
			resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			resultTable.getTableHeader().setReorderingAllowed(false);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(resultTable);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			resultTable.setModel(tableModel);
			String titleId = this.pnlTableResult.addResult(scroll);
			this.pnlTableTotalResult.addResultTotal(valueFinal, titleId, (double) totalTime + " ms", this.nameFile);
		}
		return valueFinal;
	}

	/**
	 * Implementacion del algoritmo Simulated Annealing.
	 * 
	 * @param temperature Temperatura incial.
	 * @param n1          Numero de iteraciones con diferente temperatura.
	 * @param n2          Numero de iteraciones con la misma temperatura.
	 * @param a           Constante para disminuir la temperatura.
	 * @param tableResult Matriz con la solucición inicial del algoritmo.
	 * @param rounds      Numero de rondas por solucion para calcular el valor
	 *                    esperado.
	 * @throws Exception Ocurre cuando ocurre un error al implementar el algoritmo.
	 */
	public void simulatedAnnealing(double temperature, int n1, int n2, double a, Table tableResult, int rounds)
			throws Exception {
		try {
			this.pnlResultSimulated.cleanSeries();
			this.pnlResultSimulated.writeOut(CommonConstants.OUT_PRINT);
			this.pnlResultSimulated.writeOut("Temperatura Inicial: " + temperature);
			this.pnlResultSimulated.writeOut("Iteraciones misma Teperatura: " + n1);
			this.pnlResultSimulated.writeOut("Iteraciones diferente Teperatura: " + n2);
			this.pnlResultSimulated.writeOut("Soluci\u00f3n Inicial: \n" + tableResult.printTable());
			this.pnlResultSimulated.writeOut(CommonConstants.CHANGE_ITERATION);

			int actualValue = 0;
			this.pnlProcess.start();
			Long solutionNew = new Long(0);
			Long soluctionActual = this.generateSimulation(rounds, tableResult, false);
			this.pnlResultSimulated.addPoint(actualValue, soluctionActual);
			// Iteraciones con temperatura diferente
			for (int i = 0; i < n1; i++) {
				// Iteraciones con igual temperatura
				for (int j = 0; j < n2; j++) {
					actualValue++;
					// Generar nuevo vecino.
					Table tableCopy = (Table) tableResult.clone();
					tableCopy.changeElements(1);
					solutionNew = this.generateSimulation(rounds, tableCopy, false);
					this.pnlResultSimulated.addPoint(actualValue, solutionNew);
					Long delta = solutionNew - soluctionActual;
					// System.out.println("Actual: " + soluctionActual + " Nueva: " + solutionNew);
					if (delta > 0) {
						tableResult = tableCopy;
						soluctionActual = solutionNew;
					} else {
						double t = Math.exp(delta / temperature);
						double randomNumber = Math.random();
						// System.out.println("Expo: " + t + " Random: " + randomNumber);
						if (t > randomNumber) {
							tableResult = tableCopy;
							soluctionActual = solutionNew;
						}
					}
				}
				// Disminuir T
				// temperature = temperature-a;
				temperature = a / (Math.log(1 + (i + 1)));
				this.pnlResultSimulated.writeOut("Temperatura Actual: " + temperature);
			}
			this.pnlResultSimulated.writeOut(CommonConstants.CHANGE_ITERATION);
			this.pnlResultSimulated.writeOut("Soluci\u00f3n optima: \n" + tableResult.printTable());
			this.pnlResultSimulated.writeOut("Valor esperado mejor soluci\u00f3n: " + soluctionActual);
		} catch (Exception e) {
			this.pnlProcess.stop();
			e.printStackTrace();
			throw e;
		}
		this.pnlProcess.stop();
	}

}
