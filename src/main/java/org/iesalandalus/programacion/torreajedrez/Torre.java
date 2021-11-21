package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

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
		if (columna < 'a' || columna > 'h') { // Preguntar pq el test m obliga a poner eso, pero tendría q poner q
												// fueran solo los valores a o h
			throw new IllegalArgumentException("ERROR: Columna no válida.");
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
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		if (pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}

		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}

		switch (direccion) {
		case IZQUIERDA:
			if ((color == Color.BLANCO && posicion.getColumna() - pasos < 'a')
					|| (color == Color.NEGRO && posicion.getColumna() + pasos > 'h')) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			if (color == Color.BLANCO) {
				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
			} else {
				setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
			}
			break;

		case DERECHA:
			if ((color == Color.BLANCO && posicion.getColumna() + pasos > 'h')
					|| (color == Color.NEGRO && posicion.getColumna() - pasos < 'a')) {
				throw new OperationNotSupportedException("ERROR: se sale del tablero"); // Esto no lo está comprobando,
																						// solo la excp d arriba.
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
				throw new OperationNotSupportedException("ERROR: se sale del tablero");
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

}
