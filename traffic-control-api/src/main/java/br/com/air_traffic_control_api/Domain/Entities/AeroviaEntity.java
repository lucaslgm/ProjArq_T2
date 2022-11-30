package br.com.air_traffic_control_api.Domain.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "aerovias")
public class AeroviaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aerovia_id", nullable = false)
    private Long id;

    @ElementCollection(targetClass = SlotEntity.class, fetch = FetchType.EAGER)
    private final List<SlotEntity> slots = new ArrayList<>(240);

    @ManyToOne
    @JoinColumn(name = "origem", referencedColumnName="refgeo_id")
    private RefGeoEntity origem;

    @ManyToOne
    @JoinColumn(name = "destino", referencedColumnName="refgeo_id")
    private RefGeoEntity destino;

    private String nome;
    private double distancia;

    public SlotEntity getSlotByAltiudeAndHorario(int altitude, int horario){
        for(SlotEntity slot : slots){
            if(slot.getAltitude() == altitude && slot.getHora() == horario)
                return slot;
        }
        return null;
    }
}