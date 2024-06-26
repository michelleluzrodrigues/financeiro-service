package br.com.michelle.financeiroservice.config;

import br.com.michelle.financeiroservice.receiver.FinanceiroReceiver;
import br.com.michelle.financeiroservice.sender.FinanceiroSender;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class financeiroConfig {

    //Usada para rotear mensagens de acordo com os padrões de roteamento 
    @Bean
    public TopicExchange topic() { return new TopicExchange("financeiro.topic"); }

    //Rotea mensagens para as filas especificas
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("pagamento.direct");
    }
    
    //configura filas anonimas
    private static class ReceiverConfig {

        //metodos que criam e retornam filas anonimas
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        //metodo que criam e retornam associacao entre a filas e a troca direta
        @Bean
        public Binding binding1(DirectExchange direct,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("pago");
        }

        @Bean
        public Binding binding2(DirectExchange direct,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("pendente");
        }

        //processa mensagem recebidas
        @Bean
        public FinanceiroReceiver receiver() {
            return new FinanceiroReceiver();
        }
    }

    //Envia mensagens
    @Bean
    public FinanceiroSender sender() {
        return new FinanceiroSender();
    }
}
