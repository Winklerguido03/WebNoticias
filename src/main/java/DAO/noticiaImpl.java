package DAO;

import com.mysql.cj.jdbc.Blob;
import entities.Administrador;
import entities.Categoria;
import entities.Noticia;
import interfaces.Dao;
import interfaces.adminConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class noticiaImpl implements Dao<Noticia,Integer>, adminConexion {

  private Connection conn=null;

  private static final String SQL_GETALL="SELECT * FROM noticia ORDER BY autor";
  private static final String SQL_INSERT="INSERT INTO noticia (titulo,descripcion,fecha,categoria,autor,Administrador_idAdministrador,imagen)"+
                                         "VALUES (?,?,?,?,?,?,?)";
  private static final String SQL_UPDATE="UPDATE noticia SET "+
                                         "titulo=?, descripcion=?, fecha=?, categoria=?, autor=?,imagen=? "+ "WHERE idNoticia=?";
  private static final String SQL_DELETE="DELETE FROM noticia WHERE idNoticia=?";
  private static final String SQL_GETBYID="SELECT * FROM noticia WHERE idNoticia = ?";
  private static final String SQL_GETBYCATEGORY="SELECT * FROM noticia WHERE categoria=?";
  private static final String SQL_GETBYADMIN="SELECT * FROM noticia WHERE Administrador_idAdministrador = ? ORDER BY fecha DESC";


  @Override
  public List<Noticia> getAll() {
    Connection conn=obtenerConexion();
    List<Noticia> listaNoticias=new ArrayList<>();
    PreparedStatement pst=null;
    ResultSet rs=null;

    try{

      pst=conn.prepareStatement(SQL_GETALL);
      rs= pst.executeQuery();

     while(rs.next()){

       Noticia noticia=new Noticia();

       noticia.setIdNoticia(rs.getInt("idNoticia"));
       noticia.setTitulo(rs.getString("titulo"));
       noticia.setDescripcion(rs.getString("descripcion"));
       noticia.setAutor(rs.getString("autor"));
       noticia.setCategoria(Categoria.valueOf(rs.getString("categoria")));
       noticia.setImagen(rs.getString("imagen"));

       listaNoticias.add(noticia);
     }

      rs.close();
      pst.close();
      conn.close();

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return listaNoticias;
  }

    public List<Noticia> getByAdministrador(int idAdmin) {

        Connection conn=obtenerConexion();
        List<Noticia> lista = new ArrayList<>();
        PreparedStatement pst=null;
        ResultSet rs=null;

        try {
            pst=conn.prepareStatement(SQL_GETBYADMIN);
            pst.setInt(1, idAdmin);
            rs = pst.executeQuery();

            while (rs.next()) {
                Noticia n = new Noticia();
                n.setIdNoticia(rs.getInt("idNoticia"));
                n.setTitulo(rs.getString("titulo"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setAutor(rs.getString("autor"));
                n.setFecha(rs.getDate("fecha"));
                n.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                n.setImagen(rs.getString("imagen"));
                n.setAdministrador_idAdministrador(rs.getInt("administrador_idAdministrador"));
                lista.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Noticia> getByCategory(String categoria) {

        Connection conn=obtenerConexion();
        List<Noticia> lista = new ArrayList<>();
        PreparedStatement pst=null;
        ResultSet rs=null;

        try {
            pst=conn.prepareStatement(SQL_GETBYCATEGORY);
            pst.setString(1, categoria);
            rs = pst.executeQuery();

            while (rs.next()) {
                Noticia n = new Noticia();
                n.setIdNoticia(rs.getInt("idNoticia"));
                n.setTitulo(rs.getString("titulo"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setAutor(rs.getString("autor"));
                n.setFecha(rs.getDate("fecha"));
                n.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                n.setImagen(rs.getString("imagen"));
                n.setAdministrador_idAdministrador(rs.getInt("administrador_idAdministrador"));
                lista.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

  @Override
  public void insert(Noticia objeto) {
   Noticia noticia=objeto;

   Connection conn=obtenerConexion();

   PreparedStatement pst=null;

   try{

     pst=conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

     pst.setString(1, noticia.getTitulo());
     pst.setString(2, noticia.getDescripcion());
     pst.setDate(3, noticia.getFecha());
     pst.setString(4, noticia.getCategoria().name());
     pst.setString(5, noticia.getAutor());
     pst.setInt(6,noticia.getAdministrador_idAdministrador());
     pst.setString(7,noticia.getImagen());

     int resultado = pst.executeUpdate();
     if (resultado == 1) {
       System.out.println("Noticia insertada correctamente");
     } else {
       System.out.println("Error al agregar noticia");
     }

     ResultSet rs = pst.getGeneratedKeys();
     if (rs.next()) {
       noticia.setIdNoticia(rs.getInt(1));
       System.out.println("El id asignado es: " + noticia.getIdNoticia());
     }

     pst.close();
     conn.close();
     rs.close();

   } catch (RuntimeException e) {
     throw new RuntimeException(e);
   } catch (SQLException e) {
     throw new RuntimeException(e);
   }

  }

  @Override
  public void update(Noticia objeto) {
  Connection conn=this.obtenerConexion();
  Noticia noticia=objeto;

  if (this.existsById(noticia.getIdNoticia())){

  PreparedStatement pst=null;

    try {

      pst = conn.prepareStatement(SQL_UPDATE);

      pst.setString(1, noticia.getTitulo());
      pst.setString(2,noticia.getDescripcion());
      pst.setDate(3,noticia.getFecha());
      pst.setString(4,noticia.getCategoria().toString());
      pst.setString(5,noticia.getAutor());
      pst.setString(6,noticia.getImagen());
      pst.setInt(7,noticia.getIdNoticia());


      int resultado = pst.executeUpdate();
      if (resultado == 1) {
        System.out.println("Noticia actualizada correctamente");
      } else {
        System.out.println("No se pudo actualizar la noticia");
      }

      pst.close();
      conn.close();

    } catch (SQLException e) {
      System.out.println("Error al crear el statement");
      e.printStackTrace();
    }
  }


  }

  @Override
  public void delete(Integer id) {

    Connection conn = this.obtenerConexion();

    try {
      PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
      pst.setInt(1,id);
      int resultado = pst.executeUpdate();
      if (resultado == 1) {
        System.out.println("Noticia eliminado correctamente");
      } else {
        System.out.println("No se pudo eliminar la noticia");
      }
      pst.close();
      conn.close();
    } catch (SQLException e) {
      System.out.println("No se pudo eliminar la noticia. Error: " + e.getMessage());
    }

  }

  @Override
  public Noticia getById(Integer id) {
      Connection conn = obtenerConexion();
    // Se crea un statement
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean existe = false;
    Noticia noticia=null;

    try {
      pst = conn.prepareStatement(SQL_GETBYID);
      pst.setInt(1,id);
      rs = pst.executeQuery();

      if (rs.next()) {
        noticia=new Noticia();
        // asigno los datos a auto
        noticia.setIdNoticia(rs.getInt("idNoticia"));
        noticia.setTitulo(rs.getString("titulo"));
        noticia.setDescripcion(rs.getString("descripcion"));
        noticia.setAutor(rs.getString("autor"));
        noticia.setFecha(rs.getDate("fecha"));
        noticia.setCategoria(Categoria.valueOf(rs.getString("categoria")));
        noticia.setImagen(rs.getString("imagen"));
      }


      rs.close();
      pst.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return noticia;
  }

  @Override
  public boolean existsById(Integer id) {
    Connection conn = obtenerConexion();

    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean existe = false;
    try {
      pst = conn.prepareStatement(SQL_GETBYID);
      pst.setInt(1,id);
      rs = pst.executeQuery();

      if (rs.next()) {
        existe = true;
      }

      rs.close();
      pst.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return existe;
  }
}
