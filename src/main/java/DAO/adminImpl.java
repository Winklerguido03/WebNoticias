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

public class adminImpl implements  adminConexion,Dao<Administrador,String> {

  private Connection conn=null;
  private static final String SQL_GETBYUSERPASS =
      "SELECT * FROM administrador WHERE correo = ? AND password = ?";
  private static final String SQL_GETBYCORREO = "SELECT idAdministrador, correo, password " +
          "FROM administrador " +
          "WHERE correo = ?";


  @Override
  public List<Administrador> getAll() {
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
  public void delete(String id) {

  }

    @Override
  public Administrador getById(String id) {
    return null;
  }

  @Override
  public boolean existsById(String id) {
    return false;
  }

    public Administrador getByCorreo(String correo) {
        conn = obtenerConexion();
        Administrador admin = null;

        String sql = "SELECT idAdministrador, correo, password " +
                "FROM administrador " +
                "WHERE correo = ?";

        try (
                PreparedStatement ps = conn.prepareStatement(SQL_GETBYCORREO)
        ) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setIdAdministrador(rs.getInt("idAdministrador"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setPassword(rs.getString("password"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }


    public boolean existsByUserPass(String correo, String password) {
    conn = obtenerConexion();


    boolean existe=false;
    try {
      PreparedStatement pst = conn.prepareStatement(SQL_GETBYUSERPASS);
      pst.setString(1, correo);
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
