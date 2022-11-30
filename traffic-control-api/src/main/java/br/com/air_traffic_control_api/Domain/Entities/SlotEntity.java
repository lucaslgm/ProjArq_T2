package br.com.air_traffic_control_api.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SlotEntity {
    private int altitude;
    private int hora;
    private boolean disponivel;
}