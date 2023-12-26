/**
 * 
 */
package Presentacion.Pase;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Negocio.Pase.TPase;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author PC
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class VMostrarPase extends JFrame implements IGUI {
	
	private Set<JDialog> jDialog;
	private Set<JTextField> jTextField;
	private Set<JPanel> jPanel;
	private Set<JButton> jButton;
	private Set<JLabel> jLabel;

	public VMostrarPase(){
		super("Mostar Pase");
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
	
	private void initGUI(){
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID del Pase que quiere que se muestre ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textIdPase = ComponentsBuilder.createLabel("ID Pase: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdPase);

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
		        VMostrarPase.this.setVisible(false);
				try {
					Integer id_Pase = Integer.parseInt(id.getText());
					//Vamos a tratar el error de campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_PASE, 
	            			!id.getText().isEmpty()? id_Pase: 0));

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
		            VMostrarPase.this.setVisible(false);
		            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE, null));
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

	/** 
	* (non-Javadoc)
	* @see ActionListener#actionPerformed(ActionEvent e)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void actionPerformed(ActionEvent e) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.MOSTRAR_PASE_OK) {
			TPase pase = (TPase) res.getDatos();
			String msj = "ID: "+ pase.getID() + " ,Id Habitat: " + pase.getIDHabitat() + " ,Precio: "+ pase.getPrecio() + " ,Cantidad: "+ pase.getCantidadDisponible() + ", Fecha: "+ pase.getFecha()+ " ,Hora: " + pase.getHora() + " ,Activo: "+ pase.getActivo();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_UNO, msj));
		
		} else if (res.getEvento() == Evento.MOSTRAR_PASE_KO) {
			
			TPase pase = (TPase) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) pase.getID()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}
}