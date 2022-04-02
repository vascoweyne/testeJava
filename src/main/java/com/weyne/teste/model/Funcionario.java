package com.weyne.teste.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tb_funcionario", schema = "public")
@SequenceGenerator(sequenceName = "public.seq_tb_funcionario", name = "id", schema = "public", allocationSize = 1)

public class Funcionario extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @Column
    private Double saldo;

    @ManyToOne
    @JoinColumn(name="id_empresa", referencedColumnName = "id")
    private Empresa empresa;

}
