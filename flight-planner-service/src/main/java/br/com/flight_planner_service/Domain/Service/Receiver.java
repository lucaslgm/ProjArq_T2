package br.com.flight_planner_service.Domain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Autowired
    private PlanoDeVooService planoDeVooService;

    public void receiveMessage(String message) {
            System.out.println("Message received: ["+ message+"]");
    }
}
