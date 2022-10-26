package com.halfsay.flower.service;

import com.halfsay.flower.pojo.CardType;

import java.util.List;

public interface ICardTypeService {
    /**
     * 获取所有会员卡类型
     *
     * @return 所有会员卡类型
     */
    public List<CardType> getList();

    /**
     * 检查会员卡名称
     *
     * @param cardname 会员卡名称
     * @return 会员卡类型
     */
    public CardType ckCardName(String cardName);

    /**
     * 增加会员卡类型
     *
     * @param cardType 会员卡类型
     * @return 成功/否
     */
    public int add(CardType cardType);

    /**
     * 更新会员卡信息
     *
     * @param cardType 会员卡信息
     * @return 成功/否
     */
    public int update(CardType cardType);

    /**
     * 删除会员卡
     *
     * @param id 会员卡id
     * @return 成功/否
     */
    public int delete(Integer id);

    /**
     * 按id查找会员卡
     *
     * @param id 待查找id
     * @return 会员卡信息
     */
    public CardType findById(Integer id);
}
