package com.kentaurus.counting.model;

/**
 * Clase que representa un elemento de la tabla de estrategia.
 * @author Cristhian Castillo
 *
 */
public class ElementList {

	/**
	 * Representa el numero de giro que se esta realizando.
	 */
	private int turn;
	
	/**
	 * Representa la posición del numero que se genera.
	 */
	private int position;

	/**
	 * Contruye una instancia del objeto que representa un elemento de la tabla.
	 */
	public ElementList() {

	}

	/**
	 * Construye una instancia del objeto que representa un elemento de la tabla.
	 * @param turn Giro que se esta realizando-
	 * @param position Posición del numero que se genera.
	 */
	public ElementList(int turn, int position) {
		this.turn = turn;
		this.position = position;
	}

	/**
	 * Obtiene el giro del elemento de la tabla.
	 * @return Giro del elemento.
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Actualiza el giro del elemento de la tabla.
	 * @param turn Giro del elemento.
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * Obtiene la posición del numero que se genera.
	 * @return Pisicion del numero.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Actualiza la posición del numero que se genera.
	 * @param position Posición del numero.
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Asocia un texto significativo a la instancia.
	 */
	@Override
	public String toString() {
		return "ElementList [turn=" + turn + ", position=" + position + "]";
	}
}
