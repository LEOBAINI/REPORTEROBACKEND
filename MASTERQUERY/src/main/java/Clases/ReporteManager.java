package Clases;

import java.util.ArrayList;

public class ReporteManager {
	public static ArrayList <Reporte> reportes=new ArrayList<Reporte>();
	
	public static void correrReportes() {
		for(int i=0;i<reportes.size();i++) {
			reportes.get(i).ejecutar();
		}
		
	}
	

}
