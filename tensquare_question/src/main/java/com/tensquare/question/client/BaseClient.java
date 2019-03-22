package com.tensquare.question.client;

import com.tensquare.question.client.impl.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="tensquare-base",fallback = BaseClientImpl.class)
public interface BaseClient {
    @RequestMapping(value="/label/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);
}
