package com.taosun.jmsqueue;

import com.taosun.activedemo.jms.queue.QueueProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-jms-producer.xml")
public class TestQueuePro {

    //注入消费者
    @Autowired
    private QueueProducer queueProducer;

    @Test
    public void sendQueueMes(){
        for (int i=0;i<50;i++){
            queueProducer.sendTextMessage("springjms点对点");
        }

    }


}
