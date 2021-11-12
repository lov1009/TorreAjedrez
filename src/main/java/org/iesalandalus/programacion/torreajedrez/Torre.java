package org.iesalandalus.programacion.torreajedrez;

public class Torre {

	private Color color;
	private Posicion posicion;

	public Torre() {
		setColor(Color.NEGRO);
		setPosicion(new Posicion(8, 'h'));
	}

	public Torre(Color color) {
		setColor(color);
		if (color == Color.NEGRO) {
			setPosicion(new Posicion(8, 'h'));
		} else {
			setPosicion(new Posicion(1, 'h'));
		}

	}

	public Torre(Color color, char columna) {
		if (columna != 'a' && columna != 'h') {
			throw new IllegalArgumentException("columna no valida");
		}
		setColor(color);
		if (color == Color.BLANCO) {
			setPosicion(new Posicion(1, columna));
		} else {
			setPosicion(new Posicion(8, columna));
		}

	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		if (color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		this.color = color;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	private void setPosicion(Posicion posicion) {
		this.posicion = new Posicion(posicion);

	}

}
