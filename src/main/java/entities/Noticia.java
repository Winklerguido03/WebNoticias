package entities;

import com.mysql.cj.jdbc.Blob;

import java.sql.Date;

public class Noticia {

  private int idNoticia;
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private String autor;
  private Date fecha;

  public Noticia(){}

  public Noticia(int idNoticia,String titulo,String descripcion,Categoria categoria,String autor,Date fecha){
    this.idNoticia=idNoticia;
    this.titulo=titulo;
    this.descripcion=descripcion;
    this.categoria=categoria;
    this.autor=autor;
    this.fecha=fecha;
  }

  public Noticia(String titulo,String descripcion,Categoria categoria,String autor){
    this.titulo=titulo;
    this.descripcion=descripcion;
    this.categoria=categoria;
    this.autor=autor;
  }

  public int getIdNoticia() {
    return idNoticia;
  }

  public void setIdNoticia(int idNoticia) {
    this.idNoticia = idNoticia;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  @Override
  public String toString() {
    return "Noticia{" +
        "idNoticia=" + idNoticia +
        ", titulo='" + titulo + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", categoria=" + categoria +
        ", autor='" + autor + '\'' +
        ", fecha=" + fecha +
        '}';
  }
}
