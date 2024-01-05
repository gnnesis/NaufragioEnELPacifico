package main;

import javax.swing.JOptionPane;

import db.BBDD;
import gui.PantallaInicio;

public class NaufragioEnElPacifico {

	public static void main(String[] args) {    	
    	new BBDD().checkEstado().thenAccept(correcto -> {
    		if(!correcto)
    		{
    			JOptionPane.showMessageDialog(null, "No se ha podido establecer conexion con la base de datos");
    		}
    	});

    	new PantallaInicio();
    }
}
