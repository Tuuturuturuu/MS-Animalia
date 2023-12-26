package Presentacion.Factura;

import javax.swing.*;

import Integracion.Factura.TLineaFactura;
import Negocio.Factura.TCarrito;
import Presentacion.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class VAbrirFactura extends JFrame implements IGUI {

    public VAbrirFactura() {
        initGUI();
    }

    public void initGUI() {

    	//Creamos un TCarrito
    	TCarrito carrito = new TCarrito();
        ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_FACTURA, carrito));

    }


    public void actualizar() {
    }

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}

}
