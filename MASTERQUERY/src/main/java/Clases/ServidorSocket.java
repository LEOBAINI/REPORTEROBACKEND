package Clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket implements Runnable{
	int port=0;
	boolean ocupado=false;
	
	public ServidorSocket(int puerto) {
		this.port=puerto;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				System.out.println("Escuchando en port "+port);
				Socket socket = serverSocket.accept();
				System.out.println("Estoy dando servicio, gracias por comprobar con telnet");
			}
		} catch (IOException e) {
			System.out.println("Port ocupado -> "+port);
			System.out.println(e.getMessage());
			
		}
		
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
