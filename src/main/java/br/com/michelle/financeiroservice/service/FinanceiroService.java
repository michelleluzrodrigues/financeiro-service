package br.com.michelle.financeiroservice.service;

import br.com.michelle.financeiroservice.domain.FormaPagamento;
import br.com.michelle.financeiroservice.sender.FinanceiroSender;
import org.springframework.stereotype.Service;

@Service
public class FinanceiroService {

    private FinanceiroSender sender;

    public FinanceiroService(FinanceiroSender sender) {
        this.sender = sender;
    }

    public void processarPagamento(FormaPagamento pagamento) {
        sender.send(pagamento);
        System.out.println("Pagamento processado: " + pagamento);
    }
}
