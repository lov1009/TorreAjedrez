package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	static Torre torre;

	public static void main(String[] args) {
		int opcion;
		do {
			mostrarMenu();
			opcion = elegirOpcion();
			ejecutarOpcion(opcion);
			System.out.println("");
			System.out.println("Estado actual de la torre:");
			mostrarTorre();
			System.out.println("");
		} while (opcion != 5);

	}

	private static void mostrarTorre() {
		if (torre == null) {
			System.out.println("Torre no inicializada.");
		} else {
			System.out.println(torre.toString());
		}
	}

	private static void mostrarMenu() {
		System.out.println("          MENÚ       ");
		System.out.println("------------------------------");
		System.out.println("  1. Crear torre por defecto");
		System.out.println("  2. Crear una torre de un color");
		System.out.println("  3. Crear una torre de un color en una columna inicial ('a' o 'h')");
		System.out.println("  4. Mover la torre");
		System.out.println("  5. Salir");
		System.out.println("");

	}

	private static int elegirOpcion() {
		System.out.println("Elige una opción del menú:");
		int opcionElegida = Entrada.entero();
		while (opcionElegida < 1 || opcionElegida > 5) {
			System.out.println("Esa no es una opción del menú. Vuelve a elegir una opción correcta.");
			opcionElegida = Entrada.entero();
		}
		return opcionElegida;

	}

	private static Color elegirColor() {
		System.out.println("Elige un color: b (Blanco) o n (Negro)");
		char colorElegido = Entrada.caracter();
		while (colorElegido != 'B' && colorElegido != 'b' && colorElegido != 'N' && colorElegido != 'n') {
			System.out.println("Opción inválida. Vuelve a elegir (b/n).");
			colorElegido = Entrada.caracter();
		}
		if (colorElegido == 'B' || colorElegido == 'b') {
			return Color.BLANCO;

		}
		return Color.NEGRO;

	}

	private static char elegirColumnaInicial() {
		System.out.println("Elige la columna inicial: a o h");
		char columnaElegida = Entrada.caracter();

		while (columnaElegida != 'A' && columnaElegida != 'a' && columnaElegida != 'H' && columnaElegida != 'h') {
			System.out.println("Opción inválida. Vuelve a elegir (a/h).");
			columnaElegida = Entrada.caracter();
		}
		return Character.toLowerCase(columnaElegida);
	}

	private static void mostrarMenuDirecciones() {
		System.out.println("MENÚ DIRECCIONES:");
		System.out.println("1. ARRIBA.");
		System.out.println("2. ABAJO.");
		System.out.println("3. DERECHA.");
		System.out.println("4. IZQUIERDA");
		System.out.println("5. ENROQUE CORTO.");
		System.out.println("6. ENROQUE LARGO.");

	}

	private static Direccion elegirDireccion() {
		System.out.println("Elige una opción del menú de direcciones.");
		int direccionElegida = Entrada.entero();
		while (direccionElegida < 1 || direccionElegida > 6) {
			System.out.println("Esa no es una opción del menú de direcciones. Vuelve a elegir una opción correcta.");
			direccionElegida = Entrada.entero();
		}

		switch (direccionElegida) {
		case 1:
			return Direccion.ARRIBA;

		case 2:
			return Direccion.ABAJO;

		case 3:
			return Direccion.DERECHA;

		case 4:
			return Direccion.IZQUIERDA;

		case 5:
			return Direccion.ENROQUE_CORTO;

		case 6:
			return Direccion.ENROQUE_LARGO;
		default:
			return null;
		}
	}

	private static void crearTorreDefecto() {
		torre = new Torre();
	}

	private static void crearTorreColor() {
		torre = new Torre(elegirColor());
	}

	private static void crearTorreColorColumna() {
		torre = new Torre(elegirColor(), elegirColumnaInicial());
	}

	private static void mover() {
		mostrarMenuDirecciones();
		Direccion direccionElegida = elegirDireccion();
		switch (direccionElegida) {
		case ENROQUE_CORTO:
		case ENROQUE_LARGO:
			try {
				torre.enrocar(direccionElegida);
			} catch (OperationNotSupportedException ex) {
				System.out.println(ex.getExplanation());

			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
			break;
		default:
			System.out.println("Pasos a mover:");
			int pasosIntroducidos = Entrada.entero();
			try {
				torre.mover(direccionElegida, pasosIntroducidos);

			} catch (OperationNotSupportedException ex) {
				System.out.println(ex.getExplanation());
			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
			break;

		}
	}

	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			crearTorreDefecto();
			break;
		case 2:
			crearTorreColor();
			break;
		case 3:
			crearTorreColorColumna();
			break;
		case 4:
			mover();
			break;

		default:
			break;
		}
	}
}
