package br.com.air_traffic_control_api.Aplicacao.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FaixaAeroviaDTO {
    private int nome;
    private int altitude;
    private final ArrayList<HorarioDTO> horarios = new ArrayList<>(24);
}
