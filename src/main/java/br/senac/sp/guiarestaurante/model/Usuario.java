package br.senac.sp.guiarestaurante.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;

}
