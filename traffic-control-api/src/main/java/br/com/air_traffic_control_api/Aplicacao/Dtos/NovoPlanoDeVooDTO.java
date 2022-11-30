package br.com.air_traffic_control_api.Aplicacao.Dtos;

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
public class NovoPlanoDeVooDTO {
    private int id;
    private Long rota;
    private int altitude;
    private int velocidade;
    private Date data;
    private int horario;
    private String status;
    private int numeroVoo;
}

