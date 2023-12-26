package Negocio.TrabajadorJPA;

import java.util.List;
import java.util.Set;

public interface ASTrabajador {

	public Integer altaTrabajador(TTrabajador trabajador);
	
	public Integer bajaTrabajador(Integer idTrabajador);
	
	public Integer modificarTrabajador(TTrabajador trabajador);
	
	public TTrabajador mostrarTrabajador(Integer idTrabajador);
	
	public List<TTrabajador> listarTrabajadores();
	
	public List<TTrabajador> listarTrabajadoresPorDepartamento(Integer idDepartamento);
	
	public Double calcularSueldoTrabajador(Integer idTrabajador);
	
}