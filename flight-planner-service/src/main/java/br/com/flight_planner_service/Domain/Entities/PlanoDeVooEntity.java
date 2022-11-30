package br.com.flight_planner_service.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planoDeVoo")
public class PlanoDeVooEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planoDeVoo_id", nullable = false)
    private Long id;
    private Long rota;
    private int altitude;
    private int velocidade;

    private LocalDate data;
    private String status;
    private int numeroVoo;
}
