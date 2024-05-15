package libreria;

import java.util.*;

public interface parametrizable<T> {

	public final String desarrollador = "Jose Guallasamin";
	public final long id = 1728573153;
	public final String copyrigth = "UPS-PA";
	public final String ERNames = "^([A-ZÁ-Ú][a-zá-ú]{2,}\s?){2,4}$";
	public final String ERAge = "^[0-9]{1,2}$";
	public final String ERDNI = "[0-2][0-9]{8}[-]?[0-9]$";
	public final String ERCorreo = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
	public final String ERFecha = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
	public final String ERHora = "([0-9]{2}):([0-9]{2}):([0-9]{2})";
	public final String ERURL = "^(http|https)://([a-z0-9]+[.])*[a-z0-9]+[.][a-z]{2,3}$";
	public final String ERArchivo = "^([a-zA-Z0-9][-_]?)+[.][a-z]{2,}$";
	
	public static String creditos() {
		return String.format("Desarrollado por: %s; ID: %d; Copyrigth: %s", desarrollador, id, copyrigth);
	}
	
	public default void listarElementos(List<T> elementos) {
		for(int i = 0; i < elementos.size(); i++) {
			System.out.println((i+1) + ".- " + elementos.get(i).toString());
		}
	}
	
	public default double promedio(double... valores) {
		if(valores == null)
			return 0;
		
		double aux = 0;
		for(double valor:valores) {
			aux += valor;
		}
		
		return aux / valores.length;
	}
}
