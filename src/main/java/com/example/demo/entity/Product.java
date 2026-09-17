package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @NotBlank @Column(nullable=false) private String name;
  @Column(length=2000) private String description;
  @NotNull @Positive @Column(nullable=false, precision=12, scale=2) private BigDecimal price;
  @NotNull @Min(0) @Column(nullable=false) private Integer stock;
}
