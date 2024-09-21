package cms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryContent implements Persistencia<Content>{

    protected List<Content> cnt = new ArrayList<>();
    private Integer i=1;

    @Override
    public void save(Content cnts){
        if(cnts.getId() == null){
            cnts.setId(i++);
        }
        cnt.add(cnts);
    }
    @Override
    public void update(Content entidade){
        for(Content content : cnt){
            if (content.getId() == entidade.getId()){
                content.setTitulo(entidade.getTitulo());
                content.setTexto(entidade.getTexto());
            }
        }
    }
    
    @Override
    public void list() {
        for (Content content : cnt) {
            System.out.println("ID: " + content.getId() + ", Título: " + content.getTitulo() + ", Descrição: " + content.getTexto());
        }
    }


//    @Override
//    public List<Content> list(){
//        return Collections.unmodifiableList(cnt);
//    }
    
    

    @Override
    public boolean remove(int id){
        return cnt.removeIf(content -> content.getId() == id);
    }
}