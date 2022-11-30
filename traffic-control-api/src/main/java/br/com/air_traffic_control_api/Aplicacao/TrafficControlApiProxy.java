package br.com.air_traffic_control_api.Aplicacao;

import br.com.air_traffic_control_api.Aplicacao.Dtos.NovoPlanoDeVooDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.air_traffic_control_api.Domain.Entities.PlanoDeVooEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="flight-planner-service")
public interface TrafficControlApiProxy {
    @GetMapping("/flight-planner/answer")
    String retrieveExchangeValue();

    @PutMapping("/flight-planner/cancel-flight-plan")
    boolean retriveCancelPlanAnswer(@RequestParam("planoDeVooID") long planoDeVoo);

    @PostMapping("/flight-planner/insert-flight-plan")
    Long retriveInsertPlanAnswer(@RequestBody NovoPlanoDeVooDTO planoDeVoo);

    @GetMapping("/flight-planner/list-flight-plans")
    List<PlanoDeVooEntity> retriveFindAllPlansAnswer();
}
