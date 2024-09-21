package cms;

import java.util.Scanner;
public class TextUserInterface {
    private Scanner sc = new Scanner(System.in);
    private UserService us = new UserService();
    private ServiceContent cs = new ServiceContent((Persistencia<Content>) new HSQLContent());

    public User menuPrincipal() {
        int opc;

        while(true) {
            System.out.println("-------------MENU-------------");
            System.out.println("1 - Login");
            System.out.println("2 - Listar Conteudos");
            System.out.println("3 - Fechar sistema");
            System.out.println();
            opc = sc.nextInt();
            switch(opc){
                case 1:
                    System.out.println("Digite o user: ");
                    String username = sc.nextLine();
                    System.out.println("Digite a senha: ");
                    String password = sc.nextLine();
                    User user = us.validateLog(username, password);
                    if(user != null) {
                        System.out.println("Login Inválido");
                        return user;
                    }
                    return null;

                case 2:
                    listContent();
                    break;

                case 3:
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    public User menuContent(User currentUser){
        int opc;
        System.out.println("-------------MENU-------------");
        System.out.println("Bem-vindo, " + currentUser.getUser() + "!");
        System.out.println("1 - Criar conteudo");
        System.out.println("2 - Listar conteudo");
        System.out.println("3 - Atualizar conteudo");
        System.out.println("4 - Excluir conteudo");
        System.out.println("5 - Logout");
        opc = sc.nextInt();

        if(opc == 1){
            String titulo = readInfo("Digite o título");
            String texto = readInfo("Digite o texto");
            Content cnt = new Content(null, titulo, texto, currentUser);
            cs.save(cnt);
            System.out.println("Conteúdo Criado");
        } else if (opc == 2) {
            listContent();
         }else if (opc == 3) {
            String ids = readInfo("Digite o ID do conteúdo para atualizar");
            int id = Integer.parseInt(ids);
            String titulo = readInfo("Digite o título");
            String texto = readInfo("Digite o texto");
            cs.updateContent(id, titulo, texto, currentUser);
            System.out.println("Conteúdo Atualizado");
        } else if (opc == 4) {
            String ids = readInfo("Digite o ID do Conteúdo a ser excluso");
            int id = Integer.parseInt(ids);
            boolean remove = cs.removeContent(id);
            if(remove){
                System.out.println("Conteúdo excluso.");
            }else {
                System.out.println("Conteúdo não encontrado.");
            }
        } else if (opc == 5){
            currentUser = null;
        }
        return currentUser;
    }

    private void listContent(){
        for(Content cnt : cs.listContent()){
            System.out.println(cnt);
        }
    }

    private String readInfo(String label){
        System.out.println(label +": ");
        return sc.nextLine().trim();
    }
}
