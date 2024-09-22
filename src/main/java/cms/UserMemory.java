package cms;

import java.util.ArrayList;
import java.util.List;

public class UserMemory implements UserPersistencia {
    private List<User> users = new ArrayList<>();
    private User loggedInUser;

    public UserMemory() {
        // Usuário padrão (pode ser removido ou substituído)
        users.add(new User("admin", "admin"));
    }

    // Validação de login
    public User validateLog(String user, String password) {
        for (User u : users) {
            if (u.getUser().equals(user) && u.getPassword().equals(password)) {
                loggedInUser = u;
                return u;
            }
        }
        return null; // Login inválido
    }

    // Logout
    public void logout() {
        loggedInUser = null;
        System.out.println("Logout realizado com sucesso!");
    }

    // Criar usuário
    public void createUser(String username, String password) {
        if (getUserByUsername(username) == null) {
            users.add(new User(username, password));
            System.out.println("Usuário criado: " + username);
        } else {
            System.out.println("Usuário já existe.");
        }
    }

    // Listar todos os usuários
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário disponível.");
        } else {
            System.out.println("Lista de usuários:");
            for (User u : users) {
                System.out.println("Usuário: " + u.getUser());
            }
        }
    }

    // Atualizar usuário
    public void updateUser(String oldUsername, String newUsername, String newPassword) {
        User user = getUserByUsername(oldUsername);
        if (user != null) {
            user.setUser(newUsername);
            user.setPassword(newPassword);
            System.out.println("Usuário atualizado.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Excluir usuário
    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            users.remove(user);
            System.out.println("Usuário removido.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Alterar a senha do usuário logado
    public void changePassword(User user, String newPassword) {
        if (user != null) {
            user.setPassword(newPassword);
            System.out.println("Senha alterada com sucesso.");
        } else {
            System.out.println("Erro ao alterar senha.");
        }
    }

    // Método auxiliar para buscar usuário por nome
    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUser().equals(username)) {
                return u;
            }
        }
        return null;
    }
}

