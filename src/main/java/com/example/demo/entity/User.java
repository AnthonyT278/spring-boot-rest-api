package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) @Email @NotBlank private String email;
  @Column(nullable=false) @NotBlank @com.fasterxml.jackson.annotation.JsonIgnore private String password;
  @Column(nullable=false) private String role = "USER";
}
