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
		try {
		for(int i=0;i<reportes.size();i++) {
			System.out.println("Comenzando a ejecutar reporte");
			reportes.get(i).ejecutar();
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	/***
	 * Consulta a la base de datos y llena una lista en memoria para procesar
	 * @throws InterruptedException 
	 */
	
	public static void encolarReportesAprocesar()  {
			
			try {			
			reportes=metodos.listarReportesSinProcesarDeLaBase();
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
				
			}
		
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
