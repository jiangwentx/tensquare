package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-02 14:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= RabbitApplication.class)
public class ProducterTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend1(){
        rabbitTemplate.convertAndSend("itcast","rabbitmq生产者测试");
    }

    /*
    * 分裂模式
    * */
    @Test
    public void testSend2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式");
    }

    /*
    * 主题模式
    * */
    @Test
    public void testSend3(){
        rabbitTemplate.convertAndSend("topic84","good.log","主题模式");
    }
}

