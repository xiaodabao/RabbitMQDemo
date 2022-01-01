package org.example.config;

import org.example.constant.DirectConstant;
import org.example.listener.DirectConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Exchange directExchange() {
        // direct交换器, 任何绑定在交换器上的队列, 只要它的路由键和发布消息时一致, 它就能收到消息
        // 路由消息时, 检查绑定时判断路由键是否完全相等, 不允许使用任何类型的模式匹配
        // 默认的持久化durable为true, 自动删除autoDelete为false, 未设置任何自定义属性
        return new DirectExchange(DirectConstant.DIRECT_EXCHANGE_NAME);
    }
    @Bean
    public Queue directQueue() {
        // 将要绑定到direct交换器的队列名称
        // 默认的持久化durable为true, 自动删除autoDelete为false, 独占性(唯一消费者)exclusive为false
        return new Queue(DirectConstant.DIRECT_QUEUE_NAME);
    }

    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange) {
        // 将directQueue绑定到directExchange交换器上, 使用路由键foo.bar.direct
        return BindingBuilder.bind(directQueue).to(directExchange).with(DirectConstant.DIRECT_ROUTING_KEY);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(DirectConsumer receiver) {
        return new MessageListenerAdapter(receiver, "directReceiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DirectConstant.DIRECT_QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        container.setPrefetchCount(10);
        return container;
    }
}
