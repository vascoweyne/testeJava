package com.weyne.teste.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {
    private Long idEmpresa;
    private Long idFuncionario;
    private Double valor;
}
