package br.com.flight_planner_service.Aplicacao.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanoDeVooDTO {
    private int id;
    private Long rota;
    private int altitude;
    private int velocidade;
    private Date data;
    private int horario;
    private String status;
    private int numeroVoo;
}

