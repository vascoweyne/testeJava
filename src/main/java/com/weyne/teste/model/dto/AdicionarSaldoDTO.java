package com.weyne.teste.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// anotações do lombok
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdicionarSaldoDTO {
    private Long idEmpresa;
    private Double valor;
}


