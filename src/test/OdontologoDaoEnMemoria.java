package test;
import dao.IDao;
import entity.Odontologo;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;



public class OdontologoDaoEnMemoria {
    private IDao<Odontologo> odontologoIDao = new dao.impl.OdontologoDaoEnMemoria(new ArrayList());

    @Before
    public void cargarOdontologosTest() {
        odontologoIDao.guardar(new Odontologo(1L, "JUAN", "PEREZ", 21));
        odontologoIDao.guardar(new Odontologo(2L, "ROBERTO", "JUAREZ", 22));
        odontologoIDao.guardar(new Odontologo(3L, "JUANCHO", "FERREYRA",33));
    }
    @Test
    public void listaDeOdontologosTest(){
        List<Odontologo>odontologos = odontologoIDao.buscarTodos();
        Assert.assertEquals(odontologos.size(), 3);
    }
    @Test
    public void guardarYBuscarTest(){
        odontologoIDao.guardar(new Odontologo(3L, "PEDRO", "JAVA", 33));
        Odontologo odontologo = odontologoIDao.buscar(4L);
        Assert.assertNotNull(odontologo);
        Assert.assertEquals(odontologo.getNombre(), "PEDRO");
    }
    @Test
    public void eliminarTest(){
        odontologoIDao.eliminar(1L);
        Assert.assertEquals(odontologoIDao.buscar(1L), null);
    }
}
