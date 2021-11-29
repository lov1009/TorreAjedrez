package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Torre {
	// declarar atributos de la clase Color y Posicion
	private Color color;
	private Posicion posicion;

	/*
	 * Constructor por defecto, NO RECIBE NADA, que crea una torre negra y en
	 * posicion 8,h
	 */
	public Torre() {
		color = Color.NEGRO;
		posicion = new Posicion(8, 'h');

		/*
		 * Esta es la opcion q tenia antes, pero llamaba a los metodos pa comprobar q
		 * estuviera bien y luego caí en que es por defecto asi que no hay que hacer
		 * comprobaciones. setColor(Color.NEGRO); setPosicion(new Posicion(8, 'h'));
		 */
	}

	/*
	 * constructor copia q llama al metodo color y comprueba si es negro, y entonces
	 * asigna una posicion, si es blanco, asigna otra.
	 */
	public Torre(Color color) {
		setColor(color);
		if (color == Color.NEGRO) {
			setPosicion(new Posicion(8, 'h'));
		} else {
			setPosicion(new Posicion(1, 'h'));
		}

	}

	/*
	 * Constructor que recibe color y columna y si son válidas llama al método
	 * color, si es blanca le asigna la posición 1 y la columna dada, y si no 8 y la
	 * columna
	 */
	public Torre(Color color, char columna) {
		if (columna < 'a' || columna > 'h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}

		setColor(color);
		if (color == Color.BLANCO) {
			setPosicion(new Posicion(1, columna));
		} else {
			setPosicion(new Posicion(8, columna));
		}

	}

	// getter y setter
	public Color getColor() {
		return color; // devuelve el color, no recibe nada x parámetro
	}

	private void setColor(Color color) {// recibe un color de la clase Color,
		if (color == null) {// se comprueba y si es nulo, lanza excepcion.
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		this.color = color; // si no, se asigna. No se pone new = Color.(color)
	} // pq es un enumerado, eso solo se hace en las clases (ex:Posicion)

	public Posicion getPosicion() {
		return posicion; // devuelve la posicion, no recibe nada x parámetro
	}

	private void setPosicion(Posicion posicion) {
		this.posicion = new Posicion(posicion);

	}

	// Crear método enrocar
	public void enrocar(Direccion direccion) throws OperationNotSupportedException {
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}

		switch (direccion) {
		case ENROQUE_CORTO:
			if (color == Color.BLANCO && posicion.getColumna() == 'h' && posicion.getFila() == 1) {
				setPosicion(new Posicion(1, 'f'));
			} else if (color == Color.NEGRO && posicion.getColumna() == 'h' && posicion.getFila() == 8) {
				setPosicion(new Posicion(8, 'f'));
			} else {
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}

			break;

		case ENROQUE_LARGO:
			if (color == Color.BLANCO && posicion.getColumna() == 'a' && posicion.getFila() == 1) {
				setPosicion(new Posicion(1, 'd'));
			} else if (color == Color.NEGRO && posicion.getColumna() == 'a' && posicion.getFila() == 8) {
				setPosicion(new Posicion(8, 'd'));
			} else {
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}
			break;

		default:
			break;
		}
	}

	// Crear método mover
	// si los pasos son negativos o 0, excepcion, si direccion es nula excepcion, si
	// no, switch, con los movimientos
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		if (pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}

		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}

		switch (direccion) {

		case IZQUIERDA:
			/*
			 * recibe una direccion, q es izquierda en este caso. si el color es blanco y la
			 * posicion (segun ese metodo) es esa, la asigna con los cambios de pasos
			 * necesarios. Lo mismo en el resto de casos
			 */
			if (color == Color.BLANCO && posicion.getColumna() - pasos >= 'a') {

				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));

			} else if (color == Color.NEGRO && posicion.getColumna() + pasos <= 'h') {
				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));

			} else {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		/*
		 * la parte de arriba la hice para mejorar el código de los casos, que lo tenía
		 * como los de aquí abajo, para no repetir dos veces color == Color.BLANCO.
		 * Tengo que mejorar los demás de abajo.
		 */
		case DERECHA:
			if ((color == Color.BLANCO && posicion.getColumna() + pasos > 'h')
					|| (color == Color.NEGRO && posicion.getColumna() - pasos < 'a')) {
				throw new OperationNotSupportedException("ERROR: se sale del tablero.");
			}
			if (color == Color.BLANCO) {
				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
			} else {
				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
			}
			break;

		case ARRIBA:
			if ((color == Color.BLANCO && posicion.getFila() + pasos > 8)
					|| (color == Color.NEGRO && posicion.getFila() - pasos < 1)) {
				throw new OperationNotSupportedException("ERROR: se sale del tablero.");
			}
			if (color == Color.BLANCO) {
				setPosicion(new Posicion(posicion.getFila() + pasos, posicion.getColumna()));
			} else {
				setPosicion(new Posicion(posicion.getFila() - pasos, posicion.getColumna()));
			}

			break;

		case ABAJO:
			if ((color == Color.BLANCO && posicion.getFila() - pasos < 1)
					|| (color == Color.NEGRO && posicion.getFila() + pasos > 8)) {
				throw new OperationNotSupportedException("ERROR: se sale del tablero");
			}
			if (color == Color.BLANCO) {
				setPosicion(new Posicion(posicion.getFila() - pasos, posicion.getColumna()));
			} else {
				setPosicion(new Posicion(posicion.getFila() + pasos, posicion.getColumna()));
			}
			break;

		default:
			break;
		}
	}

	// Crear métodos hashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(color, posicion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torre other = (Torre) obj;
		return color == other.color && Objects.equals(posicion, other.posicion);
	}

	@Override
	public String toString() {
		return "fila=" + posicion.getFila() + ", columna=" + posicion.getColumna() + ", color=" + color;
	}

}
