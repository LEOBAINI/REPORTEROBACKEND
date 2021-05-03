package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;

import MetodosSql.MetodosSql;

public class Utilidades {
	
	public static void mostrarfechacreacion(File file) {
		try {
			BasicFileAttributes attrs;
		    attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		    FileTime time = attrs.lastModifiedTime();//creationTime();
		    
		    String pattern = "yyyy-MM-dd HH:mm:ss";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
		    String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );

		    System.out.println( "La fecha y hora de modificación del archivo "+file.getName()+" es: " + formatted );
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public static String leerArchivo(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String content = stringBuilder.toString();
		return content;
	}
	public static String leerArchivoSinSaltosDeLinea(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line+" ");
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String content = stringBuilder.toString();
		return content;
	}
	public static String formatearQuery(String query) {
		
		return "\"set nocount on; "+query+"\"";
	}
	public static String getComando(Reporte repo) {
		String comando;
		comando="sqlcmd -S "+repo.getIp()+"  -d "+ repo.getDatabase() +" -U "+repo.getUsuarioSql()+" -P  "+repo.getPasswordSql()+" -Q "+repo.getQuery()+"   -s \",\" -W -o "+repo.getRutaSalida()+"\\"+repo.getNombre()+"_"+MetodosSql.dameFechaDeHoy()+repo.getExtension();
		repo.setRutaAlArchivoGenerado(repo.getRutaSalida()+"\\"+repo.getNombre()+"_"+MetodosSql.dameFechaDeHoy()+repo.getExtension());
		return comando;
	}
	private String rutaDeSalida;
	
	public String setRutaArchivoSalida(String nombre) {
		
		return this.rutaDeSalida+nombre+MetodosSql.dameFechaDeHoy()+".txt";
	}
	public static void comprimir(String rutaArchivo) {
		File file=new File(rutaArchivo);
		Process proceso = null;
		String archivo7Z=rutaArchivo.replace("txt", "7z");
		
		
		
		String comando="7z a -t7z "+archivo7Z+" "+rutaArchivo;
		try {
			System.out.println("Comprimiendo..."+file.getName()+" con el comando "+comando);
			proceso=Runtime.getRuntime().exec(comando);
			try {
				proceso.waitFor();
				file.delete();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void avisarArchivoListo() {
		System.out.println("El archivo está listo, enviar mail...");
		
	}
	

}
