package com.kentaurus.counting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kentaurus.counting.util.PropertiesCache;

/**
 * Clase que representa la tabla de estrategia del juego.
 * 
 * @author Cristhian Castillo
 *
 */
public class Table implements Cloneable {

	/**
	 * Numero inicial de la ruleta.
	 */
	private int startNumber;

	/**
	 * Numero final de la ruleta.
	 */
	private int endNumber;

	/**
	 * Posiciones
	 */
	private int positions;

	/**
	 * Lista de filas para la tabla.
	 */
	private List<ElementTable> elementList;

	/**
	 * Construye una instancia de la tabla de estrategia.
	 * 
	 * @param startNumber Numero inicial de la ruleta.
	 * @param endNumber   Numero final de la ruleta.
	 * @param positions   Posiciones.
	 */
	public Table(int startNumber, int endNumber, int positions) {
		this.startNumber = startNumber;
		this.endNumber = endNumber;
		this.positions = positions;
		this.elementList = new ArrayList<>();
	}

	/**
	 * Metodo para clonar una instancia de este objeto.
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Construye una instancia de la tabla de estrategia.
	 * 
	 * @param elementList Lista de filas para la tabla.
	 */
	public Table(List<ElementTable> elementList) {
		this.elementList = elementList;
	}

	/**
	 * Metodo que agrega una fila a la tabla de estrategias. Realiza las
	 * validaciones correspondientes.para determinar si la fila es valida.
	 * 
	 * @param element Elemento que representa una fila.
	 * @throws Exception Se genera cuando la fila no es valida de acuerdo a la
	 *                   parametrizacion configurada por el usuario.
	 */
	public void addElement(ElementTable element) throws Exception {
		try {
			if (!existElement(element.getObservedNumber()))
				if (isElementValid(element.getObservedNumber()))
					if (isLocationListValid(element.getNumberLocationList()))
						this.elementList.add(element);
					else
						throw new Exception(
								String.format(PropertiesCache.getInstance().getProperty("message.error.invalid.list"),
										element.getObservedNumber()));
				else
					throw new Exception(String.format(
							PropertiesCache.getInstance().getProperty("message.error.invalid.number.view"),
							element.getObservedNumber()));
			else
				throw new Exception(
						String.format(PropertiesCache.getInstance().getProperty("message.error.invalid.number.exist"),
								element.getObservedNumber()));
		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}

	/**
	 * Metodo que valida si un elemento ya existe en la tabla de estrategia.
	 * 
	 * @param index Numero observado que se encunetra en la ruleta.
	 * @return True: Si el elemento existe. False: si el elemento no se encuentra en
	 *         la tabla.
	 */
	public boolean existElement(int index) {
		for (int i = 0; i < this.elementList.size(); i++) {
			ElementTable elementTemp = this.elementList.get(i);
			if (elementTemp.getObservedNumber() == index)
				return true;
		}
		return false;
	}

	/**
	 * Obtiene una lista de elementos de la tabla de acuerdo al nmero observado.
	 * 
	 * @param index Numero observado.
	 * @return Lista de elementos que pertenece a un numero observado.
	 */
	public List<ElementList> getListElements(int index) {
		for (int i = 0; i < this.elementList.size(); i++) {
			ElementTable elementTemp = this.elementList.get(i);
			if (elementTemp.getObservedNumber() == index)
				return elementTemp.getNumberLocationList();
		}
		return null;
	}

	/**
	 * Metodo que valida si un numero observado es valido de acuerdo a la
	 * configuracion ingresada por ele usuario.
	 * 
	 * @param index Numero observado.
	 * @return True: el elemento es valido. False: el elemento no es valido.
	 */
	public boolean isElementValid(int index) {
		if (index >= this.startNumber && index <= this.endNumber)
			return true;
		else
			return false;
	}

	/**
	 * Metodo que valida si la lista de elementos ingresados en valida. Valida que
	 * cada elemento se encuentre en una posicion adecuada.
	 * 
	 * @param listLocation Lista de elementos.
	 * @return True: la lista de elementos es valida. False: la lista de elementos
	 *         no es valida.
	 */
	public boolean isLocationListValid(List<ElementList> listLocation) {
		for (int i = 0; i < listLocation.size(); i++) {
			ElementList elementTemp = listLocation.get(i);
			int validNumber = (this.positions + 1) - elementTemp.getTurn();
			if (elementTemp.getPosition() > validNumber)
				return false;
		}
		return true;
	}

	/**
	 * Metodo que genera un numero aleatorio completo de acuerdo a la ruleta y
	 * configuración parametrizada.
	 * 
	 * @return Numero aleatorio. Ej: (54324, 54323) [null,null,null,null,null]
	 *         -----> 94451
	 */
	public int generateNumber() {
		String[] listNumber = new String[this.positions];
		Random rd = new Random();
		String realFinalString = "";
		for (int i = 0; i < this.positions; i++) {
			int number = rd.nextInt((endNumber - startNumber) + 1) + startNumber;
			int position = this.getPosition(i, number);
			int count = 0;
			for (int j = 0; j < this.positions; j++) {
				if (listNumber[j] == null) {
					count += 1;
					if (count == position) {
						listNumber[j] = number + "";
					}
				}
			}
		}
		for (int k = 0; k < this.positions; k++) {
			realFinalString += listNumber[k];
		}
		return Integer.parseInt(realFinalString);
	}

	/**
	 * Metodo que obtiene la posición exacta donde se debe ubicar un digito del
	 * numero generado.
	 * 
	 * @param turn   Giro actual.
	 * @param number Numero generado.
	 * @return Posicion real donde se debe ubicar el numero.
	 */
	public int getPosition(int turn, int number) {
		List<ElementList> elements = this.getListElements(number);
		for (int i = 0; i < elements.size(); i++) {
			ElementList elementTemp = elements.get(i);
			if (elementTemp.getTurn() == (turn + 1))
				return elementTemp.getPosition();
		}
		return -1;
	}

	/**
	 * Metodo que permite calcular una solucion vecina a la actual.
	 * 
	 * @param numberElements Numero de elementos que se debe alterar.
	 */
	public void changeElements(int numberElements) {
		Random rd = new Random();
		int row = 0;
		int column = 1;
		int value = 1;
		for (int i = 0; i < numberElements; i++) {
			row = rd.nextInt((endNumber - startNumber) + 1) + startNumber;
			column = rd.nextInt((positions - 1) + 1) + 1;
			List<ElementList> list = this.getListElements(row);
			for (int j = 0; j < list.size(); j++) {
				ElementList element = list.get(j);
				if (element.getTurn() == column) {
					boolean isValid = false;
					while (!isValid) {
						value = rd.nextInt((positions - 1) + 1) + 1;
						element.setPosition(value);
						list.set(j, element);
						isValid = this.isLocationListValid(list);
					}
					break;
				}
			}
		}
	}

	/**
	 * Metodo que convierte la matriz a String.
	 * 
	 * @return Cadena de tipo String con la matriz.
	 */
	public String printTable() {
		String tableString = "";
		for (int i = 0; i < this.elementList.size(); i++) {
			ElementTable elementTable = this.elementList.get(i);
			List<ElementList> list = elementTable.getNumberLocationList();
			String row = "";
			for (int j = 0; j < list.size(); j++) {
				if (j == 0) {
					row += list.get(j).getPosition();
				} else {
					row += " - " + list.get(j).getPosition();
				}
			}
			row = "[" + elementTable.getObservedNumber() + "]" + "[" + row + "]";
			tableString += row + "\n";
		}
		return tableString;
	}

	/**
	 * Obtiene la lista de elemetnos de la tabla.
	 * 
	 * @return Lista de elementos de la tabla.
	 */
	public List<ElementTable> getElementList() {
		return elementList;
	}

	/**
	 * Actualiza la lista de elementos de la tala.
	 * 
	 * @param elementList Lista de elementos de la tabla.
	 */
	public void setElementList(List<ElementTable> elementList) {
		this.elementList = elementList;
	}

	/**
	 * Obtiene el numero inicial de la rulet.
	 * 
	 * @return Numero inicial de la ruleta.
	 */
	public int getStartNumber() {
		return startNumber;
	}

	/**
	 * Actualiza el numero inicial de la ruleta.
	 * 
	 * @param startNumber Numero inicial de la tabla.
	 */
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	/**
	 * Obtiene el ultimo elemento de la ruleta.
	 * 
	 * @return Numero final de la ruleta.
	 */
	public int getEndNumber() {
		return endNumber;
	}

	/**
	 * Actualiza el ultimo elemento de la ruleta.
	 * 
	 * @param endNumber Ultimo elemento de la ruleta.
	 */
	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}

	/**
	 * Obtiene el numero de cifras de los numeros que se pueden generar.
	 * 
	 * @return Numero de cifras.
	 */
	public int getPositions() {
		return positions;
	}

	/**
	 * Actualiza el numero de cifras de los numeros que se pueden generar.
	 * 
	 * @param rounds Numero de cifras.
	 */
	public void setPositions(int rounds) {
		this.positions = rounds;
	}
}
