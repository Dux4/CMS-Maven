package cms;

import java.util.List;

public class ServiceContent {

    private Persistencia<Content> p;

    public ServiceContent(Persistencia<Content> p) {
        this.p = p;
    }

    public void save(Content cnt){
        p.save(cnt);
    }

    public void updateContent (Integer id, String titulo, String texto, User autor){
        p.update(new Content(id, titulo, texto, autor));
    }

    public List<Content> listContent(){
        return p.list();
    }

    public boolean removeContent(int id){
        return p.remove(id);
    }
}
