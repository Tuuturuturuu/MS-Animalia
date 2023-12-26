/**
 * 
 */
package Presentacion.ProveedorJPA;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VListarProveedoresJPA extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public VListarProveedoresJPA(List<TProveedor> list) {
		super("Mostrar todos los Proveedores");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((List<TProveedor>) list);
	}

	private void initGUI(List<TProveedor> listaProveedores) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VListarProveedoresJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPROVEEDOR, 
            			null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "CIF", "Nombre","Telefono", "Activo" };
		JTable tabla = ComponentsBuilder.createTable(listaProveedores.size(), 5, nombreColumnas);
		int i = 0;
		for (TProveedor p : listaProveedores) {
			tabla.setValueAt(p.getId(), i, 0);
			tabla.setValueAt(p.getCIF(), i, 1);
			tabla.setValueAt(p.getNombre(), i, 2);
			tabla.setValueAt(p.getTelefono(), i, 3);
			tabla.setValueAt(p.isActivo(), i, 4);
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);

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
		// TODO Auto-generated method stub
	}
}