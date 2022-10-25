package com.halfsay.flower.service;

import com.halfsay.flower.pojo.CardUseRecord;

import java.util.List;

public interface ICardUseRecordService {
    /**
     * 增加消卡记录
     *
     * @param model 会员卡信息
     * @return 成功/否
     */
    public int add(CardUseRecord model);

    /**
     * 分页获取会员卡消费记录
     *
     * @param model 会员卡信息
     * @return 消费记录
     */
    public List<CardUseRecord> getPageList(CardUseRecord model);

    /**
     * 删除消费记录
     *
     * @param ids 记录ids
     * @return 成功/否
     */
    public int delete(String[] ids);
}
