package com.example.sbb.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Record record;
}
