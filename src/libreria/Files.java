package libreria;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Files {
	/**
	 * @author Daniel Diaz
	 * @since 2023-10
	 * Modificado por: Jose Guallasamin
	 * 2024-05
	 */
	
	private Generica<String, File> dt_a;//Referencia a la clase generica<T,S>
	private boolean flag=false;//Variable que nos sirve como bandera de estado
	/*
	 * Constructor con un String como argumento
	 */
	public Files(String pathname) {
		dt_a=new Generica<>(pathname, new File(pathname));
	}
	/**
	 * Metodo que permite escribir informacion en un archivo
	 * @param text -> Es el texto a escribir en el archivo
	 * @param allow -> Es el permiso de sobreescritura
	 * @return
	 * @throws IOException
	 */
	public boolean writerFile(String text, boolean allow) throws IOException {
			FileWriter archivo=new FileWriter(
					dt_a.getAtributo3().getAbsolutePath(),!allow);
			archivo.write(text+"\n");
			archivo.close();
			return true;
	}
	/**
	 * Metodo para leer los datos de un archivo
	 * @return String
	 * @throws IOException 
	 */
	public String readerFile() throws IOException {
		dt_a.setAtributo2("");
		FileReader reader=new FileReader(dt_a.getAtributo3().getAbsolutePath());
		int caracteres;
		while((caracteres=reader.read())!=-1) {
			dt_a.setAtributo2(dt_a.getAtributo2()+String.valueOf((char)caracteres));
		}
		reader.close();
		return dt_a.getAtributo2();
	}
	/**
	 * Metodo para crear archivo y directorios
	 * @param tipo 1 crear un archivo, y 0 un directorio
	 * @return
	 */
	public boolean create(int tipo) {
		switch(tipo) {
		case 0:
			if(!dt_a.getAtributo3().exists())
				flag=dt_a.getAtributo3().mkdir();//Crear un directorio
			break;
		case 1:
			if(!dt_a.getAtributo3().exists()) {
				try {
					flag=dt_a.getAtributo3().createNewFile();//Crear un archivo
				} catch (IOException e) {
					// TODO Auto-generated catch block
					flag=false;
				}
			}
			break;
			default:
				flag=false;
				break;
				
		}
		return flag;
	}
	/**
	 * Metodo para validar una cadena mediante de una ER
	 * @param chain cadena
	 * @param ER Patron
	 * @return
	 */
	public static boolean validateByER(String text, String ER) {
		return text.matches(ER);
	}
	
	/**
	 * Metodo para reemplazar una palabra en un texto
	 * @param text Es el texto de analisis
	 * @param replace Es la palabra de reemplazo 
	 * @param SearchWord Es la palabra a buscar
	 * @return
	 */
	public static String SearchReplace(String text,String replace, String SearchWord ) {
		Pattern patron=Pattern.compile(SearchWord);
		Matcher analisis=patron.matcher(text);
		return analisis.replaceAll(replace);
	}
	/**
	 * Metodo para obtener las palabras que cumplan una expresion regular
	 * @param text Texto para analizar
	 * @param ER Expresion Regular
	 * @return List<String>
	 */
	public List<String> findWords(String text,String ER) {
		Pattern patron=Pattern.compile(ER);
		dt_a.setAtributo5(text.split("(\s+|[,.;])"));
		for(String word:dt_a.getAtributo5()) {
			Matcher analisis=patron.matcher(word);
			if(analisis.find()) {
				dt_a.loadAtributos5(analisis.group());
			}
		}
		return dt_a.getAtributo6();
	}
			
	public File getFile() {
		return dt_a.getAtributo3();
	}
	public void setFile(File new_file) {
		dt_a.setAtributo3(new_file);
	}
	/**
	 * Lista los archivos de un directorio
	 * @return
	 */
	public String[] listFiles() {
		if(dt_a.getAtributo3().isDirectory()) {
			return dt_a.getAtributo3().list();
		}else {
			return null;
		}
	}
	/**
	 * Metodo para devolver un listado de archivos o de directorios
	 * @param tipo tipo==1 Retorna directorios, tipo==0 Retorna archivos
	 * @return String[]
	 */
	public List<String> listFilesoDirectory(int tipo) {
		dt_a.setAtributo5(listFiles());
		if(dt_a.getAtributo5().length>0) {
			for(String file:dt_a.getAtributo5()) {
				dt_a.setAtributo4(new File(dt_a.getAtributo3().getAbsolutePath()+"/"+file));
				if(tipo==1) {
					if(dt_a.getAtributo4().isDirectory()) {
						dt_a.loadAtributos5(file);
					}
				}else if(tipo==0){
					if(dt_a.getAtributo4().isFile()) {
						dt_a.loadAtributos5(file);
					}
				}
			}
		}
		return dt_a.getAtributo6();
		
	}
	/**
	 * Metodo para obtener un archivo del usando FileChooser
	 * @param pn
	 * @param extension
	 * @return
	 */
	public boolean getFileChooser(JFrame pn, String descripcion, String... extensiones) {
		JFileChooser file=new JFileChooser();
		file.setAcceptAllFileFilterUsed(false);
		file.addChoosableFileFilter(
			new FileNameExtensionFilter(descripcion, extensiones));
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int flag=file.showOpenDialog(pn);					//Abre la ventana para buscar el archivo si flag es igual a 1 se capturo
		if(flag==file.APPROVE_OPTION) {
			setFile(new File(file.getSelectedFile().getAbsolutePath()));
			return true; 
		}else {
			return false;
		}
	}

	

}
