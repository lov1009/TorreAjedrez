package org.iesalandalus.programacion.torreajedrez;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	static Torre torre;

	public static void main(String[] args) {
		System.out.println("kk");
	}

	private static void mostrarTorre() {
		System.out.println(torre.toString());
	}

	private static void mostrarMenu() {
		System.out.println("MENÚ");
		System.out.println("1. Crear torre por defecto.");
		System.out.println("2. Crear una torre de un color");
		System.out.println("3. Crear una torre de un color en una columna inicial dada ('a' o 'h')");
		System.out.println("4. Mover la torre");
		System.out.println("5. Salir");

	}

	private static int elegirOpcion() {
		System.out.println("Elige una opción del menú.");
		int opcionElegida = Entrada.entero();
		while (opcionElegida < 1 || opcionElegida > 5) {
			System.out.println("Esa no es una opción del menú. Vuelve a elegir una opción correcta.");
			opcionElegida = Entrada.entero();
		}
		return opcionElegida;

	}

	private static Color elegirColor() {
		System.out.println("Elige un color: B(Blanco) o N(Negro)");
		char colorElegido = Entrada.caracter();
		while (colorElegido != 'B' || colorElegido != 'b' || colorElegido != 'A' || colorElegido != 'a') {
			System.out.println("Opción inválida. Vuelve a elegir.");
			colorElegido = Entrada.caracter();
		}
		if (colorElegido == 'B' || colorElegido == 'b') {
			return Color.BLANCO;

		}
		return Color.NEGRO;

	}

	private static char elegirColumnaInicial() {
		System.out.println("Elige la columna inicial: A o H");
		char columnaElegida = Entrada.caracter();

		while (columnaElegida != 'A' || columnaElegida != 'a' || columnaElegida != 'H' || columnaElegida != 'h') {
			System.out.println("Opción inválida. Vuelve a elegir.");
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
}
