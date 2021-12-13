package dao.interfaces;

import entities.Feedback;

import java.util.Optional;

public interface FeedbackDao {
    
    Optional<Feedback> getByOrderId(Long id);
    
    void deleteByOrderId(Long id);
    
}
