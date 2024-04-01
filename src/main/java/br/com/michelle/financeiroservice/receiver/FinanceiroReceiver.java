package br.com.michelle.financeiroservice.receiver;

import br.com.michelle.financeiroservice.dto.PagamentoRecebido;
import br.com.michelle.financeiroservice.service.FinanceiroService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroReceiver {

    @Autowired
    private FinanceiroService financeiroService;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws InterruptedException {
        System.out.println("instance " + receiver + " [x] Received '" + in + "'");

        var pagamento = new Gson().fromJson(in, PagamentoRecebido.class);
        if (receiver == 1) {
            System.out.println("Pagamento recebido: " + pagamento.getNomeTitular() + " - " + pagamento.getValorRecebido());
        } else {
            System.out.println("Pagamento insuficiente: " + pagamento.getNomeTitular() + " - " + pagamento.getValorRecebido());
        }

    }
}
