package cms;

import java.util.Scanner;

public class TextUserInterface {
	private Scanner sc = new Scanner(System.in);
	private UserService us;
	private ServiceContent cs;

	public TextUserInterface() {
		System.out.println("Escolha o tipo de persistência:");
		System.out.println("1 - HSQLDB");
		System.out.println("2 - Memória (ListContent)");

		int choice = sc.nextInt();
		sc.nextLine(); // Limpar buffer

		if (choice == 1) {
			try {
				cs = new ServiceContent(new HSQLContent());
				us = new UserService(new HSQLUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			cs = new ServiceContent(new MemoryContent());
			us = new UserService(new UserMemory());
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
			sc.nextLine(); // Limpar buffer

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
				System.exit(0); // Fecha o programa
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
			System.out.println("5 - Criar usuário");
			System.out.println("6 - Listar usuários");
			System.out.println("7 - Atualizar usuário");
			System.out.println("8 - Excluir usuário");
			System.out.println("9 - Alterar senha");
			System.out.println("10 - Logout");
			opc = sc.nextInt();
			sc.nextLine(); // Limpar buffer

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
				titulo = readInfo("Digite o novo título");
				texto = readInfo("Digite o novo texto");
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
				String newUsername = readInfo("Digite o nome do novo usuário");
				String newPassword = readInfo("Digite a senha do novo usuário");
				us.createUser(newUsername, newPassword);
				break;

			case 6:
				us.listUsers();
				break;

			case 7:
				String oldUsername = readInfo("Digite o nome do usuário a ser atualizado");
				newUsername = readInfo("Digite o novo nome");
				newPassword = readInfo("Digite a nova senha");
				us.updateUser(oldUsername, newUsername, newPassword);
				break;

			case 8:
				String deleteUsername = readInfo("Digite o nome do usuário a ser excluído");
				us.deleteUser(deleteUsername);
				break;

			case 9:
				String newPass = readInfo("Digite a nova senha");
				us.changePassword(currentUser, newPass);
				break;

			case 10:
				currentUser = null;
				System.out.println("Você foi deslogado.");
				break;

			default:
				System.out.println("Opção inválida.");
			}
		}
		return null; // Retorna null ao sair do menu de conteúdo
	}

	private void listContent() {
		System.out.println("Listando conteúdos:");
		cs.listContent();
	}

	private String readInfo(String label) {
		System.out.println(label + ": ");
		return sc.nextLine().trim();
	}
}
