package com.kentaurus.counting;

import java.text.ParseException;

import com.kentaurus.counting.view.InterfazApp;

public class AppStarter {
	
	/**
	 * Metodo pricipal de la aplicación.
	 * 
	 * @param args No son necesarios.
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		InterfazApp application = new InterfazApp();
		application.setVisible(true);
	}
}
