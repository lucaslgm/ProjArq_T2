package br.com.air_traffic_control_api.Infraestrutura.Connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConnection {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nome){
        return new Queue(nome, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaCadastro = fila(RabbitMqConstants.FILA_CADASTRO);
        Queue filaCancelamento = fila(RabbitMqConstants.FILA_CANCELAMENTO);

        DirectExchange troca = trocaDireta();

        Binding ligacaoCadastro = relacionamento(filaCadastro, troca);
        Binding ligacaoCancelamento = relacionamento(filaCancelamento, troca);

        amqpAdmin.declareQueue(filaCadastro);
        amqpAdmin.declareQueue(filaCancelamento);

        amqpAdmin.declareBinding(ligacaoCadastro);
        amqpAdmin.declareBinding(ligacaoCancelamento);
    }
}
