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

    @Bean
    public TopicExchange topic() { return new TopicExchange("financeiro.topic"); }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("pagamento.direct");
    }

    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }


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

        @Bean
        public FinanceiroReceiver receiver() {
            return new FinanceiroReceiver();
        }
    }

    @Bean
    public FinanceiroSender sender() {
        return new FinanceiroSender();
    }
}
