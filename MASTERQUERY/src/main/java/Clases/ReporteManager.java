package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import MetodosSql.MetodosSql;

public class ReporteManager {
	public static ArrayList <Reporte> reportes=new ArrayList<Reporte>();
	 static MetodosSql metodos=new MetodosSql();
	
	public static void correrReportes() {
		for(int i=0;i<reportes.size();i++) {
			System.out.println("Comenzando a ejecutar reporte");
			reportes.get(i).ejecutar();
		}
		
	}
	/***
	 * Consulta a la base de datos y llena una lista en memoria para procesar
	 * @throws InterruptedException 
	 */
	
	public static void encolarReportesAprocesar()  {
			
			/*		
			Reporte reporte=new Reporte();
			reporte.setDatabase("monitordb");
			reporte.setIp("10.54.118.60");
			reporte.setNombre("ElReporteCrazy");
			reporte.setUsuarioSql("sa");
			reporte.setPasswordSql("anaconda1");
			reporte.setRutaAlQuery("C:\\QueryInputs\\Detalle1.sql");
			reporte.setRutaSalida("C:\\xampp\\htdocs\\REPORTERO\\Descargas");
			*/
			
			reportes=metodos.listarReportesSinProcesarDeLaBase();
		
	}
	/**
	 * Borra de la base de datos los reportes viejos, mas antiguos que n días
	 */
	public static void borrarColaReportesAntiguos() {
		
	}
	public static boolean hayReportesSinEncolar() {
		 String cantidad=metodos.consultarUnaCelda(
				 "select count(1) from colareportes where fechafinalizado is null");
		 if(cantidad.equalsIgnoreCase("0")) {
			 return false;
		 };
		return true;
	}
	
	

}
