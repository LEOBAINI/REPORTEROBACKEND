package MASTERQUERY.MASTERQUERY;


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
import java.util.Properties;
import MetodosSql.MetodosSql;
import configs.ReadPropertyFile;

public class Masterquery {
	static String rutaArchivo;
	static String horaBBDD;
	static int flagError = 0;
	
	
	public static void main(String[] args) {
		/*Properties props = ReadPropertyFile.getInstance().obtenerPropiedades();		
		String ipBase=props.getProperty("ipBase");
		String usuarioBase=props.getProperty("usuarioBase");
		String passwordBase=props.getProperty("passwordBase");	
		String baseDefault=props.getProperty("baseDefault");

		MetodosSql baseCCCC = new MetodosSql(ipBase,usuarioBase, passwordBase); 
		baseCCCC.setDatabase(baseDefault);
		
		System.out.println(baseCCCC.consultar("use monitordb;													\r\n" + 
				"select sy.cs_no,ali.alarminc_no,ali.event_id,e.descr,ali.system_no,ali.alarm_local_date,													\r\n" + 
				"(													\r\n" + 
				"case when (													\r\n" + 
				"	select count(1) from event_history evh with(nolock)												\r\n" + 
				"	where ali.alarminc_no=evh.alarminc_no												\r\n" + 
				"	and evh.event_id='FRN032')=0 then 'NO' else 'SI' END -- EVENTO SEGURO QUE GUARDA VIDEO, si mayor a 0 hubo eventos de video												\r\n" + 
				"	) TUVO_EVENTOS_FRN032_de_VIDEO												\r\n" + 
				"from alarm_incident ali with(nolock)													\r\n" + 
				"inner join event e with(nolock) on ali.event_id=e.event_id													\r\n" + 
				"inner join system sy on ali.system_no=sy.system_no													\r\n" + 
				"where ali.system_no in (													\r\n" + 
				"select system_no from system sy  with(nolock) where systype_id='FRONTL' or systype_id='RSI' or  systype_id='FRNTEL' or  systype_id='FRONTEL'  -- SOLO LOS SYSTEMS RSI													\r\n" + 
				")													\r\n" + 
				"and ali.alarm_local_date between getdate()-30 and getdate() -- DEL PERIODO DE TIEMPO													\r\n" + 
				""));
		
		
	}
*/

		File file=new File("C:\\Temporal1\\Detalle1.sql");
		//System.out.println(leerArchivo(file));
	//	System.out.println(file.list().length);
	//	File [] files=file.listFiles();
	//	mostrarfechacreacion(file);
		/*for(int i=0;i<files.length;i++) {
			mostrarfechacreacion(files[i]);
		}*/
		       String ip="una ip";
			   String usuario="un usuario";
			   String passw="una pass";
			   String query=leerArchivoSinSaltosDeLinea(file);
			   query="\"set nocount on; "+query+"\"";
			   if(query.contains("--")) {
				   System.out.println("Error, quite los comentarios de linea, y ponga entre barra y asterisco");
				   return;
			   }
			   String salida="C:\\xampp\\htdocs\\REPORTERO\\Descargas\\salida.txt";
			   String comando="sqlcmd -S "+ip+"  -d monitordb -U "+usuario+" -P  "+passw+" -Q "+query+"   -s \",\" -o "+salida;
			   System.out.println(comando);
			   Process proceso = null;
			try {
				proceso = Runtime.getRuntime().exec(comando);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   System.out.println(proceso.pid());
			   try {
				proceso.waitFor();
				System.out.println("Finalizado");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			 //  System.out.println(proceso.isAlive());
				
		
	}
	private static void mostrarfechacreacion(File file) {
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
	private static String leerArchivo(File file) {
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
	private static String leerArchivoSinSaltosDeLinea(File file) {
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

}
