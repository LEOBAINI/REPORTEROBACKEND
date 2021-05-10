package MASTERQUERY;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Clases.Reporte;
import Clases.ReporteManager;
import Clases.ServidorSocket;
import MetodosSql.MetodosSql;

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
        	
        	 

        //	 while(ReporteManager.hayReportesSinEncolar())//si da true sigue
       // 	 {
        	 ReporteManager.encolarReportesAprocesar();
        	 ReporteManager.correrReportes();
        	 ReporteManager.borrarColaReportesAntiguos();
        	 System.out.println("Trabajando");
        //	 }
        	 System.out.println("No hay mas reportes, saliendo...");
        	 System.exit(0);
        	 
        }else {
        	System.out.println("Ya hay una instancia corriendo, saliendo del programa");
        	//sino logra abrir port, salir 
        	
        }
     
		
		
	}

	
	

}

