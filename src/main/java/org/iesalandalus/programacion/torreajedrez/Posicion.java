package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

public class Posicion {
	// crear los atributos con visibilidad adecuada
	private int fila;
	private char columna;

	// crear constructor que acepta por parametros una fila y una columna
	public Posicion(int fila, char columna) {
		setFila(fila);
		setColumna(columna);
		// aquí estoy llamando al metodo que hace esa comprobación.

	}

	public Posicion(Posicion posicion) {
		if (posicion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		} // si la posicion es nula lanza excepcion, else asigna la fila y columna

		fila = posicion.fila;
		columna = posicion.columna;
	}

	// métodos getter y setter
	// recibe una fila por parámetro, si es correcta la asigna, else lanza
	// excepcion.
	private void setFila(int fila) {
		if (fila >= 1 && fila <= 8) {
			this.fila = fila;// la asigna
		} else {
			throw new IllegalArgumentException("ERROR: Fila no válida.");
		}

	}

	public int getFila() {
		return fila; // devuelve la fila
	}

	// recibe una columna por parámetro, si es correcta la asigna, else lanza
	// excepcion.
	private void setColumna(char columna) {
		if (columna >= 'a' && columna <= 'h') {
			this.columna = columna;
		} else {

			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}

	}

	public char getColumna() {
		return columna;// devuelve la columna
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return columna == other.columna && fila == other.fila;
	}

	@Override
	public String toString() {
		return "fila=" + fila + ", columna=" + columna;
	}
}
