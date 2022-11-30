package br.com.air_traffic_control_api.Domain.Entities;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aeroportos")
public class AeroportoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aeroporto_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localizacao", referencedColumnName = "refgeo_id")
    private RefGeoEntity localizacao;

    private String nome;
    private String iata;
}
