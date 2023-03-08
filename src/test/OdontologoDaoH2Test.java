package test;
import dao.IDao;
import dao.impl.OdontologoDaoH2;
import entity.Odontologo;
import org.junit.*;


import java.util.List;


public class OdontologoDaoH2Test {
    private static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();
    @BeforeClass
    public static void cargargentusa(){
        odontologoIDao.guardar(new Odontologo(1L, "JUAN", "PEREZ", 3));
        odontologoIDao.guardar(new Odontologo(2L, "ROBERTO", "RIQUELME", 22));

    }

    @Test
    public void guardarYBuscarTest(){
        odontologoIDao.guardar(new Odontologo(3L, "JUANK", "PEREZ", 3423));
        Odontologo odontologo = odontologoIDao.buscar(3L);
        Assert.assertNotNull(odontologo);
        Assert.assertEquals(odontologo.getNombre(), "JUANK");

    }

    @Test
    public void traerTodoTest(){
        List<Odontologo> odontologos = odontologoIDao.buscarTodos();
        Assert.assertEquals(odontologos.size(), 2);
    }
    @Test
    public  void eliminarTest(){
        odontologoIDao.eliminar(1L);
        Assert.assertEquals(odontologoIDao.buscar(1L), null);
    }
}
