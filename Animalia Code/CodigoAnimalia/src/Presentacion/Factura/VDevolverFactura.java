package Presentacion.Factura;

import javax.swing.*;

import Integracion.Factura.TLineaFactura;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VDevolverFactura extends JFrame implements IGUI {

    private JTextField idFacturaField;
    private JTextField idPaseField;
    private JTextField cantidadField;

    public VDevolverFactura() {
        super("Devolver Factura");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 400;
        int alto = 250;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ID de la Factura
        JPanel panelIDFactura = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(panelIDFactura);

        JLabel labelIDFactura = ComponentsBuilder.createLabel("ID Factura: ", 10, 10, 80, 20, Color.BLACK);
        panelIDFactura.add(labelIDFactura);

        idFacturaField = new JTextField();
        idFacturaField.setPreferredSize(new Dimension(250, 30));
        panelIDFactura.add(idFacturaField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ID del Pase
        JPanel panelIDPase = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(panelIDPase);

        JLabel labelIDPase = ComponentsBuilder.createLabel("ID Pase: ", 10, 10, 80, 20, Color.BLACK);
        panelIDPase.add(labelIDPase);

        idPaseField = new JTextField();
        idPaseField.setPreferredSize(new Dimension(250, 30));
        panelIDPase.add(idPaseField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Cantidad a Devolver
        JPanel panelCantidad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(panelCantidad);

        JLabel labelCantidad = ComponentsBuilder.createLabel("Cantidad a Devolver: ", 10, 10, 150, 20, Color.BLACK);
        panelCantidad.add(labelCantidad);

        cantidadField = new JTextField();
        cantidadField.setPreferredSize(new Dimension(250, 30));
        panelCantidad.add(cantidadField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(panelBotones);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VDevolverFactura.this.setVisible(false);

                try {
                    int idFactura = Integer.parseInt(idFacturaField.getText());
                    int idPase = Integer.parseInt(idPaseField.getText());
                    int cantidad = Integer.parseInt(cantidadField.getText());

                    TLineaFactura lf = new TLineaFactura();
                    lf.SetIdFactura(idFactura);
                    lf.SetIdPase(idPase);
                    lf.SetCantidad(cantidad);
                    
                    ApplicationController.getInstance().manageRequest(new Context(Evento.DEVOLVER_FACTURA, lf));
                } catch (NumberFormatException ex) {
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
                }
            }
        });
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VDevolverFactura.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
    }

    public void actualizar() {
    }

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.DEVOLVER_FACTURA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_DEVOLVER_FACTURA_CORRECT, null));
		} else if (res.getEvento() == Evento.DEVOLVER_FACTURA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
		
	}
}
