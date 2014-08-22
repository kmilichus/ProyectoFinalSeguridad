package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Mundo.Cripto;

public class Ventana extends JFrame {


	public Cripto mundo;
	private PanelImagen panelImagen;
	private PanelInfo panelInfo;
	private PanelBotones panelB;




	public Ventana() throws HeadlessException {


		setLayout( new BorderLayout( ) );
		setTitle( "Proyecto Final Seguridad" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable(true);
		setSize(new Dimension(950,600));

		this.panelImagen = new PanelImagen();

		add(BorderLayout.NORTH, panelImagen);

		panelInfo = new PanelInfo(this);

		add( panelInfo, BorderLayout.WEST );
		
		panelB= new PanelBotones(this);
		add( panelB, BorderLayout.CENTER );
		
		mundo = new Cripto();
		


	}
	
	public void cargarDeArchivo(){
		try{
			File archivo= cargarArchivo();
			System.out.println(archivo.getAbsolutePath());
			
			
			pack();
			}catch(Exception e){
				e.printStackTrace();
				int respuesta = JOptionPane.showConfirmDialog(this, "Hubo un error al cargar el archivo.\n¿Desea salir del programa?");
				if(respuesta==JOptionPane.YES_OPTION){
					System.exit(0);
				}
				
			}
    }


	private File cargarArchivo()throws Exception{
		boolean cargaBien = false;
		File salida = null;
		while(!cargaBien){
			JFileChooser fc = new JFileChooser("./data");
			fc.setDialogTitle("Abrir el archivo");
			int resultado = fc.showOpenDialog(this);
			if(resultado == JFileChooser.APPROVE_OPTION){
				salida = fc.getSelectedFile();
				cargaBien=true;
			}
		}

		if (salida == null){
			throw new Exception("No se eligió un archivo");
		}
		return salida;
	}


	public static void main(String[] args) {
		Ventana x = new Ventana();
		x.setVisible(true);
	}

	public void refrescarInfo() {
		this.panelInfo.refrescarPanelInfo();
		
	}

	public void iguales() {
		String respuesta ="";
		if (mundo.hash.equals(mundo.hash2)) {
			respuesta = "NO SE CAMBIO EL ARCHIVO";
		}else {
			respuesta = "ARCHIVO MODIFICADO";
		}
		
		JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}



}
