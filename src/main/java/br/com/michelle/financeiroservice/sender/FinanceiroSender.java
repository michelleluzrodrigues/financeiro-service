package br.com.michelle.financeiroservice.sender;

import br.com.michelle.financeiroservice.domain.FormaPagamento;
import com.google.gson.Gson;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroSender {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private TopicExchange topic;

    public void send(FormaPagamento pagamento) {
        var key = pagamento.getMeioPagamento() + "." + pagamento.getBanco();
        Gson gson = new Gson();
        var json = gson.toJson(pagamento);

        template.convertAndSend(topic.getName(), key, json);
        System.out.println(" [x] Sent '" + json + "'");
    }
}
