package com.halfsay.flower.controller;

import com.halfsay.flower.pojo.Card;
import com.halfsay.flower.pojo.CardUseRecord;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.service.impl.CardUseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usecard")
@CrossOrigin
public class CardUseRecordController {
    private CardUseRecordService cardUseRecordService;

    @Autowired
    public CardUseRecordController(CardUseRecordService cardUseRecordService) {
        this.cardUseRecordService = cardUseRecordService;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody CardUseRecord model) {
        Result result = new Result();
        try {
            int rows = cardUseRecordService.add(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("success");
            } else {
                result.setStatusCode(300);
                result.setMessage("添加数据失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }
}
