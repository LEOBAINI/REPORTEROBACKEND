package MASTERQUERY;

import Clases.Reporte;
import Clases.ReporteManager;

public class Masterquery {
	
	
	
	public static void main(String[] args) {
		Reporte reporte=new Reporte();
		reporte.setDatabase("");
		reporte.setIp("");
		reporte.setNombre("ElReporte2");
		reporte.setUsuarioSql("");
		reporte.setPasswordSql("");
		reporte.setRutaAlQuery("C:\\QueryInputs\\Detalle1.sql");
		reporte.setRutaSalida("C:\\QueryInputs");
		//reporte.ejecutar();
		ReporteManager.reportes.add(reporte);
		ReporteManager.correrReportes();
		
		
		
	}
	

}
