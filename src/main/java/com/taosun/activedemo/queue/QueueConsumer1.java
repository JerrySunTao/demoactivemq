package com.taosun.activedemo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *不能使用同一个连接去获取多个consumer对象，因为不管获取多少个，最终他们都是同一个对象，
 * 若要创建不同的消费者则需要重新获取连接后创建
 */
public class QueueConsumer1 {

    public static void main(String[] args) {

        try {
            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.106:61616");
            //2.获取连接
            Connection connection = connectionFactory.createConnection();
            //3.启动连接
            connection.start();
            //4.获取session(参数1：是否启动事务,参数2：消息确认模式)
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //5.创建队列对象
            Queue queue = session.createQueue("test-queue");
            //6.创建消息消费
            MessageConsumer consumer1 = session.createConsumer(queue);
            //MessageConsumer consumer2 = session.createConsumer(queue);
            System.out.println(consumer1);
            //System.out.println(consumer2);
            //7.监听消息
            consumer1.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收到消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            //8.等待键盘输入
            System.in.read();
            //9.关闭资源
            consumer1.close();
            session.close();
            connection.close();

        }catch (Exception e){

        }



    }

}


