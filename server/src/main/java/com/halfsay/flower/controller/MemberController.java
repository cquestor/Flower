package com.halfsay.flower.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halfsay.flower.pojo.BatchIds;
import com.halfsay.flower.pojo.Member;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {
    private IMemberService memberService;

    @Autowired
    public MemberController(IMemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping({"/getpagelist/{pageIndex}/{pageSize}/{name}", "/getpagelist/{pageIndex}/{pageSize}", "/getpagelist/{pageIndex}"})
    public Result getPageList(@PathVariable(value = "pageIndex", required = false) Integer pageIndex, @PathVariable(value = "pageSize", required = false) Integer pageSize, @PathVariable(value = "name", required = false) String name) {
        Result result = new Result();
        try {
            pageIndex = pageIndex == null || pageIndex < 0 ? 1 : pageIndex;
            pageSize = pageSize == null || pageSize < 1 ? 12 : pageSize;
            PageHelper.startPage(pageIndex, pageSize);
            List<Member> list = memberService.getMembersPage(name);
            PageInfo<Member> pageInfo = new PageInfo<>(list);
            if (pageInfo != null && pageInfo.getTotal() > 0) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(pageInfo);
            } else {
                result.setStatusCode(404);
                result.setMessage("没有查询到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(500);
            result.setMessage("查询数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/del")
    public Result deleteBatchIds(@RequestBody BatchIds ids) {
        Result result = new Result();
        try {
            int rows = memberService.delete(ids.getIds());
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功删除工作人员的数据");
            } else {
                result.setStatusCode(300);
                result.setMessage("删除工作人员失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("删除数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Member model) {
        Result result = new Result();
        try {
            int rows = memberService.add(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功添加新的工作人员");
            } else {
                result.setStatusCode(300);
                result.setMessage("添加新的工作人员失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("添加数据错误，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/findbyid/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        Result result = new Result();
        try {
            Member model = memberService.findById(id);
            if (model != null) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(model);
            } else {
                result.setStatusCode(300);
                result.setMessage("没有查找数据");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("查找数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Member model) {
        Result result = new Result();
        try {
            int rows = memberService.update(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功修改了会员信息");
            } else {
                result.setStatusCode(300);
                result.setMessage("修改会员信息失败");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("修改数据出错，请与管理员联系");
        }
        return result;
    }
}
