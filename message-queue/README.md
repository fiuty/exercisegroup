#### 《springboot rabbitmq入门使用》
- 以下例子代码可在github或者在gitee下载
  github：[代码链接](https://github.com/fiuty/exercisegroup)
  gitee：[代码链接](https://gitee.com/fiuty/exercisegroup.git)
> RabbitMQ作为一款能实现高性能存储分发消息的分布式中间件，具有异步通信、服务解耦、接口限流、消息分发和业务延迟处理等功能，在实际生产环境中具有很广泛的应用。

>为了能在项目中使用RabbitMQ，需要在本地安装RabbitMQ并能进行简单的使用。可参考改教程安装RabbitMQ：[安装教程](https://www.cnblogs.com/count-mjb/p/10939117.html)

##### 一、总的代码目录结构如下：
![rabbitmq使用代码目录结构.png](https://upload-images.jianshu.io/upload_images/17109776-cabbce9518747a1b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- entity包实体类order，作为消息载体；
- ackmodel包是写消息确认消费机制，自动确认消费和手工确认消费；
- delay包是写延迟队列，在业务延迟处理时可用到，如订单30分钟内未付款自动取消订单相关业务；
- exchangemodel包是写不同类型交换机下的消息模型，常见的三种消息模型是：direct-直接传输、fanout-广播、topic-主题消息模型；
- springevent包是spring事件驱动模型的demo，要了解消息队列，先了解一下spring的事件驱动会更好；
- 数据库是用mysql，项目启动需要连接本地数据库，新建一个exercisegroup数据库；
- appilication.yaml配置rabbitmq地址，pom文件引入amqp starter即可。
##### 二、spring事件驱动
（1）spring事件驱动模型由三部分构成，生产者、事件(消息)、消费者，即生成者采用异步的方式把事件(消息)发送给消费者，消费者监听到事件(消息)再进一步处理。
![spring事件驱动.jpeg](https://upload-images.jianshu.io/upload_images/17109776-8e8c85d860802f6d.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（2）代码目录结构如下：
![springevent.png](https://upload-images.jianshu.io/upload_images/17109776-e5cd9ba1ddcb6cc6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（3）示例代码
- OrderEvent类，订单事件，继承ApplicationEvent，：
```
public class OrderEvent extends ApplicationEvent {
    public OrderEvent(Order source) {
        super(source);
    }
}
```
- OrderPublisher类，生产者，异步发送事件：
```
@Component
public class OrderPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    public void sendMsg() {
        Order order = new Order();
        order.setOrdernum("123456");
        OrderEvent orderEvent = new OrderEvent(order);
        //发送消息
        applicationEventPublisher.publishEvent(orderEvent);
    }
}
```
- OrderConsumer类，消费者，监听生产者发送过来的事件
```
@Component//加入Spring的IOC容器
@EnableAsync//允许异步执行
@Slf4j
public class OrderConsumer implements ApplicationListener<OrderEvent> {
    @Override
    @Async
    public void onApplicationEvent(OrderEvent event) {
        log.info("监听到订单,订单号：{}", ((Order) event.getSource()).getOrdernum());
    }
}
```
（4）执行MessageQueueApplicationTests的test方法即可看结果，RabbitMQ本质也是异步通信，消息在生产者端进行发送，在消费者端进行监听，对监听到的消息进一步处理，其功能更加强大。
![test-springevent.png](https://upload-images.jianshu.io/upload_images/17109776-fbaacdfdbf691d7c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 三、RabbitMQ一些专有名词
> Producer/Publisher生产者，投递消息的一方。
Consumer消费者，接收消息的一方。
Message消息：实际的数据，如demo中的order订单消息载体。
Queue队列：是RabbitMQ的内部对象，用于存储消息，最终将消息传输到消费者。
Exchange交换机：在RabbitMQ中，生产者发送消息到交换机，由交换机将消息路由到一个或者多个队列中
RoutingKey路由键：生产者将消息发给交换器的时候，一般会指定一个RoutingKey，用来指定这个消息的路由规则。
Binding绑定：RabbitMQ中通过绑定将交换器与队列关联起来，在绑定的时候一般会指定一个绑定键（BindingKey），这样RabbitMQ就知道如何正确地将消息路由到队列。
##### 四、RabbitMQ使用
（1）简单的demo
![消息队列流程图.jpeg](https://upload-images.jianshu.io/upload_images/17109776-a5c9b15434a6eb17.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![简单的demo.png](https://upload-images.jianshu.io/upload_images/17109776-a407e6bd34d8153e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（2）示例代码
- RabbitmqConfig配置类，SimpleRabbitListenerContainerFactory Bean是消息监听容器，服务于监听者；RabbitTemplate是RabbitMQ发送消息的操作组件RabbitTemplate，此外配置类还有三个Bean，一个是队列basicQueue用于存储消息最终消息会被消费者监听到，basicExchange是交换机，生产者发送消息到交换机根据路由规则发送到相应的队列basicQueue上，basicBinding是负责绑定交换机basicExchange和队列basicQueue，根据路由规则绑定起来。创建队列、交换机的名词以及路由规则我都放到常量类RabbitMqConstants里面。
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    //自动装配消息监听器所在的容器工厂配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
    /**
     * 下面为单一消费者实例的配置
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        return factory;
    }
    //自定义配置RabbitMQ发送消息的操作组件RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置“发送消息后进行确认”
        connectionFactory.setPublisherConfirms(true);
        //设置“发送消息后返回确认信息”
        connectionFactory.setPublisherReturns(true);
        //构造发送消息组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送消息后，如果发送成功，则输出“消息发送成功”的反馈信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData,ack,cause));
        //发送消息后，如果发送失败，则输出“消息发送失败-消息丢失”的反馈信息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message));
        //定义消息传输的格式为JSON字符串格式
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //最终返回RabbitMQ的操作组件实例RabbitTemplate
        return rabbitTemplate;
    }
    //创建队列
    @Bean
    public Queue basicQueue(){
        return new Queue(RabbitMqConstants.BASIC_QUEUE,true);
    }
    //创建交换机：在这里以DirectExchange为例
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(RabbitMqConstants.BASIC_EXCHANGE,true,false);
    }
    //创建绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(RabbitMqConstants.BASICE_ROUTING_KEY);
    }
}
```
- RabbitMqConstants常量类，存放创建队列、交换机的名词以及路由规则。
```
@Data
public class RabbitMqConstants {
    //队列名词
    public static final String BASIC_QUEUE = "mq.basic.info.queue";
    //交换机名词
    public static final String BASIC_EXCHANGE = "mq.basic.info.exchange";
    //路由规则,实际为字符串
    public static final String BASICE_ROUTING_KEY = "mq.basic.info.routing.key";
}
```
- BasicPublisher 类，生产者，异步发送消息
```
@Component
@Slf4j
public class BasicPublisher {
    //定义RabbitMQ消息操作组件RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     * @param message 待发送的消息
     */
    public void sendMsg(Order message){
        try {
            //指定消息模型中的交换机
            rabbitTemplate.setExchange(RabbitMqConstants.BASIC_EXCHANGE);
            //指定消息模型中的路由
            rabbitTemplate.setRoutingKey(RabbitMqConstants.BASICE_ROUTING_KEY);
            //转化并发送消息
            rabbitTemplate.convertAndSend(message);
            log.info("rabbitmq demo-生产者-发送消息：{} ", JSONUtil.toJsonStr(message));
        } catch (Exception e) {
            log.error("rabbitmq demo-生产者-发送消息发生异常：{} ", message, e.fillInStackTrace());
        }
    }
}
```
- BasicConsumer 类，消费者，监听到消息时对消息进行处理，需要为消费者设置监听的队列mq.basic.info.queue以及监听容器singleListenerContainer。
```
@Component
@Slf4j
public class BasicConsumer {
    /**
     * 监听并接收消费队列中的消息-在这里采用单一容器工厂实例即可
     */
    @RabbitListener(queues = RabbitMqConstants.BASIC_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeMsg(Order message) {
        try {
            log.info("rabbitmq demo-消费者-监听消费到消息：{} ", JSONUtil.toJsonStr(message));
        } catch (Exception e) {
            log.error("rabbitmq demo-消费者-发生异常：", e.fillInStackTrace());
        }
    }
}
```
（3）安装好erlang语言以及rabbitmq之后，项目启动，访问http://127.0.0.1:15672，输入默认账号密码，可以看到：
![消息队列.png](https://upload-images.jianshu.io/upload_images/17109776-2ec4b339a5872cfd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![交换机.png](https://upload-images.jianshu.io/upload_images/17109776-e4aa65154bc16eb3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![交换机绑定关系.png](https://upload-images.jianshu.io/upload_images/17109776-2a68d5d517b6480b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（4）运行test方法：
```
    @Test
    public void testBasicPublish() {
        Order order = new Order();
        order.setOrdernum("123456");
        basicPublisher.sendMsg(order);
    }
```
![生产者发送消息.png](https://upload-images.jianshu.io/upload_images/17109776-f1b103c8918419e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![消费者监听消息.png](https://upload-images.jianshu.io/upload_images/17109776-d8b9e9fe00442645.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**参考资料：**
《分布式中间件实战》
《rabbitmq实战指南》

#### 《springboot rabbitmq不同交换机类型实战》
- 以下例子代码可在github或者在gitee下载
  github：[代码链接](https://github.com/fiuty/exercisegroup)
  gitee：[代码链接](https://gitee.com/fiuty/exercisegroup.git)
- 如果对springboot 使用rabbitmq还不太熟悉的话可以看上一篇博文：[springboot rabbitmq入门使用](https://www.jianshu.com/p/99c8e842fff7)
> RabbitMQ常用的交换器类型有fanout、direct、topic、headers这四种，其中headers实际很少用到。
fanout：把所有发送到该交换器的消息路由到所有与该交换器绑定的队列中。
direct：匹配规则相对简单，把消息路由到交换机和路由键RoutingKey绑定的队列中。
topic：匹配规则灵活，路由键RoutingKey可使用通配符" * " 和 “ # ”，代表匹配一个单词和任意单词。

代码目录结构如图，分别演示三种交换机类型：
![三种交换机类型.png](https://upload-images.jianshu.io/upload_images/17109776-eeae1eba81c4f3fa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 一、fanout广播消息模型
fanout广播交换机，当有多个消息队列需要监听同个消息进行不同的业务处理的时候，那么可以采用广播交换机，一个广播交换机，多个消息队列绑定该交换机，在发送消息的时候把消息发送到该交换机上，那么在监听消息端多个消息队列将监听到该消息，如图所示。
![fanout交换机流程图.jpeg](https://upload-images.jianshu.io/upload_images/17109776-291f78d057b7b5ca.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（1）首先在RabbitmqConfig中创建两个队列，fanoutQueueOne和fanoutQueueTwo，交换机类型为FanoutExchange，最后是两个队列与FanoutExchange交换机绑定起来。
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    /**
     * 创建消息模型-fanoutExchange
     */
    //广播fanout消息模型-队列1
    @Bean
    public Queue fanoutQueueOne(){
        return new Queue(RabbitMqConstants.FANOUT_ONE_QUEUE,true);
    }
    //广播fanout消息模型-队列2
    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue(RabbitMqConstants.FANOUT_TWO_QUEUE,true);
    }
    //广播fanout消息模型-创建交换机-fanoutExchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitMqConstants.FANOUT_EXCHANGE,true,false);
    }
    //广播fanout消息模型-创建绑定1
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    //广播fanout消息模型-创建绑定2
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }
}
```
RabbitMqConstants常量值如下：
```
@Data
public class RabbitMqConstants {
    //广播fanoutExchange消息模型
    public static final String FANOUT_ONE_QUEUE = "mq.fanout.one.queue";
    public static final String FANOUT_TWO_QUEUE = "mq.fanout.two.queue";
    public static final String FANOUT_EXCHANGE = "mq.fanout.exchange";
}
```
（2）启动项目，访问http://127.0.0.1:15672/可以看到我们设置的队列交换机以及绑定的路由相关信息：

![fanout两个消息队列.png](https://upload-images.jianshu.io/upload_images/17109776-63cc0c52ee3142e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![fanout广播交换机.png](https://upload-images.jianshu.io/upload_images/17109776-d5811d382a6fcd5c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![fanout广播交换机详情.png](https://upload-images.jianshu.io/upload_images/17109776-420692e394d433c0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![fanout消息队列详情-绑定关系.png](https://upload-images.jianshu.io/upload_images/17109776-3a80eb1bf72b0b42.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（3）fanout广播消息模型-生产者FanoutPublisher，RabbitMQ发送消息的操作组件RabbitTemplate设置fanout广播交换机，最后发送消息。
```
@Slf4j
@Component
public class FanoutPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     * @param order 订单消息
     */
    public void sendMsg(Order order){
        try {
            //设置广播式交换机FanoutExchange
            rabbitTemplate.setExchange(RabbitMqConstants.FANOUT_EXCHANGE);
            //发送消息
            rabbitTemplate.convertAndSend(order);
            //打印日志
            log.info("消息模型fanoutExchange-生产者-发送消息：{} ", order);
        }catch (Exception e){
            log.error("消息模型fanoutExchange-生产者-发送消息:{},发生异常： ", order, e);
        }
    }
}
```
（4）fanout广播消息模型-消费者FanoutConsumer，前面设置了两个队列，这里设置两个队列进行监听。
```
@Slf4j
@Component
public class FanoutConsumer {
    /**
     * 监听并消费队列中的消息-fanoutExchange-one-这是第一条队列对应的消费者
     */
    @RabbitListener(queues = RabbitMqConstants.FANOUT_ONE_QUEUE,containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgOne(Order order){
        try {
            log.info("消息模型fanoutExchange-one-消费者-监听消费到消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常：",e);
        }
    }
    /**
     * 监听并消费队列中的消息-fanoutExchange-two-这是第二条队列对应的消费者
     */
    @RabbitListener(queues = RabbitMqConstants.FANOUT_TWO_QUEUE,containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgTwo(Order order){
        try {
            log.info("消息模型fanoutExchange-two-消费者-监听消费到消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常：",e);
        }
    }
}
```
（5）最后调用test方法发送消息
```
    @Test
    public void testFanoutPublish() {
        Order order = new Order();
        order.setOrdernum("123456");
        fanoutPublisher.sendMsg(order);
    }
```

![测试fanout广播模型.png](https://upload-images.jianshu.io/upload_images/17109776-625e10086e5e1950.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![fanout消息队列监听消息.png](https://upload-images.jianshu.io/upload_images/17109776-c63377210e2b7f8c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 二、direct直连传输消息模型
direct交换机相对严谨，不像fanout广播交换机，direct交换机发送消息到消息队列的时候有一个路由规则，即路由键，这个路由键将指引交换机把消息指定到对应的队列之中进行消费，在实际开发中，direct交换机比较常用，当有某个特定消息需要被某一个队列进行消费处理的时候，可采用direct交换机。
![direct交换机流程图.jpeg](https://upload-images.jianshu.io/upload_images/17109776-3b81ba8ed51eb6c8.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（1）同样在RabbitmqConfig 配置类中创建两个队列directQueueOne、directQueueTwo，由directExchange用分别用两个路由键"mq.direct.routing.key.one"和"mq.direct.routing.key.two"绑定起来。
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    /**
     * 创建消息模型-directExchange
     */
    //直连传输direct消息模型-创建交换机-directExchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(RabbitMqConstants.DIRECT_EXCHANGE,true,false);
    }
    //直连传输direct消息模型-创建队列1
    @Bean
    public Queue directQueueOne(){
        return new Queue(RabbitMqConstants.DIRECT_ONE_QUEUE,true);
    }
    //直连传输direct消息模型-创建队列2
    @Bean
    public Queue directQueueTwo(){
        return new Queue(RabbitMqConstants.DIRECT_TWO_QUEUE,true);
    }
    //直连传输direct消息模型-创建绑定1
    @Bean
    public Binding directBindingOne(){
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(RabbitMqConstants.DIRECT_ONE_ROUTING_KEY);
    }
    //直连传输direct消息模型-创建绑定2
    @Bean
    public Binding directBindingTwo(){
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(RabbitMqConstants.DIRECT_TWO_ROUTING_KEY);
    }
}
```
RabbitMqConstants常量值如下：
```
@Data
public class RabbitMqConstants {
    //直连directExchange消息模型
    public static final String DIRECT_ONE_QUEUE = "mq.direct.one.queue";
    public static final String DIRECT_TWO_QUEUE = "mq.direct.two.queue";
    public static final String DIRECT_ONE_ROUTING_KEY = "mq.direct.routing.key.one";
    public static final String DIRECT_TWO_ROUTING_KEY = "mq.direct.routing.key.two";
    public static final String DIRECT_EXCHANGE = "mq.direct.exchange";
}
```
（2）启动项目，访问http://127.0.0.1:15672/可以看到我们设置的队列交换机以及绑定的路由相关信息：
![direct两个消息队列.png](https://upload-images.jianshu.io/upload_images/17109776-bba867bcd2231cba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![direct直连传输交换机.png](https://upload-images.jianshu.io/upload_images/17109776-8bbf328d99622ace.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![direct直连传输交换机详情.png](https://upload-images.jianshu.io/upload_images/17109776-46b0fae86f2f704c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![direct消息队列详情-绑定关系.png](https://upload-images.jianshu.io/upload_images/17109776-58e04072a07d22e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（3）directExchange直连传输消息模型-生产者DirectPublisher
```
@Slf4j
@Component
public class DirectPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息-基于DirectExchange消息模型-one
     */
    public void sendMsgDirectOne(Order order){
        try {
            //设置交换机
            rabbitTemplate.setExchange(RabbitMqConstants.DIRECT_EXCHANGE);
            //设置路由1
            rabbitTemplate.setRoutingKey(RabbitMqConstants.DIRECT_ONE_ROUTING_KEY);
            //发送消息
            rabbitTemplate.convertAndSend(order);
            //打印日志
            log.info("消息模型DirectExchange-one-生产者-发送消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ",order, e);
        }
    }
    /**
     * 发送消息-基于DirectExchange消息模型-two
     */
    public void sendMsgDirectTwo(Order order){
        try {
            //设置交换机
            rabbitTemplate.setExchange(RabbitMqConstants.DIRECT_EXCHANGE);
            //设置路由2
            rabbitTemplate.setRoutingKey(RabbitMqConstants.DIRECT_TWO_ROUTING_KEY);
            //发送消息
            rabbitTemplate.convertAndSend(order);
            //打印日志
            log.info("消息模型DirectExchange-two-生产者-发送消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型DirectExchange-two-生产者-发送消息:{},发生异常：{} ",order, e);
        }
    }
}
```
（4）directExchange直连传输消息模型-消费者DirectConsumer
```
@Slf4j
@Component
public class DirectConsumer {

    /** 这是第一个路由绑定的对应队列的消费者方法
     * 监听并消费队列中的消息-directExchange-one
     */
    @RabbitListener(queues = RabbitMqConstants.DIRECT_ONE_QUEUE,containerFactory = "singleListenerContainer")
    public void consumeDirectMsgOne(Order order){
        try {
            //打印日志消息
            log.info("消息模型directExchange-one-消费者-监听消费到消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型directExchange-one-消费者-监听消费发生异常：",e);
        }
    }

    /**
     * 这是第二个路由绑定的对应队列的消费者方法
     * 监听并消费队列中的消息-directExchange-two
     */
    @RabbitListener(queues = RabbitMqConstants.DIRECT_TWO_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeDirectMsgTwo(Order order) {
        try {
            //打印日志消息
            log.info("消息模型directExchange-two-消费者-监听消费到消息：{} ", order);
        } catch (Exception e) {
            log.error("消息模型directExchange-two-消费者-监听消费发生异常：", e);
        }
    }
}
```
（5）最后调用test方法发送消息
```
    @Test
    public void testDirectPublish() {
        Order order1 = new Order();
        order1.setOrdernum("one-123456");

        Order order2 = new Order();
        order2.setOrdernum("tow-123456");

        directPublisher.sendMsgDirectOne(order1);
        directPublisher.sendMsgDirectTwo(order2);
    }
```

![测试direct直连传输消息模型.png](https://upload-images.jianshu.io/upload_images/17109776-bf695f00f0a1fd29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![direct消息队列监听消息.png](https://upload-images.jianshu.io/upload_images/17109776-a72b6dfbe281df72.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 三、topic主题消息模型
topic交换机相对灵活，路由键规则有通配符" \* " 和 " # "符号代替了一个单词和零或者多个单词，例如当路由键有用通配符" * "符号的时候，即有一个路由键为“mq.topic.routing.key.\*”，那么在发送消息的时候，生产者设置了路由键为“mq.topic.routing.key.one”、“mq.topic.routing.key.two”、“mq.topic.routing.key.three”等等，都可以将该消息发送到topic交换机路由键为“mq.topic.routing.key.*”绑定的消息队列中，最终被监听到。
![topic交换机流程图.jpeg](https://upload-images.jianshu.io/upload_images/17109776-9fa9ad02d9256c7d.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
（1）同样在RabbitmqConfig 配置类中创建两个队列topicQueueOne、topicQueueTwo，由topicExchange用分别用两个路由键"mq.topic.routing.key.*"和"mq.topic.routing.key.#"绑定起来。

```
@Slf4j
@Configuration
public class RabbitmqConfig {
    //主题topic消息模型-创建交换机-topicExchange
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE,true,false);
    }
    //主题topic消息模型-创建队列1
    @Bean
    public Queue topicQueueOne(){
        return new Queue(RabbitMqConstants.TOPIC_ONE_QUEUE,true);
    }
    //主题topic消息模型-创建队列2
    @Bean
    public Queue topicQueueTwo(){
        return new Queue(RabbitMqConstants.TOPIC_TWO_QUEUE,true);
    }
    //主题topic消息模型-创建绑定-通配符为*的路由
    @Bean
    public Binding topicBindingOne(){
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(RabbitMqConstants.TOPIC_ONE_ROUTING_KEY);
    }

    //主题topic消息模型-创建绑定-通配符为#的路由
    @Bean
    public Binding topicBindingTwo(){
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(RabbitMqConstants.TOPIC_TWO_ROUTING_KEY);
    }
}
```
RabbitMqConstants常量值如下：
```
@Data
public class RabbitMqConstants {
    //主题topicExchange消息模型
    public static final String TOPIC_ONE_QUEUE = "mq.topic.one.queue";
    public static final String TOPIC_TWO_QUEUE = "mq.topic.two.queue";
    public static final String TOPIC_ONE_ROUTING_KEY = "mq.topic.routing.key.*";
    public static final String TOPIC_TWO_ROUTING_KEY = "mq.topic.routing.key.#";
    public static final String TOPIC_EXCHANGE = "mq.topic.exchange";
}
```
（2）启动项目，访问http://127.0.0.1:15672/可以看到我们设置的队列交换机以及绑定的路由相关信息：

![topic两个消息队列.png](https://upload-images.jianshu.io/upload_images/17109776-b4810bee31f84718.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![topic交换机.png](https://upload-images.jianshu.io/upload_images/17109776-4da7cd7a17516f47.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![topic交换机详情.png](https://upload-images.jianshu.io/upload_images/17109776-3c1cfc882b874d77.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![topic消息队列绑定关系.png](https://upload-images.jianshu.io/upload_images/17109776-e7d309d9f1e3e309.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（3）topicExchange消息模型-生产者topicPublisher
```
@Slf4j
@Component
public class TopicPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息-基于TopicExchange消息模型
     */
    public void sendMsgTopic(Order order, String routingKey){
        try {
            //指定交换机
            rabbitTemplate.setExchange(RabbitMqConstants.TOPIC_EXCHANGE);
            //指定路由的实际取值，根据不同取值，RabbitMQ将自行进行匹配通配符，从而路由到不同的队列中
            rabbitTemplate.setRoutingKey(routingKey);
            //发送消息
            rabbitTemplate.convertAndSend(order);
            //打印日志
            log.info("消息模型TopicExchange-生产者-发送消息：{},路由：{} ", order, routingKey);
        } catch (Exception e) {
            log.error("消息模型TopicExchange-生产者-发送消息:{},发生异常：{} ", order, e);
        }
    }
}
```
（4）topicExchange消息模型-消费者topicConsumer
```
@Slf4j
@Component
public class TopicConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 监听并消费队列中的消息-topicExchange-*通配符
     */
    @RabbitListener(queues = RabbitMqConstants.TOPIC_ONE_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeTopicMsgOne(Order order) {
        try {
            log.info("消息模型topicExchange-*-消费者-监听消费到消息：{} ", order);
        } catch (Exception e) {
            log.error("消息模型topicExchange-*-消费者-监听消费发生异常：", e);
        }
    }

    /**
     * 监听并消费队列中的消息-topicExchange-#通配符
     */
    @RabbitListener(queues = RabbitMqConstants.TOPIC_TWO_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeTopicMsgTwo(Order order) {
        try {
            log.info("消息模型topicExchange-#-消费者-监听消费到消息：{} ", order);
        } catch (Exception e) {
            log.error("消息模型topicExchange-#-消费者-监听消费发生异常：", e);
        }
    }
}
```
（5）最后调用test方法发送消息，路由键有：mq.topic.routing.key.java、mq.topic.routing.key.php.python、mq.topic.routing.key。
```
    @Test
    public void testTopicPublish() {
        //此时相当于*，即java替代了*的位置
        //当然由于#表示任意单词，因而也将路由到#表示的路由和对应的队列中
        String routingKeyOne="mq.topic.routing.key.java";
        //此时相当于#：即 php.python 替代了#的位置
        String routingKeyTwo="mq.topic.routing.key.php.python";
        //此时相当于#：即0个单词
        String routingKeyThree="mq.topic.routing.key";
        Order order = new Order();
        order.setOrdernum("123456");
        topicPublisher.sendMsgTopic(order,routingKeyOne);
        //topicPublisher.sendMsgTopic(order,routingKeyTwo);
        //topicPublisher.sendMsgTopic(order,routingKeyThree);
    }
```
![topic test方法.png](https://upload-images.jianshu.io/upload_images/17109776-33714fb19f3890b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![topic test结果.png](https://upload-images.jianshu.io/upload_images/17109776-eb0e141f9f861073.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**参考资料：**
《分布式中间件实战》
《rabbitmq实战指南》

#### 《springboot rabbitmq高可用消息确认消费实战》
- 以下例子代码可在github或者在gitee下载
  github：[代码链接](https://github.com/fiuty/exercisegroup)
  gitee：[代码链接](https://gitee.com/fiuty/exercisegroup.git)

> RabbitMQ的高可用主要体现在消息的发送、传输和接收的过程中，可以保证消息成功发送、不会丢失，以及被确认消费/不重复消费。

- 对于消息是否发送成功，主要是针对生产者端的消息生产确认机制；
- 对于消息不会丢失，主要是rabbitmq消息持久化机制；
- 对于消息确认消费/不重复消费，主要是针对消费者端对消息的确认消费机制。

##### 一、消息生产确认机制
对于消息是否发送成功，在rabbitmq自定义操作组件中可以统一设置消息生产确认相关逻辑rabbitTemplate.setConfirmCallback和rabbitTemplate.setReturnCallback。
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    //自定义配置RabbitMQ发送消息的操作组件RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置“发送消息后进行确认”
        connectionFactory.setPublisherConfirms(true);
        //设置“发送消息后返回确认信息”
        connectionFactory.setPublisherReturns(true);
        //构造发送消息组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送消息后，如果发送成功，则输出“消息发送成功”的反馈信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData,ack,cause));
        //发送消息后，如果发送失败，则输出“消息发送失败-消息丢失”的反馈信息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message));
        //定义消息传输的格式为JSON字符串格式
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //最终返回RabbitMQ的操作组件实例RabbitTemplate
        return rabbitTemplate;
    }
}
```
##### 二、消息持久化
1. 在创建交换机和队列的时候，有个durable的参数，即是否持久化，如果设置为true，当rabbitmq服务器重启的时候，创建的交换机和队列均还存在着，不会丢失；
2. 在发送消息的时候可以选择为该消息设置持久化，即消息体Message的deliveryMode设置为MessageDeliveryMode.PERSISTENT持久化，当消息来不及消费rabbitmq服务器重启，那么消息依旧存在，如果将所有消息都设置持久化，那么会影响性能，内存和磁盘的读写速度差异很大。
##### 三、消息确认消费机制
- 如何保证消息能够被准备消费、不重复消费，RabbitMQ提供了消息确认机制，即ACK模式。RabbitMQ的消息确认机制有3种，分别是NONE（无须确认）、AUTO（自动确认）和MANUAL（手动确认）。
- 无须确认流程图如下图所示，对于该模式，消息是否消费成功生产者端是不知道的，存在可能重复消费/消息消费失败的情况：
  ![无需确认.jpeg](https://upload-images.jianshu.io/upload_images/17109776-0291f9773eccf5c1.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 代码目录如图所示，演示自动确认和手动确认：
  ![自动确认和手动确认.png](https://upload-images.jianshu.io/upload_images/17109776-dd3862cb128aa2c0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
  对于设置ACK模式，可以在yaml配置文件中设置spring.rabbitmq.listener.simple.acknowledge-mode: xxx，也可以在声明的监听器Bean中设置，用简单监听器SimpleRabbitListenerContainerFactory即可：
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    /**
     * 确认消费模式为自动确认机制-AUTO,采用直连传输directExchange消息模型
     */
    @Bean
    public SimpleRabbitListenerContainerFactory singleListenerContainerAuto(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        //确认消费模式为自动确认机制
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 确认消费模式为手动确认机制-MANUAL,采用直连传输directExchange消息模型
     */
    @Bean
    public SimpleRabbitListenerContainerFactory singleListenerContainerManual(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        //确认消费模式为自动确认机制
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
```
（1）自动确认模式
自动确认模式流程图如图所示，RabbitMQ内置组件通知生产者端，当消息成功消费/消费失败都会通知：
![auto确认.jpeg](https://upload-images.jianshu.io/upload_images/17109776-df99a67aea0a80ac.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
对于自动确认模式，在消费者端可以看到和普通的消息队列没什么区别，而手工确认消费模式则比较灵活。
- 确认消费模式为自动确认机制-AUTO,采用直连传输directExchange消息模型-生产者
```
@Slf4j
@Component
public class AutoAckPublisher {
    //定义RabbitMQ消息操作组件RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        try {
            //设置交换机
            rabbitTemplate.setExchange(RabbitMqConstants.AUTO_ACKNOWLEDGE_EXCHANGE);
            //设置路由
            rabbitTemplate.setRoutingKey(RabbitMqConstants.AUTO_ACKNOWLEDGE_ROUTING_KEY);
            //发送消息
            rabbitTemplate.convertAndSend(order);
            log.info("确认消费模式为自动确认机制-消息模型DirectExchange-one-生产者-发送消息：{} ",order);
        }catch (Exception e){
            log.error("确认消费模式为自动确认机制-消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ",order, e);
        }
    }
}
```
- 确认消费模式为自动确认机制-AUTO,采用直连传输directExchange消息模型-消费者
```
@Slf4j
@Component
public class AutoAckConsumer {

    @RabbitListener(queues = RabbitMqConstants.AUTO_ACKNOWLEDGE_QUEUE, containerFactory = "singleListenerContainerAuto")
    public void consumeMsg(Order order) {
        try {
            log.info("基于AUTO的自动确认消费模式-消费者监听消费消息-内容为：{} ",order);
        }catch (Exception e){
            log.error("基于AUTO的自动确认消费模式-消费者监听消费消息:{},发生异常：", order, e);
        }
    }
}
```
（2）手工确认流程图如图所示，当消息处理过程中出现异常的时候，需要手工确认处理该异常消息，该消息是否重新归入队列等处理。
![manual确认.jpeg](https://upload-images.jianshu.io/upload_images/17109776-1c76c263a349aa01.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 确认消费模式为手动确认机制-MANUAL,采用直连传输directExchange消息模型-生产者
```
@Slf4j
@Component
public class ManualAckPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        try {
            rabbitTemplate.setExchange(RabbitMqConstants.MANUAL_ACKNOWLEDGE_EXCHANGE);
            rabbitTemplate.setRoutingKey(RabbitMqConstants.MANUAL_ACKNOWLEDGE_ROUTING_KEY);
            rabbitTemplate.convertAndSend(order);
            log.info("确认消费模式为手动确认机制-消息模型DirectExchange-one-生产者-发送消息：{} ", order);
        }catch (Exception e){
            log.error("确认消费模式为手动确认机制-消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ", order, e);
        }
    }
}
```
- 确认消费模式为手动确认机制-MANUAL,采用直连传输directExchange消息模型-消费者
  在监听到消息并且消息成功处理完之后，通过basicAck来确认消息成功消费，当捕获到异常的时候即该消息处理失败的时候，有两种方式，一种是拒绝该消息并且消息重新归入队列中，另一种是拒绝该消息并且丢弃掉，一般情况下重新归入队列，还是会出现异常没法消费掉，除非把异常修复了才行，并且在未修复该异常的情况下，后面的消息会被堵塞住没办法消费，将消息重新归入队列中或许不是一个好的选择。
  一般情况下可以保留该消息的信息然后把消息丢弃掉，最后重新发送消息；或者把该消息丢入到死信队列中，不对该死信队列进行监听，最后在rabbitmq管理后台取出该消息/重新监听该消息重新发送到原先队列进行消费，修复好异常情况再发送消息进行处理，保证消息成功消费。
```
@Slf4j
@Component
public class ManualAckConsumer {
    @RabbitListener(queues = RabbitMqConstants.MANUAL_ACKNOWLEDGE_QUEUE, containerFactory = "singleListenerContainerManual")
    public void consumeMsg(Order order, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        try {
            log.info("基于MANUAL的手工确认消费模式-消费者监听消费消息,消息投递标记：{},内容为：{} ", tag, order);
            //抛异常,归入使得消息重新归入队列
            //int num = 1 / 0;
            //执行完业务逻辑后，手动进行确认消费，其中第一个参数为：消息的分发标识(全局唯一);第二个参数：是否允许批量确认消费
            channel.basicAck(tag, false);
        }catch (Exception e){
            //第二个参数reueue重新归入队列,true的话会重新归入队列,需要人为地处理此次异常消息,重新归入队列也会继续异常
            channel.basicReject(tag, true);
            log.error("基于MANUAL的手工确认消费模式-消费者监听消费消息:{},消息投递标签：{},发生异常：", order, tag, e);
        }
    }
}
```
出现异常重新归入队列的情况，如图所示，显示有unacked 1条消息，下面有get messages，当点击的时候发现提示queue is empty队列为空，确实准备消费的消息为0条，正在消费的消息一直是unacked状态无法取出。
![unacked消息.png](https://upload-images.jianshu.io/upload_images/17109776-17e6e4f9a95534f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![取不出来.png](https://upload-images.jianshu.io/upload_images/17109776-c391cedf1b509b93.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这个时候只能停止监听重启项目，这个在线上不是好的办法，停止监听之后消息变为ready状态，这个时候可以取出，可以看到提示“取出消息是毁灭性的操作”。
![ready状态消息.png](https://upload-images.jianshu.io/upload_images/17109776-9804a2bb8f01a76f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![取出消息.png](https://upload-images.jianshu.io/upload_images/17109776-b8399ad0fc72d8c7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
四种取出消息的模式，分别为：不确认消息重新归入队列、确认消息不重新归入队列、拒绝该消息重新归入队列、拒绝该消息不重新归入队列。当取出消息可以看到消息的内容。
![取出消息模式.png](https://upload-images.jianshu.io/upload_images/17109776-65ed7445e5a1509d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![消息内容.png](https://upload-images.jianshu.io/upload_images/17109776-36ac6709b4678c2b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

对于确认消息消费，避免消息异常出现上述情况，可以用死信队列来处理，捕获异常消息，发送消息到死信队列，不监听该队列的消息，最后修复异常重新发送消息到原先队列进行消费，详情请看下篇博文

**参考资料：**
《分布式中间件实战》
《rabbitmq实战指南》

#### 《springboot rabbitmq死信队列与延迟队列实战》
- 以下例子代码可在github或者在gitee下载
  github：[代码链接](https://github.com/fiuty/exercisegroup)
  gitee：[代码链接](https://gitee.com/fiuty/exercisegroup.git)

在上一篇博文中提到，在消息确认消费的过程中，即消息处理过程中出现了异常，为避免消息重新归入队列又继续异常，也为了避免消息不归入队列而把消息丢弃掉，那么可以采用死信队列来处理该情况，当然这个也要结合实际场景，也不一定非要用死信队列，之前遇到过的场景就没采用死信队列，是这样的场景：同步订单后需要发送订单消息去处理，也没用死信队列，异常可以触发邮件告警，之后丢弃消息，而后处理完异常被丢弃的消息可以调用api触发再一次同步订单，故也没采用死信队列。

死信队列其实也是类似于普通的队列，有交换机、队列、路由等信息，只不过是叫做死信交换机和死信路由以及死信队列，相对特殊了一点，是在正常的队列中绑定了这个特殊的队列的交换机以及路由信息，这样一来正常的队列消息出现特殊的情况下（称为死信消息）可以把这个消息转向这个特殊的队列，即死信队列。

那么什么样的消息才会变成死信消息呢？
- 消息被拒绝（Basic.Reject/Basic.Nack），并且设置requeue参数为false；
- 消息过期；
- 队列达到最大长度。
  对于第一点，可以针对消息消费过程中出现异常情况，把消息拒绝转向死信队列，对于第二点，可以利用起来变成延迟队列，其实延迟队列也是死信队列的另一种体现，场景是给消息设置时间，消息时间一到即变成死信消息，转向死信队列，故也称为延迟队列，延迟队列在实际生产环境中有更加广泛的应用。

##### 一、死信队列
- 死信队列流程图
  ![死信队列流程图.jpg](https://upload-images.jianshu.io/upload_images/17109776-4e54367b56209ae4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 死信队列代码：
  ![死信队列代码.png](https://upload-images.jianshu.io/upload_images/17109776-2a453d5dfe5ac1d7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（1）首先是创建正常的队列以及在这个队列中绑定特殊的队列的交换机以及路由信息，在rabbitmq中是通过在创建队列的过程中增加附加参数x-dead-letter-exchange、x-dead-letter-routing-key，这两个参数是死信交换机和死信路由，我们知道在directExchange交换机类型中，交换机和路由可以为消息指引到队列，那么有了这两个参数，就可以绑定死信队列了。
- RabbitmqConfig配置类，创建两个队列，一个正常的普通队列directDeadPreQueue，以及死信队列deadQueue，普通队列创建的时候增加额外信息死信交换机x-dead-letter-exchange和死信路由x-dead-letter-routing-key。
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    //演示死信队列,为directExchange消息模型队列绑定死信队列
    @Bean
    public Queue directDeadPreQueue() {
        //创建死信队列的组成成分map，用于存放组成成分的相关成员
        Map<String, Object> args = new <String, Object>HashMap(2);
        //设死信交换机
        args.put("x-dead-letter-exchange", RabbitMqConstants.DEAD_EXCHANGE);
        //死信队列的路由
        args.put("x-dead-letter-routing-key", RabbitMqConstants.DEAD_ROUTING_KEY);
        return new Queue(RabbitMqConstants.DIRECT_QUEUE_DEAD_PRE, true, false, false, args);
    }
    //交换机
    @Bean
    public DirectExchange directDeadPreExchange() {
        return new DirectExchange(RabbitMqConstants.DIRECT_EXCHANGE_DEAD_PRE, true, false);
    }
    //交换机路由绑定队列
    @Bean
    public Binding directDeadPreBinding() {
        return BindingBuilder.bind(directDeadPreQueue()).to(directDeadPreExchange()).with(RabbitMqConstants.DIRECT_ROUTING_KEY_DEAD_PRE);
    }
    //死信队列
    @Bean
    public Queue deadQueue() {
        return new Queue(RabbitMqConstants.DEAD_QUEUE, true);
    }
    //死信交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(RabbitMqConstants.DEAD_EXCHANGE, true, false);
    }
    //路由交换机绑定死信队列
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(RabbitMqConstants.DEAD_ROUTING_KEY);
    }
}
```
- RabbitMqConstants常量值类
```
@Data
public class RabbitMqConstants {
    //演示死信队列,为directExchange消息模型队列绑定死信队列
    public static final String DIRECT_QUEUE_DEAD_PRE = "mq.direct.queue.dead.pre";
    public static final String DIRECT_EXCHANGE_DEAD_PRE = "mq.direct.exchange.dead.pre";
    public static final String DIRECT_ROUTING_KEY_DEAD_PRE = "mq.direct.routing.key.dead.pre";
    //死信队列
    public static final String DEAD_QUEUE = "mq.dead.queue";
    public static final String DEAD_EXCHANGE = "mq.dead.exchange";
    public static final String DEAD_ROUTING_KEY = "mq.dead.routing.key";
}
```
（2）创建完队列之后，启动项目，访问http://127.0.0.1:15672/，查看rabbitmq管理后台，可以看到普通队列新增了DLX、DLK的特性，即设置了死信交换机和死信路由。
![普通队列.png](https://upload-images.jianshu.io/upload_images/17109776-d238d1042137d660.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![普通队列详情.png](https://upload-images.jianshu.io/upload_images/17109776-8f9950d9a6eaf958.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（3）普通队列-生产者-OrdinaryPublisher
```
public class OrdinaryPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMsg(Order order) {
        try {
            //设置交换机、路由键,发送消息
            rabbitTemplate.convertAndSend(RabbitMqConstants.DIRECT_EXCHANGE_DEAD_PRE, RabbitMqConstants.DIRECT_ROUTING_KEY_DEAD_PRE, order);
            log.info("普通队列-生产者,发送消息：{}", order);
        } catch (Exception e) {
            log.error("普通队列-生产者,发送消息异常,消息：{},异常：", order, e);
        }
    }
}
```
（4）普通队列-消费者-OrdinaryConsumer，在监听到消息并且处理消息过程中故意抛除以0的异常，这样一来消息就被拒绝了。
```
public class OrdinaryConsumer {
    @RabbitListener(queues = RabbitMqConstants.DIRECT_QUEUE_DEAD_PRE, containerFactory = "singleListenerContainerManual")
    public void consumeMsg(Order order, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        try {
            log.info("普通队列-消费者,监听到消息：{},准备处理业务逻辑。", order);
            int i = 1 / 0;
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("普通队列-消费者,监听到消息：{},发生异常,消息不再归入队列中,转向死信队列,异常e：", order, e);
            //channel.basicNack(tag, false, false);
            channel.basicReject(tag, false);
        }
    }
}
```
（5）执行test方法
```
    @Test
    public void testDeadPrePublish() {
        Order order = new Order();
        order.setOrdernum("1234567");
        ordinaryPublisher.sendMsg(order);
    }
```
![test方法.png](https://upload-images.jianshu.io/upload_images/17109776-e4e44b4d6368dfbc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![test结果.png](https://upload-images.jianshu.io/upload_images/17109776-0efdeb54c4010643.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
这个时候由于我们的代码注释掉了监听死信队列，故打开rabbitmq管理后台可以看到死信队列中存着死信消息：
![死信队列.png](https://upload-images.jianshu.io/upload_images/17109776-0c197b4bfd47c435.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![死信队列详情.png](https://upload-images.jianshu.io/upload_images/17109776-862dd745882a2004.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以有两种种方式来解决这个问题，当然问题的根本是把异常处理完，然后消息重新消费。我们假设现在已经把1/0这个异常代码修复了即注释掉了。
- 方式一，在rabbitmq管理后台取出该消息并且在rabbitmq管理后台发送该消息到普通队列中
  手工取出异常消息：{"ordernum":"1234567"}
  ![手工取出异常消息.png](https://upload-images.jianshu.io/upload_images/17109776-ba353de10d95aa85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
  发送异常消息到原先的普通队列mq.direct.queue.dead.pre进行再次消费
  ![发送异常消息到原先的普通队列.png](https://upload-images.jianshu.io/upload_images/17109776-da467b3a55b75a32.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 方式二，在代码中监听死信队列
  在代码中监听该死信队列，动态配置一个配置值，标识是否修复了异常信息，如果修复了，那么发送消息到原先的普通队列进行消费，如果未修复，那么消息重新归入死信队列，直到修复了异常。
  监听死信队列代码DeadQueueConsumer：
```
public class DeadQueueConsumer {
    @Autowired
    private OrdinaryPublisher ordinaryPublisher;
    //为方便演示,写死在这里,实际可以用配置中心apollo或者阿里云naocs动态刷新该值,即修复bug之后刷新该值为true
    private Boolean dynamicRepairSign = false;
    //可以注释掉监听,在rabbitmq管理后台取出该消息,等到异常处理完之后把该消息丢回原先的队列进行处理。
    @RabbitListener(queues = RabbitMqConstants.DEAD_QUEUE, containerFactory = "singleListenerContainerManual")
    public void consumeMsg(Order order, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        log.info("死信队列监听到消息：{}", order);
        if (dynamicRepairSign) {
            //修复完异常之后发送消息到原先队列进行消费
            ordinaryPublisher.sendMsg(order);
            channel.basicAck(tag, false);
        } else {
            channel.basicReject(tag, true);
        }
    }
}
```
这样的话也可以不用像方式一那样去rabbitmq管理后台取出死信队列中的消息，然后再把消息手工发送到原先的队列中，当然死信队列也不是一定要用到，要视实际场景而定。
##### 二、延迟队列
- 延迟队列流程图：
  ![延迟队列流程图.jpg](https://upload-images.jianshu.io/upload_images/17109776-8f0e0a3cbcedbdab.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 延迟队列代码：
  ![延迟队列代码.png](https://upload-images.jianshu.io/upload_images/17109776-9781efa6e4ee79a7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 前面提到，延迟队列是死信队列的特殊情况，因为消息设置了TTL时间，消息过期了变成死信，继而可以利用该特性来做我们想做的事情，监听死信队列，在死信队列中对死信消息做业务处理，所以也称之为延迟队列。

- 由于其特性，消息在某段时间过后进行消费处理，延迟队列在实际开发中应用很广泛，比如12306或者其他电商等平台，下单之后，30分钟内如果未付款，那么自动取消该订单并且释放相应的库存，类似于这样的场景，用延迟队列是个很好的选择，可能有人会说，那么也可以通过用定时器的方式来实现该功能，但是定时器是轮询地去查数据库，如果在订单量很大或者像12306这样，每隔一段时间去查询一次数据库，还有多少订单未付款并且到了30分钟，这样的话会给系统数据库造成很大的压力，有可能还会压垮系统奔溃掉。

- 死信队列是在普通的队列中新增两个附加参数，即死信交换机和死信路由，那么延迟队列其实实现起来也很简单，由于消息过期不消费也会变成死信，那么在发送消息的时候设置消息过期时间，同时不对该普通队列进行监听消费，那么该消息不就一定会过期变成死信消息了，继而最后消息被转向了延迟队列中。

（1）首先是创建正常的队列以及在这个队列中绑定特殊的队列的交换机以及路由信息，像死信队列一样在创建队列的过程中增加附加参数x-dead-letter-exchange、x-dead-letter-routing-key，死信交换机和死信路由，另外为了实现延迟队列，需要再增加额外的参数，消息过期时间TTL，x-message-ttl参数，最后死信交换机和死信路由通过绑定关系绑定延迟队列，结合消息的TTL达到延迟的消费的作用。
- RabbitmqConfig配置类创建普通队列和延迟队列，普通队列的消息设置了TTL时间为30s
```
@Slf4j
@Configuration
public class RabbitmqConfig {
    //延迟队列
    @Bean
    public Queue delayQueuePre() {
        //创建延迟队列的组成成分map，用于存放组成成分的相关成员
        Map<String, Object> args = new <String, Object>HashMap(16);
        //设置消息过期之后的死信交换机(真正消费的交换机)
        args.put("x-dead-letter-exchange", RabbitMqConstants.DELAY_EXCHANGE);
        //设置消息过期之后死信队列的路由(真正消费的路由)
        args.put("x-dead-letter-routing-key", RabbitMqConstants.DELAY_ROUTING_KEY);
        //设定消息的TTL，单位为ms，在这里指的是30s
        args.put("x-message-ttl", 30000);
        return new Queue(RabbitMqConstants.DELAY_QUEUE_PRE, true,false,false, args);
    }
    //直连传输directExchange消息模型-交换机
    @Bean
    public DirectExchange delayExchangePre() {
        return new DirectExchange(RabbitMqConstants.DELAY_EXCHANGE_PRE, true, false);
    }
    //直连传输directExchange消息模型-路由交换机绑定队列
    @Bean
    public Binding delayBindingPre() {
        return BindingBuilder.bind(delayQueuePre()).to(delayExchangePre()).with(RabbitMqConstants.DELAY_ROUTING_KEY_PRE);
    }
    //延迟队列（真正处理消息的队列）
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitMqConstants.DELAY_QUEUE, true);
    }
    //死信交换机（真正处理消息的交换机）
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(RabbitMqConstants.DELAY_EXCHANGE, true, false);
    }
    //死信交换机、死信路由绑定延迟队列
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(RabbitMqConstants.DELAY_ROUTING_KEY);
    }
}
```
- RabbitMqConstants常量值
```
@Data
public class RabbitMqConstants {
    //演示延迟队列,为directExchange消息模型队列绑定延迟队列
    public static final String DELAY_QUEUE_PRE = "mq.direct.queue.delay.pre";
    public static final String DELAY_EXCHANGE_PRE = "mq.direct.exchange.delay.pre";
    public static final String DELAY_ROUTING_KEY_PRE = "mq.routing.key.delay.pre";
    //延迟队列
    public static final String DELAY_QUEUE = "mq.delay.queue";
    public static final String DELAY_EXCHANGE = "mq.delay.exchange";
    public static final String DELAY_ROUTING_KEY = "mq.delay.routing.key";
}
```
（2）创建完队列之后，启动项目，访问http://127.0.0.1:15672/，查看rabbitmq管理后台

![普通队列、延迟队列.png](https://upload-images.jianshu.io/upload_images/17109776-ee4c89151a98636b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![普通队列详情绑定延迟队列.png](https://upload-images.jianshu.io/upload_images/17109776-d761eaf11a89c0de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![延迟队列详情.png](https://upload-images.jianshu.io/upload_images/17109776-52763a3814ecbf46.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（3）发送消息到普通队列中，并且不对该队列进行监听消费消息，让该消息自动达到过期时间转向延迟队列中
- 普通队列-生产者-DelayQueuePrePublisher
```
public class DelayQueuePrePublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        try {
            //设置延迟队列交换机、延迟队列路由键,消息实体并且发送消息
            rabbitTemplate.convertAndSend(RabbitMqConstants.DELAY_EXCHANGE_PRE, RabbitMqConstants.DELAY_ROUTING_KEY_PRE, order);
            log.info("延迟队列消息发送成功,消息：{},发送时间：{}", order, LocalDateTime.now());
        } catch (Exception e) {
            log.error("延迟队列消息发送异常,消息：{},异常e：", order, e);
        }
    }
}
```
（4）监听延迟队列，当普通队列中的消息30秒过期了之后变成死信消息，会转向被该队列监听到
- 延迟队列-消费者-DelayQueueConsumer
```
public class DelayQueueConsumer {
    @RabbitListener(queues = RabbitMqConstants.DELAY_QUEUE, containerFactory = "singleListenerContainerAuto")
    public void consumeMsg(Order order) {
        try {
            log.info("延迟队列-30s时间到达后,真正消费消息的队列,监听消息：{},当前时间：{}", order, LocalDateTime.now());
        } catch (Exception e) {
            log.error("延迟队列-30s时间到达后,真正消费消息的队列,监听消息：{},处理发生异常e：", order, e);
        }
    }
}
```
（5）运行test方法：
```
    @Test
    public void testDelayPublish() {
        Order order = new Order();
        order.setOrdernum("1234567");
        delayQueuePrePublisher.sendMsg(order);
    }
```
从打印日志可以看出，发送消息时间是：2021-03-02 01:25:14.976，消费消息时间是：2021-03-02 01:25:44.992，相差是30秒（4毫秒误差就不计啦），从而实现了延迟队列的功能。
![发送消息时间.png](https://upload-images.jianshu.io/upload_images/17109776-7358557b0f8d20da.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![消费消息时间.png](https://upload-images.jianshu.io/upload_images/17109776-3f9732b9dc5c7741.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**参考资料：**
《rabbitmq实战指南》
《分布式中间件实战》
