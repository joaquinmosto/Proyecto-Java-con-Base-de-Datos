package service;

import dao.IDao;
import entity.Odontologo;

import java.util.List;

public class OdontologoService {


    public OdontologoService() {
    }

    private IDao idao;

    public OdontologoService(IDao idao) {
        this.idao = idao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        idao.guardar(odontologo);
        return odontologo;
    }
    public void eliminarOdontologo (Long id){
        idao.eliminar(id);
    }
    public Odontologo buscarOdontologo(Long id){
       return (Odontologo) idao.buscar(id);

    }

    public List traerTodos(){
        return idao.buscarTodos();
    }



}
