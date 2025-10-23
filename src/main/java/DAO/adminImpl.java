package DAO;

import entities.Administrador;
import entities.Noticia;
import interfaces.Dao;
import interfaces.adminConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminImpl implements Dao<Administrador,Integer>, adminConexion {

  private Connection conn=null;
  private static final String SQL_GETBYUSERPASS =
      "SELECT * FROM administrador WHERE usuario = ? AND password = ?";


  @Override
  public List<Administrador> getAll(Integer id) {
    List<Administrador>listaAdmins=new ArrayList<>();
    return listaAdmins;
  }

  @Override
  public void insert(Administrador objeto) {

  }

  @Override
  public void update(Administrador objeto) {

  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public Administrador getById(Integer id) {
    return null;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }

  public boolean existsByUserPass(String nombre, String password) {
    conn = obtenerConexion();


    boolean existe=false;
    try {
      PreparedStatement pst = conn.prepareStatement(SQL_GETBYUSERPASS);
      pst.setString(1, nombre);
      pst.setString(2, password);
      ResultSet rs = pst.executeQuery();

      if (rs.next()) {
        existe = true;
      }
      rs.close();
      pst.close();
      conn.close();
      return existe;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
}
