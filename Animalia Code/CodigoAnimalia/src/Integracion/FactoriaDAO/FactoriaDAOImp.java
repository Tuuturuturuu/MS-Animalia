package Integracion.FactoriaDAO;

import Integracion.Pase.DAOPase;
import Integracion.Pase.DAOPaseImp;
import Integracion.Animal.DAOAnimal;
import Integracion.Animal.DAOAnimalImp;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Empleado.DAOEmpleadoImp;
import Integracion.Especie.DAOEspecie;
import Integracion.Especie.DAOEspecieImp;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOFacturaImp;
import Integracion.Habitat.DAOHabitat;
import Integracion.Habitat.DAOHabitatImp;
import Integracion.Habitat.DAOTrabajo;
import Integracion.Habitat.DAOTrabajoImp;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.DAOLineaFacturaImp;

public class FactoriaDAOImp extends FactoriaDAO {

	public DAOPase getDAOPase() {
		return new DAOPaseImp();
	}

	public DAOAnimal getDAOAnimal() {
		return new DAOAnimalImp();

	}

	public DAOEmpleado getDAOEmpleado() {
		return new DAOEmpleadoImp();
	}

	public DAOEspecie getDAOEspecie() {
		return new DAOEspecieImp();
	}

	public DAOFactura getDAOFactura() {
		return new DAOFacturaImp();
	}

	public DAOHabitat getDAOHabitat() {
		return new DAOHabitatImp();
	}

	public DAOLineaFactura getDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}

	public DAOTrabajo getDAOTrabajo() {
		return new DAOTrabajoImp();
	}
}