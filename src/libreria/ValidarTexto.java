package libreria;

public class ValidarTexto implements parametrizable<String>{

	public static boolean validarNombre(String nombre) {
        return nombre.matches(ERNames);
    }
	
	public static boolean validarEdad(String edad) {
		return edad.matches(ERAge);
	}
	
	public static boolean validarDNI(String dni) {
		return dni.matches(ERDNI);
	}
	
	public static boolean validarCorreo(String correo) {
		return correo.matches(ERCorreo);
	}
	
	public static boolean validarFecha(String fecha) {
		return fecha.matches(ERFecha);
	}
	
	public static boolean validarHora(String hora) {
		return hora.matches(ERHora);
	}
	
	public static boolean validarURL(String url) {
		return url.matches(ERURL);
	}
	
	public static boolean validarArchivo(String archivo) {
		return archivo.matches(ERArchivo);
	}
}
