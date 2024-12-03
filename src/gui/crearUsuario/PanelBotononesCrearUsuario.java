package gui.crearUsuario;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotononesCrearUsuario extends JPanel implements ActionListener {
	
	private static final String CANCELAR = "cancelar";
	private static final String CREAR = "crear";
	
	private JButton butCancelar;
	private JButton butCrear;
	
	private VentanaCrearUsuario ventanaCrear;
	
	public PanelBotononesCrearUsuario() {
		setLayout(new FlowLayout());
		
		butCancelar = new JButton("Cancelar");
		butCancelar.addActionListener(this);
		butCancelar.setActionCommand(CANCELAR);
		butCancelar.setFont(new Font("Calibri", Font.PLAIN, 10));
		
		butCrear = new JButton("Crear");
		butCrear.addActionListener(this);
		butCrear.setActionCommand(CREAR);
		butCrear.setFont(new Font("Calibri", Font.PLAIN, 10));
		
		add(butCancelar);
		add(butCrear);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
