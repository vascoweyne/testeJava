package com.weyne.teste.model;

import com.weyne.teste.util.LocalDateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class EntidadeBase extends EntidadeAPI {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Long id;

    @Column
    private LocalDateTime alteracao;

    @Column(nullable = false)
    private LocalDateTime inclusao;


    @PrePersist
    public void prePersist() {
        inclusao = LocalDateTimeUtils.dataAtual();
    }

    @PreUpdate
    public void preUpdate() {
        alteracao = LocalDateTimeUtils.dataAtual();
    }
}
