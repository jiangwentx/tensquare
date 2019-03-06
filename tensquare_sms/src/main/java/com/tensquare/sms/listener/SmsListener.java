package com.tensquare.sms.listener;

import com.tensquare.sms.utils.SMSUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-02 17:02
 **/
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void executerSms(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        SMSUtil.getCode(checkcode,mobile);
    }
}

