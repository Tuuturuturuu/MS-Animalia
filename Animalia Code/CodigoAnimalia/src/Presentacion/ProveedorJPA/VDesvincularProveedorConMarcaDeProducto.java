/**
 * 
 */
package Presentacion.ProveedorJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.ProveedorJPA.TProveedorConMarcas;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VDesvincularProveedorConMarcaDeProducto extends JFrame implements IGUI{
	public VDesvincularProveedorConMarcaDeProducto() {
		super("Desvincular Proveedor de Marca de Producto");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		// this.setBounds(100, 100, 1000, 525);
		// this.setBounds(100, 100, 650, 430);
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

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
	            "Introduzca los datos del proveedor y de la marca de producto que desea desvincular ",
	            1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir el ID del proveedor
	    JLabel labelProveedorID = new JLabel("ID del Proveedor:");
	    labelProveedorID.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelProveedorID);

	    JTextField textProveedorID = new JTextField(20);
	    textProveedorID.setMaximumSize(textProveedorID.getPreferredSize());
	    mainPanel.add(textProveedorID);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el ID de la marca
	    JLabel labelmarcaID = new JLabel("ID de la marca:");
	    labelmarcaID.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelmarcaID);

	    JTextField textMarcaID = new JTextField(20);
	    textMarcaID.setMaximumSize(textMarcaID.getPreferredSize());
	    mainPanel.add(textMarcaID);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VDesvincularProveedorConMarcaDeProducto.this.setVisible(false);
	        	try {
	                int proveedorID = Integer.parseInt(textProveedorID.getText());
	                int marcaID = Integer.parseInt(textMarcaID.getText());
	             
	                ApplicationController.getInstance().manageRequest(new Context(Evento.DESVINCULAR_PROVEEDOR_DE_MARCA, new TProveedorConMarcas(proveedorID, marcaID)));
	            } catch (Exception ex) {
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
	            }
	        }
	    });
	    panelBotones.add(botonAceptar);
	    
	    //BOTON CANCELAR
	    JButton botonCancelar = new JButton("Cancelar");
	    botonCancelar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VDesvincularProveedorConMarcaDeProducto.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPROVEEDOR, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
		
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.DESVINCULAR_PROVEEDOR_DE_MARCA_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.DESVINCULAR_PROVEEDOR_DE_MARCA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); 
		
	}
}