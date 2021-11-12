package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

public class Posicion {

	private int fila;
	private char columna;

	public Posicion(int fila, char columna) {
		setFila(fila);
		setColumna(columna);
	}

	public Posicion(Posicion posicion) {
		if (posicion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		}

		fila = posicion.fila;
		columna = posicion.columna;
	}

	private void setFila(int fila) {
		if (fila >= 1 && fila <= 8) {
			this.fila = fila;
		} else {

			throw new IllegalArgumentException("ERROR: Fila no válida.");
		}

	}

	public int getFila() {
		return fila;
	}

	private void setColumna(char columna) {
		if (columna >= 'a' && columna <= 'h') {
			this.columna = columna;
		} else {

			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}

	}

	public char getColumna() {
		return columna;
	}

}
