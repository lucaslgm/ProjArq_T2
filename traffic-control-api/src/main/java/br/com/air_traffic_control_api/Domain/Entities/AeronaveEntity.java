package br.com.air_traffic_control_api.Domain.Entities;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aeronaves")
public class AeronaveEntity {
    @Id
    @Column(name = "prefixo", nullable = false)
    private String Prefixo;

    private int VelocidadeCruzeiro;
    private int autonomia;
}
