package Integracion.Especie;

import Negocio.Especie.TEspecie;

import java.util.Set;

public interface DAOEspecie {

    Integer altaEspecie(TEspecie especie);

    Integer bajaEspecie(Integer idEspecie);

    TEspecie mostrarEspecie(Integer idEspecie);

    Integer modificarEspecie(TEspecie especie);

    Set<TEspecie> listarEspecie();

    Set<TEspecie> listarEspeciePorHabitat(Integer idHabitat);

    TEspecie leerPorNombreUnico(String nombreEspecie);

    Set<TEspecie> listarEspeciePorHabitatActivos(Integer idHabitat);
}
