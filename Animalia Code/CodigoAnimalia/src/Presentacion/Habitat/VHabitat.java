package Presentacion.Habitat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Habitat.THabitat;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VHabitat extends JFrame implements IGUI{
	
	private JButton bAltaHabitat;
	private JButton bBajaHabitat;
	private JButton bModificarHabitat;
	private JButton bMostrarHabitat;
	private JButton bListarHabitats;
    private JButton bVincularEmpleado;
    private JButton bDesvincularEmpleado;
    private JButton bListarHabitatsPorEmpleado;
    private JButton bCalcularHabitatConMasIngresos;
	
	private JButton backButton;
	private JPanel j;

	private THabitat tHabitat;

	public VHabitat(){
		super("Animalia");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	public void initGUI() {
		tHabitat = new THabitat();
		JLabel label = ComponentsBuilder.createLabel("Habitat", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA Habitat
		bAltaHabitat = ComponentsBuilder.createButton("Alta Habitat", 100, 100, 185, 100);
		bAltaHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_HABITAT, tHabitat));
			}

		});
		bAltaHabitat.setVisible(true);
		this.add(bAltaHabitat);
		
		//BAJA Habitat
		bBajaHabitat= ComponentsBuilder.createButton("Baja Habitat", 407, 100, 185, 100);
		bBajaHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_HABITAT, tHabitat));
			}

		});
		bBajaHabitat.setVisible(true);
		this.add(bBajaHabitat);
		
		//MODIFICAR Habitat
		bModificarHabitat = ComponentsBuilder.createButton("Modificar Habitat", 715, 100, 185, 100);
		bModificarHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_HABITAT, tHabitat));
			}

		});
		bModificarHabitat.setVisible(true);
		this.add(bModificarHabitat);
		
		//MOSTRAR Habitat
		bMostrarHabitat = ComponentsBuilder.createButton("Mostrar Habitat", 100, 220, 185, 100);
		bMostrarHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_HABITAT, tHabitat));
			}

		});
		bMostrarHabitat.setVisible(true);
		this.add(bMostrarHabitat);
		
		//LISTAR HabitatS
		bListarHabitats = ComponentsBuilder.createButton("Listar Habitats", 407, 220, 185, 100);
		bListarHabitats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_HABITAT, tHabitat));
			}

		});
		bListarHabitats.setVisible(true);
		this.add(bListarHabitats);
		
        // LISTAR HABITATS POR EMPLEADO
        bListarHabitatsPorEmpleado = ComponentsBuilder.createButton("Listar Habitats por Empleado", 708, 220, 200, 100);
        bListarHabitatsPorEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VHabitat.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_HABITATS_POR_EMPLEADO, tHabitat));
            }
        });
        bListarHabitatsPorEmpleado.setVisible(true);
        this.add(bListarHabitatsPorEmpleado);
        
        // VINCULAR EMPLEADO A HABITAT
        bVincularEmpleado = ComponentsBuilder.createButton("Vincular Empleado", 100, 340, 185, 100);
        bVincularEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VHabitat.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VVINCULAR_EMPLEADO_A_HABITAT, tHabitat));
            }
        });
        bVincularEmpleado.setVisible(true);
        this.add(bVincularEmpleado);

        // DESVINCULAR EMPLEADO DE HABITAT
        bDesvincularEmpleado = ComponentsBuilder.createButton("Desvincular Empleado", 407, 340, 185, 100);
        bDesvincularEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VHabitat.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VDESVINCULAR_EMPLEADO_DE_HABITAT, tHabitat));
            }
        });
        bDesvincularEmpleado.setVisible(true);
        this.add(bDesvincularEmpleado);

        // CALCULAR HABITAT CON MAS INGRESOS
        bCalcularHabitatConMasIngresos = ComponentsBuilder.createButton("Habitat con mas Ingresos", 715, 340, 185, 100);
        bCalcularHabitatConMasIngresos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VHabitat.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VCALCULAR_HABITAT_CON_MAS_INGRESOS, tHabitat));
            }
        });
        bCalcularHabitatConMasIngresos.setVisible(true);
        this.add(bCalcularHabitatConMasIngresos);

		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW,null));
				dispose();
			}
		});
		backButton.setVisible(true);
		this.add(backButton);
		
		getContentPane().add(j);


	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}
