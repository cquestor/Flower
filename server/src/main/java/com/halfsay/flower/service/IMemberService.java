package com.halfsay.flower.service;

import com.halfsay.flower.pojo.Member;

import java.util.List;

public interface IMemberService {
    public List<Member> getMembersPage(String name);

    public int delete(String[] ids);

    public int add(Member member);

    public int update(Member member);

    public Member findById(Integer id);

    public List<Member> getList();
}
