package br.com.michelle.financeiroservice.dto;

import br.com.michelle.financeiroservice.enums.Banco;
import br.com.michelle.financeiroservice.enums.MeioPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoRecebido {

    private Long id;
    private MeioPagamento meioPagamento;
    private String nomeTitular;
    private BigDecimal valor;
    private Banco banco;
    private BigDecimal valorRecebido;
}
