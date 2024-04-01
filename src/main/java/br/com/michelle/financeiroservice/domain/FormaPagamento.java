package br.com.michelle.financeiroservice.domain;

import br.com.michelle.financeiroservice.enums.Banco;
import br.com.michelle.financeiroservice.enums.MeioPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FormaPagamento {

    private MeioPagamento meioPagamento;
    private String nomeTitular;
    private BigDecimal valor;
    private Banco banco;
}
