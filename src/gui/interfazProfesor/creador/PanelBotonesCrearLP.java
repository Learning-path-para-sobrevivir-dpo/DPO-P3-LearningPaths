package gui.interfazProfesor.Creador;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesCrearLP extends JPanel implements ActionListener{

	private static final String CREAR = "nuevo";
    private static final String CERRAR = "cerrar";

    private JButton bCrear;
    private JButton bCerrar;

    private VentanaCrearLP ventanaCrear;

    
    public PanelBotonesCrearLP(VentanaCrearLP ventanaCrear) {
		super();
		this.ventanaCrear = ventanaCrear;
		

        setLayout( new FlowLayout( ) );

        bCrear = new JButton("Crear Learning Path");
        bCrear.addActionListener(this);
        bCrear.setVisible(true);
        bCrear.setActionCommand(CREAR);
		bCrear.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.add(bCrear);

        bCerrar = new JButton("Cerrar");
        bCerrar.addActionListener(this);
        bCerrar.setVisible(true);
        bCerrar.setActionCommand(CERRAR);
		bCerrar.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.add(bCerrar);
	}


    
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( CREAR ) )
        {
            ventanaCrear.agregarLearningPath( );
			JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente!");

            ventanaCrear.cerrarVentana();
        }
        else if( comando.equals( CERRAR ) )
        {
            ventanaCrear.cerrarVentana( );
        }
    }
}
