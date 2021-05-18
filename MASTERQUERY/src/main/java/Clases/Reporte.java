package Clases;

import java.io.File;
import java.io.IOException;


public class Reporte {
	String idcolaReportes;
	String usuarioSql;
	String nombre;
	private String query;
	long windowsProcessId;
	String horaEjecucion;
	String estaEnEjecucion;	
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
				
				proceso.waitFor();
				//System.out.println(proceso.pid());
				System.out.println(proceso.exitValue());	
				System.out.println(getRutaAlArchivoGenerado());					
				Utilidades.comprimir(getRutaAlArchivoGenerado());
				Utilidades.avisarArchivoListo(this);
				
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
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
	public void setFechaHoraEjecucion(String horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}
	
	public void setEstaEnEjecucion(String string) {
		this.estaEnEjecucion = string;
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

	public String getIdcolaReportes() {
		return idcolaReportes;
	}

	public void setIdcolaReportes(String string) {
		this.idcolaReportes = string;
	}
	


	

}
