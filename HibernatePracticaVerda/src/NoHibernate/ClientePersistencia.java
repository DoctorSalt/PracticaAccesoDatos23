package NoHibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia {
	private final static String MySQL_DB_USUARIO = "root";
	private final static String MySQL_DB_PASSWORD = "Patata01";

	private final static String MySQL_DB_DRIVER = "com.mysql.jdbc.Driver";
	private final static String MySQL_DB_URL = "jdbc:mysql://localhost:3306/hotel";

	public static synchronized Connection getMySQL_Connection() throws Exception {
		Connection connMySQL;
		try {
			Class.forName(MySQL_DB_DRIVER);
			connMySQL = DriverManager.getConnection(MySQL_DB_URL, MySQL_DB_USUARIO, MySQL_DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new Exception("Error obteniendo la conexión: " + e.getMessage());
		}
		return connMySQL;
	}
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) {
		int idF=0;
		try {
			Connection conection = getMySQL_Connection();
			String consulta = "INSERT INTO clientes VALUES (null, '"+ nombre + "', '"+apellidos +"', '"+ email +"', '" + dni + "', '"+ clave+"');";
			Statement st = conection.createStatement();
			st.execute(consulta);
			st.close();
			st = conection.createStatement();
			consulta = "SELECT MAX(idCliente) AS 'MAXID' FROM clientes";
			ResultSet rs=st.executeQuery(consulta);
			rs.next();
			int id =rs.getInt("MAXID");
			idF=id;
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return idF;
	}

	public static String readCliente(int idCliente, String campo) {
		String resultado="";
		try {
			Connection conection = getMySQL_Connection();
			String consulta = "SELECT * from clientes where idCliente="+idCliente+";";
			Statement st;
			st = conection.createStatement();
			ResultSet rs=st.executeQuery(consulta);
			rs.next();
			switch(campo) {
			case "nombre":
			case "Nombre":
				resultado=rs.getString("nombreCliente");
				break;
			case "apellidos":
			case "Apellidos":
				resultado=rs.getString("apellidosCliente");
				break;
			case "email":
				resultado=rs.getString("emailCliente");
				break;
			case "DNI":
			case "dni":
				resultado=rs.getString("dniCliente");
				break;
			case "clave":
			case "Clave":
				resultado=rs.getString("claveCliente");
				break;
			}
			st.close();
		}
		catch (SQLException e) {
			System.out.println("Error sql probablemente falta ese cliente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
		boolean resultado=false;
		try {
			Connection conection = getMySQL_Connection();
			String consulta = "SELECT * from clientes;";
			Statement st = conection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                    );
			ResultSet rs=st.executeQuery(consulta);	
			rs.absolute(idCliente);
			switch(campo) {
			case "nombre":
			case "Nombre":
				rs.updateString("nombreCliente",nuevoValor);
				break;
			case "apellidos":
			case "Apellidos":
				rs.updateString("apellidosCliente",nuevoValor);
				break;
			case "email":
				rs.updateString("emailCliente",nuevoValor);
				break;
			case "DNI":
			case "dni":
				rs.updateString("dniCliente",nuevoValor);
				break;
			case "clave":
			case "Clave":
				rs.updateString("claveCliente",nuevoValor);
				break;
			}
			rs.updateRow();
			st.close();
			resultado=true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error SQL:");
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("El error es:");
			System.out.println(e);
		}
		return resultado;
	}

	public static boolean deleteCliente(int idCliente) {
		boolean result=false;
		int idF=0;
		try {
			Connection conection = getMySQL_Connection();
			String consulta = "DELETE FROM clientes where idCliente ="+idCliente+";";
			Statement st = conection.createStatement();
			idF=st.executeUpdate(consulta);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error sql probablemente falta ese cliente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(idF!=0) {
			result=true;
		}
		return result;		
	}
}
