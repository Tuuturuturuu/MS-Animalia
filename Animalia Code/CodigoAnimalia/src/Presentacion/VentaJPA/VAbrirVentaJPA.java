package Presentacion.VentaJPA;


import Presentacion.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import javax.swing.JFrame;

import Negocio.VentaJPA.TCarritoJPA;


public class VAbrirVentaJPA extends JFrame implements IGUI {
	
	public VAbrirVentaJPA(){
		initGUI();
	}
	
	 public void initGUI() {

	    	//Creamos un TCarrito
		 TCarritoJPA carrito = new TCarritoJPA();
	        ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_VENTA, carrito));

	    }
	 

	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
	
}