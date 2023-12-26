package Integracion.FactoriaDAO;

import Integracion.Pase.DAOPase;
import Integracion.Animal.DAOAnimal;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Especie.DAOEspecie;
import Integracion.Factura.DAOFactura;
import Integracion.Habitat.DAOHabitat;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Habitat.DAOTrabajo;

public abstract class FactoriaDAO {

	private static FactoriaDAOImp instance;

	public static synchronized FactoriaDAOImp getInstance() {
		if(instance == null)
			instance = new FactoriaDAOImp();
		return instance;
	}

	public abstract DAOPase getDAOPase();

	public abstract DAOAnimal getDAOAnimal();

	public abstract DAOEmpleado getDAOEmpleado();

	public abstract DAOEspecie getDAOEspecie();

	public abstract DAOFactura getDAOFactura();

	public abstract DAOHabitat getDAOHabitat();

	public abstract DAOLineaFactura getDAOLineaFactura();

	public abstract DAOTrabajo getDAOTrabajo();

}