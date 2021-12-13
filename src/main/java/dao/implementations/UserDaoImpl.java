package dao.implementations;

import dao.interfaces.Dao;
import entities.RoleEnum;
import entities.User;
import sql.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements Dao<User, Long> {

    private static final Logger LOGGER =
            Logger.getLogger(UserDaoImpl.class.getName());
    private final Optional<Connection> connection;

    public UserDaoImpl() {

        this.connection = ConnectionPool.getConnection();
    }

    @Override
    public Optional<User> get(Long id) {
        String sql = "SELECT * FROM users join roles on users.role_id=roles.role_id WHERE users.id=" + id;
        return connection.flatMap(conn -> {
            Optional<User> user = Optional.empty();

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    RoleEnum role = RoleEnum.valueOf(resultSet.getString("role_name"));

                    user = Optional.of(new User(id, login, password, role));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return user;
        });
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users join roles on users.role_id=roles.role_id";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    RoleEnum role = RoleEnum.valueOf(resultSet.getString("role_name"));

                    User user = new User(id, login, password, role);
                    users.add(user);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return users;
    }

    @Override
    public Optional<Long> save(User user) {
        String sql = "INSERT INTO "
                + "users(login password) "
                + "VALUES(?, ?)";

        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, user.getLogin());
                statement.setString(3, user.getPassword());

                int numberOfInsertedRows = statement.executeUpdate();

                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getLong("id"));
                        }
                    }
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return generatedId;
        });
    }

    @Override
    public void update(User user) {
        Long id = user.getId();
        String sql = "UPDATE users SET "
                + "login=?, "
                + "password=? "
                + "WHERE id=" + id;
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, user.getLogin());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id=?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, user.getId());
                statement.executeUpdate();


            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
