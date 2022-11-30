package br.com.air_traffic_control_api.Api.Controllers;

import br.com.air_traffic_control_api.Aplicacao.Service.IAeroviaService;
import br.com.air_traffic_control_api.Aplicacao.Service.IRefGeoService;
import br.com.air_traffic_control_api.Domain.Entities.AeroviaEntity;
import br.com.air_traffic_control_api.Domain.Entities.RefGeoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aerovia")
public class AeroviaController {
    private final IAeroviaService service;
    private final IRefGeoService refGeoService;

    @Autowired
    public AeroviaController(IAeroviaService service, IRefGeoService refGeoService) {
        this.service = service;
        this.refGeoService = refGeoService;
    }

//    @PostMapping("/save")
//    public AeroviaEntity save(@RequestBody RefGeoEntity origem, RefGeoEntity destino, double distancia){
//        return this.service.CadastrarNovaAerovia(origem,destino,distancia);
//    }

    @GetMapping
    public List<AeroviaEntity> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/refs")
    public List<RefGeoEntity> listarReferenciasGeograficas(){
        List<RefGeoEntity> refs = refGeoService.listarReferenciasGeograficas();
        return  refs;
    }
}


