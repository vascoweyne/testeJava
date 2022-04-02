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
@Table(name = "tb_empresa", schema = "public")
@SequenceGenerator(sequenceName = "public.seq_tb_empresa", name = "id", schema = "public", allocationSize = 1)

public class Empresa extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @Column
    private Double saldo;
}
