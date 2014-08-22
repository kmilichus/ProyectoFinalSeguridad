

package Vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;



public class PanelBotones extends JPanel implements ActionListener{

	public static final String ENCRIPTAR= "en";
	public static final String DESENCRIPTAR= "des";


	private JLabel labClave;
	private JLabel labRutaArchivo;
	private JLabel labRutaEscritura;
	public JPasswordField textClave;
	public JTextField textRutaArchivo;
	public JTextField textRutaEscritura;
	private JButton encriptar;
	private JButton desencriptar;
	private Ventana principal;




	public PanelBotones(Ventana x)
	{
		principal=x;

		//setLayout( new BorderLayout() );
		setPreferredSize(new Dimension (400, 250));



		setBorder( new TitledBorder( "Inforamacion Generada del Archivo" ));
		setLayout (new GridLayout(4,2));


		labClave = new JLabel( "Digite su clave: " );
		textClave = new JPasswordField();

		labRutaArchivo = new JLabel( "Ruta del archivo para cifrar " );
		textRutaArchivo = new JTextField();

		labRutaEscritura = new JLabel( "Ruta para Escribir " );
		textRutaEscritura = new JTextField();
		
		encriptar= new JButton();
		encriptar.setText( "Encripar Archivo" );
		encriptar.setActionCommand( ENCRIPTAR );
		encriptar.addActionListener( this );
		
		desencriptar= new JButton();
		desencriptar.setText( "Desencripar Archivo" );
		desencriptar.setActionCommand( DESENCRIPTAR );
		desencriptar.addActionListener( this );


		add(labClave);
		add(textClave);
		add(labRutaArchivo);
		add(textRutaArchivo);
		add(labRutaEscritura);
		add(textRutaEscritura);
		add(encriptar);
		add(desencriptar);

		




	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	public void refrescarPanelInfo(){


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String c = e.getActionCommand();
		
		if (ENCRIPTAR.equalsIgnoreCase(c)) {
		//	principal.cargarDeArchivo();
			
			principal.mundo.generarClaves(textClave.getPassword(), textRutaArchivo.getText(), textRutaEscritura.getText());
			principal.refrescarInfo();
		}else {
			if (DESENCRIPTAR.equalsIgnoreCase(c)) {
				
				principal.mundo.desencriptar(textRutaArchivo.getText(), textRutaEscritura.getText());
				principal.iguales();
				
			}
		}
		
	}


}
