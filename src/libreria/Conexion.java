package libreria;

import java.io.File; 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.Scanner;

public class Conexion implements parametrizable<String>{
	private Files file; //contisne la gestion de archivos
	private Connection conn=null;
	private Statement stmt=null; 
	private ResultSet res=null;
	private Generica<String, String> dt_conn;
	public Conexion (String proyecto) {
		this.dt_conn = new Generica<>(proyecto);
		onInit();//oste o2 ol metado gue inieis con ol analisis del arehive 
	}
	private void onInit() { 
		// TODO Auto-generated method stub 
		//391ecciona donde se va a ubicar el folder de proyectos 
		if (System.getProperty("os.name").split(" ")[0].equals("Windows")){
			file = new Files ("C:/" + name_folder);
		} else {
			file = new Files ("/home/" + name_folder);
		}
		file.create(0);//crea el directorio
		file = new Files(file.getFile().getAbsolutePath() + "/" + dt_conn.getAtributo1() + ".txt");
		if(file.create(1)) {
			//Crear el archivo en ol folder
			String data = "proyecto=" + dt_conn.getAtributo1() +";ip=;port=;name=;user=;psw=;driver=com.mysql.cj.jdbc.Driver;";
			try {
				file.writerFile(data, true);//se snvia a escribic data
				runInitial();//Llana a la carga de datos inicialss
			}catch (IOException e) {
				// T0DO Auto-generated catch block
				System.out.println("Error al escribir el file" + e.getMessage()); 
			}
		}
	}

	private void runInitial() {
		// TODO Auto-generated method stub
		try {
			dt_conn.setAtributo5(file.readerFile().split(";"));
			String[] campos= {"PROYECT: ", "IP: ", "PORT:", "NAME DATA BASE: ", "USER: ","PSW:"};
			Scanner in = new Scanner(System.in);
			String data_proy = ""; //guarda toda la ruta del proyecto
			while(true) {
				data_proy="";
				System.out.println("INGRESE LA SIGUIENTE INFORMACIÓN: ");
				for (int i=0;i<dt_conn.getAtributo5().length;i++) {
					if (i==0) {
						System.out.println(campos[i]+"="+dt_conn.getAtributo1());
						data_proy += dt_conn.getAtributo5()[i].split("=")[0]+"=" + dt_conn.getAtributo1() + ";";
					}else if (i>=1 && i<5) {//solicita los datos IP, port, name, user
						System.out.print (campos [i]);
						data_proy += dt_conn.getAtributo5()[i].split("=")[0]+"="+in.next()+";";
					}else if(i==5) {
						System.out.print ("PASSWORD? (y/n):");
						if (in.next().toLowerCase().equals("y")) {
							System.out.print (campos [i]);
							data_proy += dt_conn.getAtributo5()[i].split("=")[0]+"="+in.next()+";";
						} else {
							data_proy += dt_conn.getAtributo5()[i].split("=")[0]+"= ;";
						}
					}else if (i==6) {
						data_proy += dt_conn.getAtributo5()[i] + "; "; //Carga el driver de MySQL
					}
				}
				System.out.println("ESTA ES LA INFORMACION PROPORCIONADA");
				for(String s:data_proy.split(";")) {
					System.out.println(s);
				}
				System.out.print ("LA INFORMACION ES CORRECTA? (y/n): ");
				if (in.next().toLowerCase().equals("y")) {
				file.writerFile(data_proy, true); //Escribir el proyecto sobre el archivo
				break;
				}
				System.out.println("EL SISTEMA PROCEDE A CREAR EL NUEVO ARCHIVO DE CONFIGURACION DE: " + dt_conn.getAtributo1());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al leer el archivo del proyecto: " + dt_conn.getAtributo1());
		}
	}

	public String getURL() throws IOException {
		dt_conn.setAtributo5(file.readerFile().split(";"));
		if (dt_conn.getAtributo5()[5].split("=")[1].equals(" "))
			dt_conn.getAtributo5()[5].split("=")[1]="";
		return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&useSSL=false&serverTimezone=UTC",
				dt_conn.getAtributo5()[1].split("=")[1], //ip
				dt_conn.getAtributo5()[2].split("=")[1], //port 
				dt_conn.getAtributo5()[3].split("=")[1], //name 
				dt_conn.getAtributo5()[4].split("=")[1], //user 
				dt_conn.getAtributo5()[5].split("=")[1]); //psw
	}

	public Connection openConn () {
		try {
			dt_conn.setAtributo5(file.readerFile().split(";"));
			Class.forName(dt_conn.getAtributo5()[6].split("=")[1]); //Captura el driver
			conn = DriverManager.getConnection(getURL());
			if (conn != null) {
				System.out.println("Conexion Exitosa");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al leer el archivo para abrir la conexión" + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en el Driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en el URL");
		}
		return conn;
	}

	public void closeConn() {
		try {
			if (conn != null) {
				conn.close();
				if (stmt!=null) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al cerrar la conexion");
		}
	}

	public boolean setQuery (String query) {
		try {
			stmt = (Statement) openConn().createStatement();
			stmt.executeUpdate(query);
			closeConn();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al actualizar la bbdd " + e);
			return false;
		}
	}


	public ResultSet getQuery(String query) {
		try {
			stmt = (Statement) openConn().createStatement();
			res = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al consultar en la bbdd " + e);
		}
		return res;
	}
}
