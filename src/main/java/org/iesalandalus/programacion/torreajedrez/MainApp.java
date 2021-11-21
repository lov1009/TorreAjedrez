package org.iesalandalus.programacion.torreajedrez;

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
		System.out.println("3. Crear una torre de un color en una columna inicial dada ('c' o 'f')");
		System.out.println("4. Mover la torre");
		System.out.println("5. Salir");

	}
}
