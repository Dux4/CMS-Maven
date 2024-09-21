package cms;

import java.util.List;

public interface Persistencia<T> {

    void save(T entidade);
    void update(T entidade);
    void list();
    boolean remove(int id);
}
