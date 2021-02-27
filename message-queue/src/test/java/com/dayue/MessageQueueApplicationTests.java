package com.dayue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.BasicPublisher;
import com.dayue.rabbitmq.ackmodel.auto.AutoAckPublisher;
import com.dayue.rabbitmq.ackmodel.manual.ManualAckPublisher;
import com.dayue.rabbitmq.deadqueue.OrdinaryPublisher;
import com.dayue.rabbitmq.delayqueue.DelayQueuePublisher;
import com.dayue.rabbitmq.priorityqueue.PriorityPublisher;
import com.dayue.springevent.OrderPublisher;
import com.dayue.rabbitmq.exchangemodel.direct.*;
import com.dayue.rabbitmq.exchangemodel.fanout.*;
import com.dayue.rabbitmq.exchangemodel.topic.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageQueueApplicationTests {

    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private BasicPublisher basicPublisher;

    @Autowired
    private FanoutPublisher fanoutPublisher;

    @Autowired
    private DirectPublisher directPublisher;

    @Autowired
    private TopicPublisher topicPublisher;

    @Autowired
    private AutoAckPublisher autoAckPublisher;

    @Autowired
    private ManualAckPublisher manualAckPublisher;

    @Autowired
    private DelayQueuePublisher delayQueuePublisher;

    @Autowired
    private OrdinaryPublisher ordinaryPublisher;

    @Autowired
    private PriorityPublisher priorityPublisher;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        orderPublisher.sendMsg();
    }

    @Test
    public void testBasicPublish() {
        Order order = new Order();
        order.setOrdernum("123456");
        basicPublisher.sendMsg(order);
    }

    @Test
    public void testFanoutPublish() {
        Order order = new Order();
        order.setOrdernum("123456");
        fanoutPublisher.sendMsg(order);
    }

    @Test
    public void testDirectPublish() {
        Order order1 = new Order();
        order1.setOrdernum("one-123456");

        Order order2 = new Order();
        order2.setOrdernum("tow-123456");

        directPublisher.sendMsgDirectOne(order1);
        directPublisher.sendMsgDirectTwo(order2);
    }

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

    @Test
    public void testAutoAckPublish() {
        Order order = new Order();
        order.setOrdernum("123456");
        autoAckPublisher.sendMsg(order);
    }

    @Test
    public void testManualAckPublish() {
        Order order = new Order();
        order.setOrdernum("1234567");
        manualAckPublisher.sendMsg(order);
    }

    @Test
    public void testDelayPublish() {
        Order order = new Order();
        order.setOrdernum("1234567");
        delayQueuePublisher.sendMsg(order);
    }

    @Test
    public void testDeadPrePublish() {
        Order order = new Order();
        order.setOrdernum("1234567");
        ordinaryPublisher.sendMsg(order);
    }

    @Test
    public void testPriorityPublish() throws InterruptedException {
        Order order1 = new Order();
        order1.setOrdernum("123");

        Order order2 = new Order();
        order2.setOrdernum("456");
        //发送订单1
        priorityPublisher.sendMsg(order1, 1);
        //延迟两秒后发送订单2
        Thread.sleep(2000);
        //发送订单2
        priorityPublisher.sendMsg(order2, 2);
    }

}
