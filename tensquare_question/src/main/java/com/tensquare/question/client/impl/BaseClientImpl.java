package com.tensquare.question.client.impl;

import com.tensquare.question.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-18 14:19
 **/
@Component
public class BaseClientImpl implements BaseClient{
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR,"熔断器触发了");
    }
}

