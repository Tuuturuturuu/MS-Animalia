package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;


import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VModificarVentaJPA extends JFrame implements IGUI{

	 public VModificarVentaJPA() {
	        super("Modificar Venta");
	        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	        int ancho = 630;
	        int alto = 430;
	        int x = (pantalla.width - ancho) / 2;
	        int y = (pantalla.height - alto) / 2;
	        this.setBounds(x, y, ancho, alto);
	        this.setLayout(null);
	        this.setResizable(false);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        initGUI();
	    }

	    public void initGUI() {
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        this.setContentPane(mainPanel);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // ID
	        JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID de la venta que quiere modificar ",
	                1, 10, 80, 20, Color.BLACK);
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

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // Fecha de Compra
	        JPanel panelFechaCompra = new JPanel();
	        mainPanel.add(panelFechaCompra);

	        JLabel labelFechaCompra = ComponentsBuilder.createLabel("ID Trabajador", 10, 100, 200, 20, Color.BLACK);
	        panelFechaCompra.add(labelFechaCompra);

	        JTextField idTrabajador = new JTextField();
	        idTrabajador.setPreferredSize(new Dimension(250, 30));
	        idTrabajador.setEditable(true);
	        panelFechaCompra.add(idTrabajador);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	        
	      //Panel para seleccionar el tipo de pago a realizar
			JPanel panelModoPago = new JPanel();
			mainPanel.add(panelModoPago);

			JLabel labelModoPago = ComponentsBuilder.createLabel("                Modo de Pago: ", 10, 100, 80, 20,
					Color.BLACK);
			panelModoPago.add(labelModoPago);

			JComboBox<String> tipoPago = new JComboBox<String>();
			tipoPago.addItem("Tarjeta de Crédito/Débito");
			tipoPago.addItem("Pago en Efectivo");
			tipoPago.addItem("Transferencia Bancaria");
			tipoPago.setPreferredSize(new Dimension(250, 25));
			panelModoPago.add(tipoPago);

			mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        JPanel panelBotones = new JPanel();
	        mainPanel.add(panelBotones);
	        

	        JButton botonAceptar = new JButton("Aceptar");
	        botonAceptar.setBounds(75, 50, 100, 100);
	        botonAceptar.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	VModificarVentaJPA.this.setVisible(false);

	                try {
	                    TVenta venta = new TVenta();
		            	venta.setId(Integer.parseInt(id.getText()));
		            	venta.setPrecio_total(0.0);
		            	venta.setIdTrabajador(Integer.parseInt(idTrabajador.getText()));
		            	venta.setMetodo_pago(tipoPago.getSelectedItem().toString());
	                    ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_VENTA, venta));
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
	            	VModificarVentaJPA.this.setVisible(false);
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
		if (res.getEvento() == Evento.MODIFICAR_VENTA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_VENTA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
	}
	
}