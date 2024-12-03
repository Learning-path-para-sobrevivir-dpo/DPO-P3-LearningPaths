package gui;

import javax.swing.JFrame;

import persistencia.ManejoDatos;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	public static void main(String[] args) {
		ManejoDatos datos = new ManejoDatos();
		VentanaPrincipal ventana = new VentanaPrincipal(datos);
	}
	
	public VentanaPrincipal(ManejoDatos datos) {
		
	}
	
	
}
