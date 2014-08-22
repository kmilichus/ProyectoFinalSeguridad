

package Vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;



public class PanelInfo extends JPanel 
{



    private JLabel labClaveSeleccionada;
    private JLabel labLlaveGenerada;
    private JLabel HashArchivo;
    private JLabel labClaveSeleccionada1;
    private JLabel labLlaveGenerada1;
    private JLabel HashArchivo1;
    private JLabel Modificado;
    private JLabel info;
    private Ventana principal;
   
    
    
    public PanelInfo(Ventana principal )
    {
     
    	this.principal = principal;
        //setLayout( new BorderLayout() );
        setPreferredSize(new Dimension (550, 250));
        
 
        
        setBorder( new TitledBorder( "Inforamacion Generada del Archivo" ));
        setLayout (new GridLayout(5,2));
        
        //Información de la pelicula
        labClaveSeleccionada1 = new JLabel( "El usuario ingreso la palabra: " );
        labClaveSeleccionada = new JLabel();
        labLlaveGenerada1 = new JLabel( "Se genero la llave: " );
        labLlaveGenerada = new JLabel();
        HashArchivo1 = new JLabel( "Hash Generado: " );
        HashArchivo = new JLabel();
        Modificado = new JLabel();
        info = new JLabel("Encriptando con AES 128 BITS");
        JLabel x = new JLabel(" ");
        
        add( labClaveSeleccionada1 );
    	add( labClaveSeleccionada );
    	add( labLlaveGenerada1 );
     	add( labLlaveGenerada );
     	add( HashArchivo1 );
     	add( HashArchivo );
     	add( Modificado );
     	add( x );
     	add( info );
     	
     	
      

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    public void refrescarPanelInfo(){
   
    	labClaveSeleccionada.setText(principal.mundo.clave);
    	labLlaveGenerada.setText(principal.mundo.llaveGenerada);
    	HashArchivo.setText(principal.mundo.hash);
    	
    }
    

}
