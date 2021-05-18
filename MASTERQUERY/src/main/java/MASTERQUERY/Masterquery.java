package MASTERQUERY;

import java.net.ServerSocket;
import Clases.ReporteManager;
import Clases.ServidorSocket;

public class Masterquery {
	
	private static ServerSocket socket; 
	/*Hacer que el port sea configurable*/
	
	public static void main(String[] args) {
		
		
		Runnable nuevoServidor = new ServidorSocket(9090);
        Thread hilo = new Thread(nuevoServidor);
     // Intentará iniciar, si el puerto está ocupado, saldrá con system.exit(1)
     // Así nos aseguramos una sola instancia.   
        hilo.start(); 
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(hilo.isAlive()) {
        	//Si logró abrir el puerto de escucha, iniciar aplicativo.
        	 System.out.println("Programa en ejecucion, comenzando tareas de generacion de reportes");
        	       	 
        	 procesarReportesEncolados();
        	 
        	 System.out.println("No hay mas reportes, saliendo...");
        	 System.exit(0);
        	 
        }else {
        	System.out.println("Ya hay una instancia corriendo, saliendo del programa");
        	//sino logra abrir port, salir 
        	
        }
     
		
		
	}
	
	public static void procesarReportesEncolados(){
		System.out.println("Encolando reportes en memoria para procesar");
		 ReporteManager.encolarReportesAprocesar();
		 System.out.println("Ejecutando reportes encolados");
    	 ReporteManager.correrReportes();
    	 System.out.println("Borrando reportes viejos");
    	 ReporteManager.borrarColaReportesAntiguos();
    	 
    	 if(ReporteManager.hayReportesSinEncolar()) {
    		 procesarReportesEncolados();
    	 }
	}

	
	

}

