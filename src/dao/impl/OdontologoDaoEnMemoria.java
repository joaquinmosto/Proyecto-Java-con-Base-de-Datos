package dao.impl;

import dao.IDao;
import entity.Odontologo;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private List<Odontologo> listaDeOdontologos;

    private static final Logger logger = Logger.getLogger(Main.class);


    public OdontologoDaoEnMemoria(List<Odontologo> listaDeOdontologos) {
        this.listaDeOdontologos = listaDeOdontologos;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("--- GUARDANDO ----");

        listaDeOdontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        logger.info("--- ELIMINANDO ----");

        Odontologo odontologo = buscar(id);
        listaDeOdontologos.remove(odontologo);

    }

    @Override
    public Odontologo buscar(Long id) {
        logger.info("--- BUSCANDO ----");

        Odontologo odontologoBuscado = null;
        int i = 0;
        while (i<listaDeOdontologos.size() && odontologoBuscado == null){
            Odontologo odontologo = listaDeOdontologos.get(i);
            if (odontologo.getId() == id) {
                odontologoBuscado = odontologo;
            }
            i++;
        }
        return odontologoBuscado;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("--- BUSCANDO TODOS LOS GUARDADOS ----");

        return listaDeOdontologos;
    }
}
