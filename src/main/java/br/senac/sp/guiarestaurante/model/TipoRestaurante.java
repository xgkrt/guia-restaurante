package br.senac.sp.guiarestaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Setter
public class TipoRestaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    @Column(unique = true)
    private String nome;
    private String descricao;
    private String palavrasChave;

}
