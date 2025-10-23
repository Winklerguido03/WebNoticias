package entities;

public class Administrador {

  private int idAdministrador;
  private String user;
  private String password;

  public Administrador(){}

  public Administrador(String user,String password){
    this.user=user;
    this.password=password;
  }

  public int getIdAdministrador() {
    return idAdministrador;
  }

  public void setIdAdministrador(int idAdministrador) {
    this.idAdministrador = idAdministrador;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
