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

import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VModificarProveedorJPA extends JFrame implements IGUI {
	public VModificarProveedorJPA() {
		super("Modificar Proveedor");
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
		
		// ID PROVEEDOR
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID y los datos del Proveedor que quiera modificar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Proveedor: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField textIdProveedor = new JTextField();
		textIdProveedor.setPreferredSize(new Dimension(250, 30));
		textIdProveedor.setEditable(true);
		panelID.add(textIdProveedor);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//CIF PROVEEDOR
		JPanel panelCIF = new JPanel();
		mainPanel.add(panelCIF);
		JLabel labelCIF = ComponentsBuilder.createLabel("CIF: ", 10, 100, 80, 20, Color.BLACK);
		panelCIF.add(labelCIF);

		JTextField textCIF = new JTextField();
		textCIF.setPreferredSize(new Dimension(250, 30));
		textCIF.setEditable(true);
		panelCIF.add(textCIF);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// NOMBRE PROVEEDOR
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField textNombre = new JTextField();
		textNombre.setPreferredSize(new Dimension(250, 30));
		textNombre.setEditable(true);
		panelNombre.add(textNombre);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// TELEFONO PROVEEDOR
		JPanel paneltelefono = new JPanel();
		mainPanel.add(paneltelefono);

		JLabel labelTelefono = ComponentsBuilder.createLabel("Telefono: ", 10, 100, 80, 20, Color.BLACK);
		paneltelefono.add(labelTelefono);

		JTextField textTelefono = new JTextField();
		textTelefono.setPreferredSize(new Dimension(250, 30));
		textTelefono.setEditable(true);
		paneltelefono.add(textTelefono);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));		

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				           
				VModificarProveedorJPA.this.setVisible(false);
				try {
					Integer id_proveedor = Integer.parseInt(textIdProveedor.getText());
	            	String CIF = textCIF.getText();
	            	String nombre_proveedor = textNombre.getText();
		            Integer telefono = Integer.parseInt(textTelefono.getText());

		            TProveedor tproveedor = new TProveedor(
        					!textIdProveedor.getText().isEmpty()? id_proveedor: 0, 
        					CIF != null ? CIF: "",
        					nombre_proveedor != null ? nombre_proveedor: "", 
        					!textTelefono.getText().isEmpty() ? telefono:0, 
        					true);
		            
		            //Vamos a tratar los errores en caso de ingresar campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_PROVEEDOR, tproveedor));
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
				VModificarProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context (Evento.CREAR_VPROVEEDOR, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}
	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_PROVEEDOR_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_PROVEEDOR_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -78));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
		
	}
}