package br.com.air_traffic_control_api.Aplicacao.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RotaDTO {
    private Long id;
    private String origem;
    private String destino;
    private Date data;
    private ArrayList<AeroviaDTO> conjuntoAerovias;
}
