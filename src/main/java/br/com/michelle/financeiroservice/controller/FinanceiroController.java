package br.com.michelle.financeiroservice.controller;

import br.com.michelle.financeiroservice.domain.FormaPagamento;
import br.com.michelle.financeiroservice.service.FinanceiroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private FinanceiroService service;

    public FinanceiroController(FinanceiroService service) {
        this.service = service;
    }

    @PostMapping
    public void pagar(@RequestBody FormaPagamento formaPagamento) {
        service.processarPagamento(formaPagamento);
        System.out.println("Pagamento realizado com sucesso!");

    }
}
