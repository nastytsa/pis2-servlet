package dao.implementations;

import dao.interfaces.FeedbackDao;
import entities.Feedback;
import sql.ConnectionPool;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDaoImpl implements FeedbackDao {
    
    private static final Logger LOGGER =
            Logger.getLogger(FeedbackDaoImpl.class.getName());
    private final Optional<Connection> connection;
    
    public FeedbackDaoImpl() {
        
        this.connection = ConnectionPool.getConnection();
    }
    
    @Override
    public Optional<Feedback> getByOrderId(Long order_id) {
        
        String sql = "SELECT * FROM feedback WHERE order_id=" + order_id;
        return connection.flatMap(conn -> {
            Optional<Feedback> feedback = Optional.empty();
            
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                
                if (resultSet.next()) {
                    Long feedback_id = resultSet.getLong("feedback_id");
                    String feedback_content = resultSet.getString("feedback");
                    
                    feedback = Optional.of(new Feedback(feedback_id, feedback_content, order_id));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            
            return feedback;
        });
    }
    
    @Override
    public void deleteByOrderId(Long order_id) {
        
        String sql = "DELETE FROM feedback WHERE order_id=?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, order_id);
                statement.executeUpdate();
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
