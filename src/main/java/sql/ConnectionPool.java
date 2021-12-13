package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    
    private static final Logger LOGGER =
            Logger.getLogger(ConnectionPool.class.getName());
    private static Optional<Connection> connection = Optional.empty();
    
    public static Optional<Connection> getConnection() {
        
        if (connection.isEmpty()) {
            String url = "jdbc:postgresql://localhost:5432/repair_shop";
            String user = "postgres";
            String password = "1234";
            
            try {
                connection = Optional.ofNullable(
                        DriverManager.getConnection(url, user, password));
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
