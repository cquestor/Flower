package com.halfsay.info.controller;


import com.halfsay.info.pojo.Flowerinfo;
import com.halfsay.info.pojo.Result;
import com.halfsay.info.service.IFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoController {
    private IFlowerService flowerService;

    @Autowired
    public InfoController(IFlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @RequestMapping(value = "/flower/{string}", method = RequestMethod.GET)
    public Result echo(@PathVariable String string) {
        Result result = new Result();
        try {
            List<Flowerinfo> flowers = flowerService.findFlower(string);
            if (flowers != null && flowers.size() > 0) {
                result.setStatusCode(200);
                result.setMessage("查询成功");
                result.setData(flowers);
            } else {
                result.setStatusCode(300);
                result.setMessage("未查询到相关数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(500);
            result.setMessage("查询出错，请与管理员联系");
        }
        return result;
    }
}
