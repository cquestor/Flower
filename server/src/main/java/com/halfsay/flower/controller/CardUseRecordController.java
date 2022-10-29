package com.halfsay.flower.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halfsay.flower.pojo.BatchIds;
import com.halfsay.flower.pojo.Card;
import com.halfsay.flower.pojo.CardUseRecord;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.service.impl.CardUseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping({"/getpagelist/{pageIndex}/{pageSize}","/getpagelist/{pageIndex}","/getpagelist"})
    public Result getPageList(@PathVariable(value = "pageIndex",required = false) Integer pageIndex, @PathVariable(value = "pageSize",required = false) Integer pageSize, @RequestBody CardUseRecord model){
        Result result = new Result();
        try{
            pageIndex = pageIndex==null || pageIndex <0 ? 1:pageIndex;
            pageSize = pageSize==null || pageSize < 1 ? 10:pageSize;
            PageHelper.startPage(pageIndex,pageSize);
            List<CardUseRecord> list = cardUseRecordService.getPagelist(model);
            PageInfo<CardUseRecord> pageInfo = new PageInfo<>(list);
            if(pageInfo!=null && pageInfo.getTotal()>0){
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(pageInfo);
            }
            else{
                result.setStatusCode(404);
                result.setMessage("没有查询到数据");
            }
        }catch (Exception ex){
            System.out.println(ex);
            result.setStatusCode(500);
            result.setMessage("查询数据出错，请与管理员了联系");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody BatchIds bids){
        Result result = new Result();
        try {
            int rows = cardUseRecordService.delete(bids.getIds());
            result.setStatusCode(200);
            result.setMessage("success");
        }catch (Exception ex){
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }
}
