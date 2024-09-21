package cms;

import java.util.List;

import java.util.List;

public class ServiceContent {
    private Persistencia<Content> persistencia;

    public ServiceContent(Persistencia<Content> persistencia) {
        this.persistencia = persistencia;
    }

    public void save(Content content) {
        persistencia.save(content);
    }

//    public List<Content> listContent() {
//        return persistencia.list();
//    }
    
    public void listContent() {
        persistencia.list();  // Chama o método list() da persistência
    }

    public void updateContent(Content content) {
        persistencia.update(content);
    }

    public boolean removeContent(int id) {
        return persistencia.remove(id);
    }
}
