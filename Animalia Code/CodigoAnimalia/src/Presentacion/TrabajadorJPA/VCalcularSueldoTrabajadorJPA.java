package Presentacion.TrabajadorJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;

public class VCalcularSueldoTrabajadorJPA extends JFrame implements IGUI {

    private JTextField textSueldo;
    private JButton botonAceptar, botonCancelar;
    private JPanel mainPanel, panelBotones;
    private JLabel msgIntroIDCabecera, labelSueldo;

    public VCalcularSueldoTrabajadorJPA() {
        super("Calcular sueldo trabajador");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 1000;
        int alto = 525;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }
    
    private void initGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID del trabajador para calcular el sueldo:", 1, 10, 80, 20, Color.BLACK);
        msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(msgIntroIDCabecera);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textId = ComponentsBuilder.createLabel("ID Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textId);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        panelBotones = new JPanel();
        mainPanel.add(panelBotones);
        
        botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            try {
            		Integer idValue = Integer.parseInt(id.getText());
	                VCalcularSueldoTrabajadorJPA.this.setVisible(false);
	                ApplicationController.getInstance().manageRequest(new Context(Evento.CALCULAR_SUELDO_TRABAJADOR, 
	                		!textId.getText().isEmpty() ? idValue:0));
	            } catch (Exception ex) {
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
	            }
            }
        });
        panelBotones.add(botonAceptar);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VCalcularSueldoTrabajadorJPA.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTRABAJADOR, null));
	        }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
    }

    @Override
    public void actualizar(Context res) {
        Double resultadoDouble = (Double) res.getDatos();
        Integer resultado = resultadoDouble.intValue(); // Convert Double to Integer

        if (res.getEvento() == Evento.CALCULAR_SUELDO_TRABAJADOR_OK) {
            ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECT_RESULTADO, resultadoDouble));
        } else if (res.getEvento() == Evento.CALCULAR_SUELDO_TRABAJADOR_KO) {
            ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, resultado));
        }
        dispose();
    }


    @Override
    public void actualizar() {
        // TODO Auto-generated method stub
    }
}
