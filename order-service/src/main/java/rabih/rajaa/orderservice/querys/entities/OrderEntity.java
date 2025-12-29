package rabih.rajaa.orderservice.querys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rabih.rajaa.commonapi.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    private String customerId;
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalAmount;
    private Instant orderDate;
    private Instant deliveryDate;
    private Instant updatedAt;
}

