package cms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HSQLUser implements UserPersistencia {
    private Connection connection;
    private String dbUrl = "jdbc:hsqldb:file:meubanco;shutdown=true";

    public HSQLUser() throws SQLException {
        connection = DriverManager.getConnection(dbUrl);
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        String dropTable = "DROP TABLE IF EXISTS Users";
        try (PreparedStatement stmt = connection.prepareStatement(dropTable)) {
            stmt.executeUpdate();
        }

        String createTable = "CREATE TABLE IF NOT EXISTS Users (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))";
        try (PreparedStatement stmt = connection.prepareStatement(createTable)) {
            stmt.executeUpdate();
        }
        createUser("admin", "admin"); // Cria o usuário admin com a senha admin
    }

    @Override
    public void createUser(String username, String password) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("Usuário criado: " + username + " no Banco HSQL");
        } catch (SQLException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @Override
    public void listUsers() {
        String query = "SELECT * FROM Users";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (!rs.next()) {
                System.out.println("Nenhum usuário disponível.");
                return;
            }
            do {
                System.out.println("Usuário: " + rs.getString("username"));
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    @Override
    public void updateUser(String oldUsername, String newUsername, String newPassword) {
        String query = "UPDATE Users SET username = ?, password = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);
            stmt.setString(3, oldUsername);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuário atualizado. ");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String username) {
        String query = "DELETE FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuário removido.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }
}

