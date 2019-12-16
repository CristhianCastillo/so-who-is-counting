package com.kentaurus.counting.model;

import java.util.List;

/**
 * Clase que representa una fila de la tabla de estrategia.
 * @author Cristhian Castillo
 *
 */
public class ElementTable {
	
	/**
	 * Numero observado de la ruleta.
	 */
	private int observedNumber;
	
	/**
	 * Lista de elementos de la tabla que contiene la posicion del numero
	 * de acuerdo al giro que se realiza.
	 */
	private List<ElementList> numberLocationList;
	
	/**
	 * Construye una instancia de la fila de la tabla de estrategia.
	 */
	public ElementTable() {}

	/**
	 * Construye una instancia de la fila de la tabla de estrategia.
	 * @param observedNumber Numero observado en la ruleta.
	 * @param numberLocationList Lista de elementos de la tabla.
	 */
	public ElementTable(int observedNumber, List<ElementList> numberLocationList) {
		this.observedNumber = observedNumber;
		this.numberLocationList = numberLocationList;
	}

	/**
	 * Obtiene el numero observado en la ruleta.
	 * @return Numero observado de la ruleta.
	 */
	public int getObservedNumber() {
		return observedNumber;
	}

	/**
	 * Actualiza el numero observado en la ruleta.
	 * @param observedNumber Numero observado en la ruleta.
	 */
	public void setObservedNumber(int observedNumber) {
		this.observedNumber = observedNumber;
	}

	/**
	 * Obtiene la lista de elemetos de la tabla.
	 * @return Lista de elementos de la tabla.
	 */
	public List<ElementList> getNumberLocationList() {
		return numberLocationList;
	}

	/**
	 * Actualiza la lista de elementos de la tabal.
	 * @param numberLocationList Lista de elementos de la tabla.
	 */
	public void setNumberLocationList(List<ElementList> numberLocationList) {
		this.numberLocationList = numberLocationList;
	}

	/**
	 * Metodo que asocia un texto a una instancia generada.
	 */
	@Override
	public String toString() {
		return "ElementTable [observedNumber=" + observedNumber + ", numberLocationList=" + numberLocationList + "]";
	}
}
