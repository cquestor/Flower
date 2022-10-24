package com.halfsay.flower.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halfsay.flower.pojo.BatchIds;
import com.halfsay.flower.pojo.Card;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin
public class CardController {
    private ICardService cardService;

    @Autowired
    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping({"/getpagelist/{pageIndex}/{pageSize}", "/getpagelist/{pageIndex}", "/getpagelist"})
    public Result getList(@PathVariable(value = "pageIndex", required = false) Integer pageIndex, @PathVariable(value = "pageSize", required = false) Integer pageSize, @RequestBody Card model) {
        Result result = new Result();
        try {
            pageIndex = pageIndex == null || pageIndex < 0 ? 1 : pageIndex;
            pageSize = pageSize == null || pageSize < 1 ? 12 : pageSize;
            PageHelper.startPage(pageIndex, pageSize);
            List<Card> list = cardService.getPageList(model);
            PageInfo<Card> pageInfo = new PageInfo<>(list);
            if (pageInfo != null && pageInfo.getTotal() > 0) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(pageInfo);
            } else {
                result.setStatusCode(404);
                result.setMessage("没有查询到数据");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("查询数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody BatchIds ids) {
        Result result = new Result();
        try {
            cardService.delete(ids.getIds());
            result.setStatusCode(200);
            result.setMessage("success");
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Card model) {
        Result result = new Result();
        try {
            int rows = cardService.add(model);
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

    @RequestMapping("/update")
    public Result update(@RequestBody Card model) {
        Result result = new Result();
        try {
            int rows = cardService.update(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功修改数据");
            } else {
                result.setStatusCode(300);
                result.setMessage("修改数据失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }

    @RequestMapping("/findbyid/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        try {
            Card model = cardService.findById(id);
            if (model != null) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(model);
            } else {
                result.setStatusCode(404);
                result.setMessage("没有找到对应的会员信息");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }
}
