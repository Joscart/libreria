package libreria;

import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

public class SerialComm extends Thread{
	private char valor = '0';
	private SerialPort port;
	private boolean pausa = false;
	private boolean fin = false;

	public SerialPort openConn() {
		SerialPort [] ports=SerialPort.getCommPorts();
		for(SerialPort port:ports) {
			System.out.println(port.getSystemPortName());
		}
		this.port=ports[0];//Carga el puerto de conexión con Arduino
		if(port.openPort()) {
			System.out.println("Puerto serial abierto");
		}else {
			System.out.println("Puerto serial ocupado");
		}
		port.setComPortParameters(9600, 8, 1, 0);
		return port;
	}

	public boolean closeConn() {
		return port.closePort();
	}

	public synchronized char getDato() {
		return valor;
	}

	public synchronized void setDato(char valor_) {
		this.valor=valor_;
	}

	public synchronized void pausar() {
		pausa = true;
	}

	public synchronized void reanudar() {
		pausa = false;
		notify();
	}
	
	public synchronized void finalizar() {
		fin = true;
	}

	public synchronized void esperar() {
		while (pausa) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		openConn();//abrir la conexion serial
		while(true) {

			esperar();

			PrintWriter output=new PrintWriter(port.getOutputStream());
			output.print(getDato());
			output.flush();
			
			if (fin) {
                closeConn();
                break;
			}

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}