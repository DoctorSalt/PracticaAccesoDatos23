package hibernates;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelManager {
	private static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	public static void crearCliente(ClientePOJO p) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(p);
		transObj.commit();
		sessionObj.close();
		System.out.println("Persona " + p.getIdCliente() + " insertada correctamente");
	}
	public static void readClientes(int idSeleccionada) {
		String c = "FROM ClientePOJO";
		Session sessionObj = getSessionFactory().openSession();
		List results = sessionObj.createQuery(c,ClientePOJO.class).list();
		Iterator personasIterator = results.iterator();
		while (personasIterator.hasNext()) {
			ClientePOJO a2 = (ClientePOJO) personasIterator.next();
			if(a2.getIdCliente()==idSeleccionada) {
				System.out.println(" - "+a2.getNombreCliente()+ " - "+a2.getApellidosCliente()+" - "+a2.getEmailCliente()+" - "+a2.getDniCliente()+" - "+a2.getClaveCliente());
			}
		}
		sessionObj.close();
	}

	public static void updateClientes(ClientePOJO cl) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		ClientePOJO p3 = (ClientePOJO) sessionObj.load(ClientePOJO.class, cl.getIdCliente());
		/* Modificamos todos los atributos */
		p3.setApellidosCliente(cl.getApellidosCliente());
		transObj.commit();
		sessionObj.close();
		System.out.println("Actualizado correctamente");
	}

	public static void deleteClientes(ClientePOJO cl) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		ClientePOJO clienteBD = (ClientePOJO) sessionObj.load(ClientePOJO.class, cl.getIdCliente());
		sessionObj.delete(clienteBD);
		transObj.commit();
		sessionObj.close();
		System.out.println("Eliminado correctamente");
	}

}
