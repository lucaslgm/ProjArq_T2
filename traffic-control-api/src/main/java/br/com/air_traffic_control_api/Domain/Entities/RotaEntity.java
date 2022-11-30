package br.com.air_traffic_control_api.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rotas")
public class RotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rota_id", nullable = false)
    private Long id;

    private String origem;

    private String destino;

    @ManyToMany( targetEntity=AeroviaEntity.class )
    private List<AeroviaEntity> conjuntoAerovias;

    @Column(name = "data")
    private Date data;
}
