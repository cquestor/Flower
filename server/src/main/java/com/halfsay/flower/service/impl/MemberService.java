package com.halfsay.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsay.flower.mapper.CardMapper;
import com.halfsay.flower.mapper.CardUseRecordMapper;
import com.halfsay.flower.mapper.MemberMapper;
import com.halfsay.flower.pojo.Member;
import com.halfsay.flower.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MemberService implements IMemberService {
    private MemberMapper memberMapper;
    private CardMapper cardMapper;
    private CardUseRecordMapper cardUseRecordMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper, CardMapper cardMapper, CardUseRecordMapper cardUseRecordMapper) {
        this.memberMapper = memberMapper;
        this.cardMapper = cardMapper;
        this.cardUseRecordMapper = cardUseRecordMapper;
    }

    @Override
    public List<Member> getMembersPage(String name) {
        name = "null".equals(name) || "".equals(name) ? null : name;
        return memberMapper.getMembersPage(name);
    }

    @Transactional
    @Override
    public int delete(String[] ids) {
        for (String id : ids) {
            cardUseRecordMapper.deleteByMid(id);
        }
        for (String id : ids) {
            cardMapper.deleteById(id);
        }
        return memberMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int add(Member member) {
        member.setDate(new Date());
        return memberMapper.insert(member);
    }

    @Override
    public int update(Member member) {
        return memberMapper.updateById(member);
    }

    @Override
    public Member findById(Integer id) {
        return memberMapper.selectById(id);
    }

    @Override
    public List<Member> getList() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return memberMapper.selectList(queryWrapper);
    }
}
