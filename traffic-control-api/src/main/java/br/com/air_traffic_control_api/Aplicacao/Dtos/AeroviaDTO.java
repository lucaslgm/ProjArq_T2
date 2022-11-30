package br.com.air_traffic_control_api.Aplicacao.Dtos;

import br.com.air_traffic_control_api.Domain.Entities.SlotEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AeroviaDTO {
    private long id;
    private String nome;
    private RefGeoDTO origem;
    private RefGeoDTO destino;
    private double distancia;
    private final List<SlotEntity> slots = new ArrayList<>(240);
}
