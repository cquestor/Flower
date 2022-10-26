package com.halfsay.flower.controller;

import com.halfsay.flower.pojo.CardType;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.service.ICardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardtype")
@CrossOrigin
public class CardTypeController {
    private ICardTypeService cardTypeService;

    @Autowired
    public CardTypeController(ICardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    @RequestMapping("/getlist")
    public Result getList() {
        Result result = new Result();
        try {
            List<CardType> list = cardTypeService.getList();
            if (list != null && list.size() > 0) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(list);
            } else {
                result.setStatusCode(300);
                result.setMessage("没有查询到数据");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("查询数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/ckcardname/{cardName}")
    public Result ckCardName(@PathVariable("cardName") String cardName) {
        Result result = new Result();
        try {
            CardType model = cardTypeService.ckCardName(cardName);
            if (model != null) {
                result.setStatusCode(200);
                result.setMessage("此充值卡名称已被使用");
            } else {
                result.setStatusCode(404);
                result.setMessage("此充值卡名称可以使用");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(500);
            result.setMessage("查找数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody CardType cardType) {
        Result result = new Result();
        try {
            if (cardTypeService.add(cardType) > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功添加会员卡类型");
            } else {
                result.setStatusCode(300);
                result.setMessage("添加会员卡类型失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("添加会员卡类型出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody CardType cardType) {
        Result result = new Result();
        try {
            if (cardTypeService.update(cardType) > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功修改会员卡类型");
            } else {
                result.setStatusCode(300);
                result.setMessage("修改会员卡类型失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("修改会员卡类型出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/del/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Result result = new Result();
        try {
            if (cardTypeService.delete(id) > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功删除会员卡类型");
            } else {
                result.setStatusCode(300);
                result.setMessage("删除会员卡类型失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("删除会员卡类型出错");
        }
        return result;
    }

    @RequestMapping("/findbyid/{id}")
    public Result findById(@PathVariable Integer id) {
        Result result = new Result();
        try {
            CardType cardType = cardTypeService.findById(id);
            if (cardType != null) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(cardType);
            } else {
                result.setStatusCode(300);
                result.setMessage("没有查找到对应的用户信息");
            }
        } catch (Exception ex) {
            result.setStatusCode(500);
            result.setMessage("fail");
        }
        return result;
    }
}
