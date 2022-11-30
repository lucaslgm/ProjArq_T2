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
@Entity
@Table(name = "referenciasgeograficas")
public class RefGeoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refgeo_id", nullable = false)
    private Long id;
    private String nome;
    private double latitude;
    private double longitude;
}