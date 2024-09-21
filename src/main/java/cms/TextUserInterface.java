package cms;
import java.util.Scanner;

public class TextUserInterface {
    private Scanner sc = new Scanner(System.in);
    private UserService us = new UserService();
    private ServiceContent cs;

    public TextUserInterface() {
        System.out.println("Escolha o tipo de persistência:");
        System.out.println("1 - HSQLDB");
        System.out.println("2 - Memória (ListContent)");

        int choice = sc.nextInt();
        sc.nextLine();  // Limpar buffer

        if (choice == 1) {
            cs = new ServiceContent(new HSQLContent());
        } else {
            cs = new ServiceContent(new MemoryContent());
        }
    }

    public User menuPrincipal() {
        int opc;
        
        while (true) {
            System.out.println("-------------MENU PRINCIPAL-------------");
            System.out.println("1 - Login");
            System.out.println("2 - Listar Conteúdos");
            System.out.println("3 - Fechar sistema");
            opc = sc.nextInt();
            sc.nextLine();  // Limpar buffer
            
            switch (opc) {
                case 1:
                    System.out.println("Digite o usuário: ");
                    String username = sc.nextLine();
                    System.out.println("Digite a senha: ");
                    String password = sc.nextLine();
                    
                    User user = us.validateLog(username, password);
                    if (user != null) {
                        System.out.println("Login realizado com sucesso!");
                        return menuContent(user);
                    } else {
                        System.out.println("Login inválido. Tente novamente.");
                    }
                    break;
                
                case 2:
                    listContent();
                    break;

                case 3:
                    sc.close();
                    System.exit(0);  // Fecha o programa
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public User menuContent(User currentUser) {
        int opc;
        while (currentUser != null) {
            System.out.println("-------------MENU DE CONTEÚDO-------------");
            System.out.println("Bem-vindo, " + currentUser.getUser() + "!");
            System.out.println("1 - Criar conteúdo");
            System.out.println("2 - Listar conteúdos");
            System.out.println("3 - Atualizar conteúdo");
            System.out.println("4 - Excluir conteúdo");
            System.out.println("5 - Logout");
            opc = sc.nextInt();
            sc.nextLine();  // Limpar buffer

            switch (opc) {
                case 1:
                    String titulo = readInfo("Digite o título");
                    String texto = readInfo("Digite o texto");
                    Content cnt = new Content(null, titulo, texto, currentUser);
                    cs.save(cnt);
                    System.out.println("Conteúdo Criado");
                    break;

                case 2:
                    listContent();
                    break;

                case 3:
                    String ids = readInfo("Digite o ID do conteúdo para atualizar");
                    int id = Integer.parseInt(ids);
                    titulo = readInfo("Digite o título");
                    texto = readInfo("Digite o texto");
                    Content contentToUpdate = new Content(id, titulo, texto, currentUser);
                    cs.updateContent(contentToUpdate);
                    System.out.println("Conteúdo Atualizado");
                    break;

                case 4:
                    ids = readInfo("Digite o ID do conteúdo a ser excluído");
                    id = Integer.parseInt(ids);
                    boolean remove = cs.removeContent(id);
                    if (remove) {
                        System.out.println("Conteúdo excluído.");
                    } else {
                        System.out.println("Conteúdo não encontrado.");
                    }
                    break;

                case 5:
                    currentUser = null;
                    System.out.println("Você foi deslogado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        return null;  // Retorna null ao sair do menu de conteúdo
    }

    private void listContent() {
        System.out.println("Listando conteúdos:");
        cs.listContent();
//        for (Content cnt : cs.listContent()) {
//            System.out.println(cnt);
//        }
    }

    private String readInfo(String label) {
        System.out.println(label + ": ");
        return sc.nextLine().trim();
    }
}
