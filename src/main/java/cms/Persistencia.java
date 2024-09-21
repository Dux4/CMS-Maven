package cms;

import java.util.List;

public interface Persistencia<T> {

    void save(T entidade);
    void update(T entidade);
    List<T> list();
    boolean remove(int id);
}
