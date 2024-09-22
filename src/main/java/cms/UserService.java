package cms;

public class UserService {
	private UserPersistencia userPersistencia;

	public UserService(UserPersistencia userPersistencia) {
		this.userPersistencia = userPersistencia;
	}

	public void createUser(String username, String password) {
		userPersistencia.createUser(username, password);
	}

	public void listUsers() {
		userPersistencia.listUsers();
	}

	public void updateUser(String oldUsername, String newUsername, String newPassword) {
		userPersistencia.updateUser(oldUsername, newUsername, newPassword);
	}

	public void deleteUser(String username) {
		userPersistencia.deleteUser(username);
	}

	public User validateLog(String username, String password) {
		User user = userPersistencia.getUserByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user; // Retorna o usuário se a senha estiver correta
		}
		return null; // Login inválido
	}

	public void changePassword(User currentUser, String newPass) {
		if (currentUser != null) {
			// Atualiza a senha do usuário
			userPersistencia.updateUser(currentUser.getUser(), currentUser.getUser(), newPass);
			currentUser.setPassword(newPass); // Atualiza a senha na instância do usuário
			System.out.println("Senha alterada com sucesso.");
		} else {
			System.out.println("Erro ao alterar a senha. Usuário não encontrado.");
		}
	}
}
