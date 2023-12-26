package Integracion.Pase;

import Negocio.Pase.TPase;

import java.util.Date;
import java.sql.Time;
import java.util.Set;

public interface DAOPase {

    Integer altaPase(TPase pase);

    TPase mostrarPase(Integer id);

    Integer modificarPase(TPase pase);

    Integer bajaPase(Integer id);

    Set<TPase> listarPases();

    Set<TPase> listarPasesPorHabitat(Integer idHabitat);

    TPase leerPorCampoUnico(Date fecha, Time hora, Integer id_habitat);

    Set<TPase> listarPasesPorHabitatActivos(Integer idHabitat);
}
