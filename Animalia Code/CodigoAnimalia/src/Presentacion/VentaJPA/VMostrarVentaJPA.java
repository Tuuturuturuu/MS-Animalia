
package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Negocio.VentaJPA.TVentaConProductos;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VMostrarVentaJPA extends JFrame implements IGUI{

	 public VMostrarVentaJPA() {
	        super("Mostrar Venta");
	        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	        int ancho = 430;
	        int alto = 330;
	        int x = (pantalla.width - ancho) / 2;
	        int y = (pantalla.height - alto) / 2;
	        this.setBounds(x, y, ancho, alto);
	        this.setLayout(null);
	        this.setResizable(false);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        initGUI();
	    }

	    private void initGUI() {
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        this.setContentPane(mainPanel);
	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        JLabel msgIntroIDCabecera = ComponentsBuilder
	                .createLabel("Introduzca el ID de la Venta a mostrar ", 1, 10, 80, 20, Color.BLACK);
	        msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	        mainPanel.add(msgIntroIDCabecera);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

	        JPanel panelID = new JPanel();
	        mainPanel.add(panelID);

	        JLabel labelID = ComponentsBuilder.createLabel("ID Venta: ", 10, 100, 80, 20, Color.BLACK);
	        panelID.add(labelID);

	        JTextField id = new JTextField();
	        id.setPreferredSize(new Dimension(250, 30));

	        id.setEditable(true);
	        panelID.add(id);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

	        JPanel panelBotones = new JPanel();
	        mainPanel.add(panelBotones);

	        // BOTON ACEPTAR
	        JButton botonAceptar = new JButton("Aceptar");
	        botonAceptar.setBounds(75, 50, 100, 100);
	        botonAceptar.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	VMostrarVentaJPA.this.setVisible(false);
	                try {
	                	Integer id_venta = Integer.parseInt(id.getText());
	                    ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_VENTA, 
	                    		!id.getText().isEmpty()? id_venta: 0));

	                } catch (Exception ex) {
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
	                }

	            }
	        });
	        panelBotones.add(botonAceptar);

	        JButton botonCancelar = new JButton("Cancelar");
	        botonCancelar.setBounds(200, 50, 100, 100);
	        botonCancelar.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	VMostrarVentaJPA.this.setVisible(false);
	                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));

	            }
	        });
	        panelBotones.add(botonCancelar);

	        this.setVisible(true);
	        this.setResizable(true);
	    }
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MOSTRAR_VENTA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.MOSTRAR_VENTA_COMPLETA, (TVentaConProductos) res.getDatos()));

		
		} else if (res.getEvento() == Evento.MOSTRAR_VENTA_KO) {
			
			TFactura factura = (TFactura) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) factura.GetId()));
		}
		dispose(); //Liberamos recursos de la memoria
	}
}