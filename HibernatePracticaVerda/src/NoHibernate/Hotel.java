package NoHibernate;

public class Hotel {
	public static void main(String[] args) {
		int id = ClientePersistencia.createCliente("Javier", "Escudero", "javier@grupostudium.com", "12345678Z", "Studium2018");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
		ClientePersistencia.updateCliente(id, "apellidos", "Escudero Fern�ndez");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
		ClientePersistencia.deleteCliente(id);
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
	}
}
