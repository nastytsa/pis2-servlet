package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Locale;

@Entity
@NoArgsConstructor
@Table(name = "orders")
@ToString
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    Long order_id;
    
    @Getter
    @Setter
    private Long client_id;

    @Getter
    @Setter
    @Column(name = "_comment")
    private String comment;
    
    @Getter
    @Setter
    private BigDecimal price;
    
    @Enumerated(value = EnumType.STRING)
    private Status status;
    
    @Getter
    @Setter
    private String content;
    
    public String getStatus() {
        
        return this.status.name();
    }
    
    public void setStatus(String status) {
        
        if (status == null || status == "")
            this.status = Status.CREATED;
        else
            this.status = Status.valueOf(status.toUpperCase(Locale.ROOT));
    }
    
    public Order(Long id, String comment, Long client_id, BigDecimal price, String status, String content) {
        
        setOrder_id(id);
        setComment(comment);
        setClient_id(client_id);
        setPrice(price);
        setStatus(status);
        setContent(content);
    }
    
    public Order(String comment, Long client_id, BigDecimal price, String status, String content) {
        
        setComment(comment);
        setClient_id(client_id);
        setPrice(price);
        setStatus(status);
        setContent(content);
    }

    public Order(Long client_id, String content){
        setClient_id(client_id);
        setContent(content);
        setStatus(null);
        setPrice(BigDecimal.ZERO);
    }
}
