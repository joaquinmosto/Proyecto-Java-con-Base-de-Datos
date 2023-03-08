package dao.impl;

import dao.IDao;
import entity.Odontologo;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(Main.class);

    public OdontologoDaoH2() {
    }

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:";
    private final static String DB_USER ="admin";
    private final static String DB_PASSWORD = "";





    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            logger.info("--- CONECTANDO ----");
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO odontologo VALUES(?,?,?,?)");
            preparedStatement.setLong(1,odontologo.getId());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.setInt(4,odontologo.getMatricula());
            preparedStatement.executeUpdate();
            logger.info("---- GUARDANDO ----");
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        logger.info("--- CONECTANDO ----");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM odontologo where id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            logger.info("--- ELIMINANDO ----");
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Odontologo buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,matricula FROM odontologo where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idOdontologo= result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int matricula = result.getInt("matricula");
                odontologo = new Odontologo(idOdontologo, nombre, apellido,matricula);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;



    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("--- CONECTANDO ----");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologo = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologo");
            ResultSet result = preparedStatement.executeQuery();
            logger.info("--- BUSCANDO ----");

            while (result.next()) {
                Long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int matricula = result.getInt("matricula");
                odontologo.add(new Odontologo(id, nombre, apellido, matricula));

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return odontologo;
    }
}
