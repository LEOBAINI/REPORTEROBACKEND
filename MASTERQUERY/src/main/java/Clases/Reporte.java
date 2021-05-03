package Clases;

import java.io.File;
import java.io.IOException;


public class Reporte {
	String usuarioSql;
	String nombre;
	String query;
	long windowsProcessId;
	String horaEjecucion;
	boolean estaEnEjecucion;	
	String ip;
	String passwordSql;
	String rutaSalida;
	String database;
	String rutaAlQuery = null;
	String rutaAlArchivoGenerado;
	
	
	public Reporte() {		       
		
	}	
	
	public void ejecutar() {
		File rutaQuery=new File(rutaAlQuery);
		this.query=Utilidades.leerArchivoSinSaltosDeLinea(rutaQuery);	
		this.query=Utilidades.formatearQuery(this.query);
		if(query.contains("--")) {
				   System.out.println("Error, quite los comentarios de linea, y ponga entre barra y asterisco");
				   return;
			   }
			   
			   String comando=Utilidades.getComando(this);
			   System.out.println("Comando a ejecutar  "+comando);
			   Process proceso = null;
			try {
				proceso = Runtime.getRuntime().exec(comando);
				try {
					setWindowsProcessId(proceso.pid());
					setEstaEnEjecucion(true);
					proceso.waitFor();
					setEstaEnEjecucion(false);
					System.out.println(proceso.exitValue());	
					System.out.println(getRutaAlArchivoGenerado());					
					Utilidades.comprimir(getRutaAlArchivoGenerado());
					Utilidades.avisarArchivoListo();
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
			  
		
				
		
	}
	public String getUsuarioSql() {
		return usuarioSql;
	}
	public void setUsuarioSql(String usuarioSql) {
		this.usuarioSql = usuarioSql;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		
		this.query = query;
	}
	public long getWindowsProcessId() {
		return windowsProcessId;
	}
	public void setWindowsProcessId(long l) {
		this.windowsProcessId = l;
	}
	public String getHoraEjecucion() {
		return horaEjecucion;
	}
	public void setHoraEjecucion(String horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}
	public boolean isEstaEnEjecucion() {
		return estaEnEjecucion;
	}
	public void setEstaEnEjecucion(boolean estaEnEjecucion) {
		this.estaEnEjecucion = estaEnEjecucion;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPasswordSql() {
		return passwordSql;
	}
	public void setPasswordSql(String passwordSql) {
		this.passwordSql = passwordSql;
	}
	public String getRutaSalida() {
		return rutaSalida;
	}
	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}

	public String getRutaAlQuery() {
		return rutaAlQuery;
	}

	public void setRutaAlQuery(String rutaAlQuery) {
		this.rutaAlQuery = rutaAlQuery;
	}

	public String getExtension() {
	
		return ".txt";
	}

	public String getRutaAlArchivoGenerado() {
		return rutaAlArchivoGenerado;
	}

	public void setRutaAlArchivoGenerado(String rutaAlArchivoGenerado) {
		this.rutaAlArchivoGenerado = rutaAlArchivoGenerado;
	}
	


	

}
