package com.halfsay.flower.service;

import com.halfsay.flower.pojo.Card;

import java.util.List;

public interface ICardService {
    /**
     * 删除会员卡
     *
     * @param ids 会员卡id
     * @return 成功/否
     */
    public int delete(String[] ids);

    /**
     * 添加会员卡
     *
     * @param card 会员卡信息
     * @return 成功/否
     */
    public int add(Card card);

    /**
     * 修改会员卡信息
     *
     * @param card 会员卡信息
     * @return 成功/否
     */
    public int update(Card card);

    /**
     * 查找会员卡信息
     *
     * @param id 会员卡id
     * @return 会员卡信息
     */
    public Card findById(Long id);

    /**
     * 分页查找会员卡信息
     *
     * @param model 会员卡信息
     * @return 会员卡信息列表
     */
    public List<Card> getPageList(Card model);
}
