package com.tensquare.rabbit.custem;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-02 14:39
 **/

@Component
@RabbitListener(queues = "kudingyu")
public class Custemer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("kudingyu:"+msg);
    }
}

