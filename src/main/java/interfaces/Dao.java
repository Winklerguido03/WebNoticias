package interfaces;

import entities.Noticia;

import java.util.List;

public interface Dao <O,K>{

  public List<O> getAll(K id);
  public void insert(O objeto);
  public void update(O objeto);
  public void delete(K id);
  public O getById(K id);
  public boolean existsById(K id);
}
