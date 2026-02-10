package entities;

public class Administrador {

  private int idAdministrador;
  private String correo;
  private String password;

  public Administrador(){}

  public Administrador(String correo,String password){
    this.correo=correo;
    this.password=password;
  }

  public int getIdAdministrador() {
    return idAdministrador;
  }

  public void setIdAdministrador(int idAdministrador) {
    this.idAdministrador = idAdministrador;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
