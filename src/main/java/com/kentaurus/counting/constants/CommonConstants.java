package com.kentaurus.counting.constants;

/**
 * Constantes que utiliza la aplicación
 * 
 * @author Cristhian Castillo
 *
 */
public class CommonConstants {

	/**
	 * Nombre de las columnas para la tabla que contiene los resultados de una
	 * muestra.
	 */
	public static final String[] COLUMNS_TABLE_RESULT = { "No.", "N\u00famero Generado" };

	/**
	 * Delimitadores para el tratamiento de archivos.
	 */
	public static final String DELIMITS = "[ .,;?!¡¿\'\"\\[\\]]+";

	/**
	 * Autores de la aplicación.
	 */
	public static final String NAMES = "Autores: Alejandro Diaz - Claudia Bermudez - Cristhian Castillo";

	/**
	 * Evento generado al pulsar la opción para seleccionar una tabla de estrategia.
	 */
	public static final String FILE_SELECT = "Seleccionar archivo";

	/**
	 * Evento generado al pulsar la opción para ejecutar el algoritmo.
	 */
	public static final String RUN_ALGORITHM = "Ejecutar Algoritmo";

	/**
	 * Numero maximo de digitos que puedes ser ingresados en un campo de texto.
	 */
	public static final int NUMBER_MAX = 4;

	/**
	 * Nombre de las columnas para la tabla que contiene los reultados totales de
	 * las muestras.
	 */
	public static final String[] COLUMNS_TABLE_TOTAL_RESULT = { "Id Resultado", "Valor Esperado", "Tiempo Ejecuci\u00f3n",
			"Tabla" };

	/**
	 * Separador de una simulación.
	 */
	public static final String OUT_PRINT = "##########################################";

	/**
	 * Separador iteración.
	 */
	public static final String CHANGE_ITERATION = "------------------------------------------------------------------------";
}
