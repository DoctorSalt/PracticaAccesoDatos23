package hibernates;

import java.util.Scanner;

public class principalHibernate {
	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		crearClienteP(leer);
		leerCliente(leer);
		modificarCliente(leer);
		eliminarCliente(leer);
	}

	private static void eliminarCliente(Scanner leer) {
		System.out.println("Escriba el id del cliente a destruir");
		boolean pass=false;
		while(!pass) {						
			try {
				int idEscrito = Integer.parseInt(leer.nextLine());
				pass=true;
				ClientePOJO cl4=new ClientePOJO(idEscrito);
				HotelManager.deleteClientes(cl4);
			}catch(Error e){
				System.out.println("Escriba bien el id o es incorrecto:");
				System.out.println("De nuevo");
		}			
		}
	}

	private static void modificarCliente(Scanner leer) {
		boolean pass=false;
		System.out.println("Escriba el id del Cliente:");
		int idEscrito = 0;
		while(!pass) {						
			try {
				idEscrito = Integer.parseInt(leer.nextLine());
				pass=true;
			}catch(Error e){
				System.out.println("Escriba bien el id");
				System.out.println("De nuevo");
			}			
		}
		System.out.println("Escriba el nuevo apellido del Cliente");
		String  apellidoEscrito = leer.nextLine();
		ClientePOJO cl3=new ClientePOJO(idEscrito,apellidoEscrito);
		HotelManager.updateClientes(cl3);
	}

	private static void crearClienteP(Scanner leer) {
		ClientePOJO cl1 = creacionClientes(leer);
		HotelManager.crearCliente(cl1);
	}

	private static void leerCliente(Scanner leer) {
		boolean pass=false;
		System.out.println("Escriba el id del Cliente:");
		while(!pass) {						
			try {
				String recogido=leer.nextLine();
				int idEscrito = Integer.parseInt(recogido);
				pass=true;
				HotelManager.readClientes(idEscrito);
			}catch(Error e){
				System.out.println("Escriba bien el id o es incorrecto:");
				System.out.println("De nuevo");
			}			
		}
	}

	private static ClientePOJO creacionClientes(Scanner leer) {
		System.out.println("Escriba el nombre del Cliente");
		String nombreEscrito = leer.nextLine();
		System.out.println("Escriba el apellido del Cliente");
		String apellidoEscrito = leer.nextLine();
		System.out.println("Escriba el correo del Cliente");
		String correoEscrito = leer.nextLine();
		System.out.println("Escriba el dni del Cliente");
		String dniEscrito = leer.nextLine();
		System.out.println("Escriba la contraseña del Cliente");
		String passwordEscrito = leer.nextLine();
		ClientePOJO cl1=new ClientePOJO(nombreEscrito, apellidoEscrito, correoEscrito, dniEscrito, passwordEscrito);
		return cl1;
	}
}
