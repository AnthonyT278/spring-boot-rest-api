package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity @Table(name="orders") @Getter @Setter @NoArgsConstructor
public class Order {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String customerEmail;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private Status status = Status.PENDING;
  @Column(nullable=false) private BigDecimal total;
  @Column(nullable=false) private Instant createdAt = Instant.now();
  @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true) private List<OrderItem> items = new ArrayList<>();
  public enum Status { PENDING, PAID, SHIPPED, CANCELLED }
}
