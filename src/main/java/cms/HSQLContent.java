package cms;


import java.util.ArrayList;
import java.util.List;

public class HSQLContent implements Persistencia<Content> {

    private List<Content> contents;

    public HSQLContent() {
        this.contents = new ArrayList<>();
        System.out.println("HSQL CRIADO");
    }

    @Override
    public void save(Content content) {
        contents.add(content);
        System.out.println("Conteúdo salvo: " + content);
    }

    @Override
    public void update(Content content) {
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).getId() == content.getId()) {
                contents.set(i, content);
                System.out.println("Conteúdo atualizado: " + content);
                return;
            }
        }
        System.out.println("Conteúdo não encontrado para atualização.");
    }
    
    @Override
    public void list() {
        if (contents.isEmpty()) {
            System.out.println("Nenhum conteúdo disponível.");
            return;
        }
        
        for (Content content : contents) {
            System.out.println("ID: " + content.getId() + ", Título: " + content.getTitulo() + ", Descrição: " + content.getTexto());
        }
    }


//    @Override
//    public List<Content> list() {
//        return new ArrayList<>(contents);  // Retorna uma cópia da lista para não permitir alterações externas
//    }

    @Override
    public boolean remove(int id) {
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).getId() == id) {
                contents.remove(i);
                System.out.println("Conteúdo removido com ID: " + id);
                return true;
            }
        }
        System.out.println("Conteúdo não encontrado para remoção.");
        return false;
    }
}

