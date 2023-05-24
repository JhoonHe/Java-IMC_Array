import javax.swing.JOptionPane;

public class Procesos {

	String[] nombres;
	String[] telefonos;
	String[] respuestaImc;

	boolean registros = false;

	public Procesos() {

	}

	public void iniciar() {

		int opcion = 0;
		String menu = obtenerMenu();

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			validarOpcion(opcion);
		} while (opcion != 6);

		JOptionPane.showMessageDialog(null, "Gracias por usar el programa");

	}

	public void validarOpcion(int opcion) {

		switch (opcion) {

		case 1:
			registrarDatos();
			break;

		case 2:
			if (registros) {
				imprimirNombre();
				break;
			}
			JOptionPane.showMessageDialog(null, "No hay personas registradas");
			break;

		case 3:
			if (registros) {
				imprimirLista();
				break;
			}
			JOptionPane.showMessageDialog(null, "No hay personas registradas");
			break;

		case 4:
			if (registros) {
				actualizarDatos();
				break;
			}
			JOptionPane.showMessageDialog(null, "No hay personas registradas");
			break;

		case 5:
			if (registros) {
				eliminarDatos();
				break;
			}
			JOptionPane.showMessageDialog(null, "No hay personas registradas");
			break;

		case 6:
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcion incorrecta");
			break;
		}
	}

	private void eliminarDatos() {

		String nombre = "";
		boolean registro = false;

		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a eliminar la informacion").toLowerCase();

		for (int i = 0; i < nombres.length; i++) {

			if (nombres[i].equals(nombre)) {
				nombres[i] = null;
				telefonos[i] = null;
				respuestaImc[i] = null;
				
				registro = true;
			} 
		}
		
		if (registro) {
			JOptionPane.showMessageDialog(null, "Los datos de la persona " + nombre + " fueron eliminados correctamente");			
		}else {			
			JOptionPane.showMessageDialog(null, "El nombre ingresado no se encuentra en el sistema");
		}
	}

	private void actualizarDatos() {

		String nombre = "**** Actualizar datos ****";
		int opcion = 0;
		boolean registro = false;

		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a actualizar la informacion").toLowerCase();

		for (int i = 0; i < nombres.length; i++) {

			if (nombres[i].equals(nombre)) {

				do {
					opcion = Integer.parseInt(JOptionPane.showInputDialog(
							"Seleccione que desea actualizar de la persona:\n1- Nombre\n2- Telefono\n3- Nombre y Telefono"
									+ nombres[i]));
				} while (opcion < 0 || opcion > 3);

				switch (opcion) {

				case 1:
					nombres[i] = nombres[i] = JOptionPane
							.showInputDialog("Ingrese el nombre nuevo de la persona " + nombres[i]).toLowerCase();
					break;

				case 2:
					telefonos[i] = JOptionPane.showInputDialog("Ingrese el telefono nuevo de la persona " + nombres[i]);
					break;

				case 3:
					nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre nuevo de la persona #" + nombres[i]).toLowerCase();
					telefonos[i] = JOptionPane.showInputDialog("Ingrese el telefono nuevo de la persona " + nombres[i]);
					break;
				}
				
				registro = true;
			} 
		}
		
		if (registro) {
			JOptionPane.showMessageDialog(null, "Los datos de la perosna ingresada fueron actualizados correctamente");			
		}else {			
			JOptionPane.showMessageDialog(null, "El nombre ingresado no se encuentra en el sistema");
		}
	}

	private void imprimirLista() {

		String mensaje = "**** Imprimir lista ****\n";

		for (int i = 0; i < nombres.length; i++) {
			
			if (nombres[i] != null) {

				mensaje += "\nNombre: " + nombres[i];
				mensaje += "| Telefono: " + telefonos[i];
				mensaje += "| IMC: " + respuestaImc[i];
			}
		}
		JOptionPane.showMessageDialog(null, mensaje);
	}

	private void imprimirNombre() {

		String nombre = "";
		String mensaje = "**** Imprimir nombre ****\n\n";
		boolean registro = false;
		
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de persona a imprimir la informacion").toLowerCase();

		for (int i = 0; i < nombres.length; i++) {

			if (nombres[i].equals(nombre)) {

				mensaje += "Nombre: " + nombres[i];
				mensaje += "| Telefono: " + telefonos[i];
				mensaje += "| IMC: " + respuestaImc[i];

				registro = true;
			}
		}
		
		if(registro) {
			JOptionPane.showMessageDialog(null, mensaje);			
		}else {
			JOptionPane.showMessageDialog(null, "El nombre ingresado no se encuentra en el sistema");			
		}
	}

	private void registrarDatos() {

		int cantidad;
		double peso = 0, talla = 0, imc = 0;

		cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a calulcar el IMC"));

		nombres = new String[cantidad];
		telefonos = new String[cantidad];
		respuestaImc = new String[cantidad];

		for (int i = 0; i < cantidad; i++) {

			nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre de la persona #" + (i + 1)).toLowerCase();
			telefonos[i] = JOptionPane.showInputDialog("Ingrese el telefono de la persona " + nombres[i]);

			peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso de la persona " + nombres[i]));
			talla = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la talla de la persona " + nombres[i]));

			imc = calcularImc(peso, talla);

			respuestaImc[i] = obtenerRespuesta(imc);
		}

		registros = true;
	}

	public String obtenerRespuesta(double imc) {

		String mensaje = "";

		if (imc < 18) {
			mensaje = "Anorexia";
		} else if (imc >= 18 && imc < 20) {
			mensaje = "Delgadez";
		} else if (imc >= 20 && imc < 27) {
			mensaje = "Normalidad";
		} else if (imc >= 27 && imc < 30) {
			mensaje = "Obesidad (grado 1)";
		} else if (imc >= 30 && imc < 35) {
			mensaje = "Obesidad (grado 2)";
		} else if (imc >= 35 && imc < 40) {
			mensaje = "Obesidad (grado 3)";
		} else {
			mensaje = "Obesidad (grado 3)";
		}

		return mensaje;
	}

	public double calcularImc(double peso, double talla) {

		double imc = 0;

		imc = peso / (talla * talla);

		return imc;
	}

	public String obtenerMenu() {
		String menu = "";

		menu += "****MENU****";
		menu += "\n\nIngrese la opcion";
		menu += "\n\n1- Registrar datos";
		menu += "\n2- Imprimir por nombre";
		menu += "\n3- Imprimir por lista completa";
		menu += "\n4- Actualizar datos";
		menu += "\n5- Eliminar datos";
		menu += "\n6- Salir\n\n";

		return menu;
	}
}
