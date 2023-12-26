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

import Negocio.Especie.TEspecie;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VMostrarProveedorJPA extends JFrame implements IGUI {

	public VMostrarProveedorJPA() {
		super("Mostar Proveedor");
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
				.createLabel("Introduzca el ID del Proveedor que quiere que se muestre ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textId = ComponentsBuilder.createLabel("ID Proveedor: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textId);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarProveedorJPA.this.setVisible(false);
				try {
					Integer id_Proveedor = Integer.parseInt(id.getText());
					//Vamos a tratar el error de campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_PROVEEDOR, 
	            			!id.getText().isEmpty()? id_Proveedor: 0));

				} catch (Exception ex) {
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -5));
				}

			}
		});
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			 @Override
		        public void actionPerformed(ActionEvent e) {
				 VMostrarProveedorJPA.this.setVisible(false);
		            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPROVEEDOR, null));
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
				if (res.getEvento() == Evento.MOSTRAR_PROVEEDOR_OK) {
					TProveedor prov = (TProveedor) res.getDatos();
					String msj = "ID: "+ prov.getId() + ", CIF: " + prov.getCIF() + ", Nombre: " + prov.getNombre() + ", Telefono: "+ prov.getTelefono() + ", Activo: "+ prov.isActivo();
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_UNO, msj));
				
				} else if (res.getEvento() == Evento.MOSTRAR_PROVEEDOR_KO) {
					
					TEspecie especie = (TEspecie) res.getDatos();
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) especie.getID()));
				}
				dispose(); //Liberamos recursos de la memoria
				// end-user-code
		
	}
}