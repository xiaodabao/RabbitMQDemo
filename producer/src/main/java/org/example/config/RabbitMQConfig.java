package org.example.config;

import org.example.constant.DirectConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Exchange directExchange() {
        // direct交换器, 任何绑定在交换器上的队列, 只要它的路由键和发布消息时一致, 它就能收到消息
        // 路由消息时, 检查绑定时判断路由键是否完全相等, 不允许使用任何类型的模式匹配
        // 默认的持久化durable为true, 自动删除autoDelete为false, 未设置任何自定义属性
        return new DirectExchange(DirectConstant.DIRECT_EXCHANGE_NAME);
    }

}
