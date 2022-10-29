package com.halfsay.flower.service.impl;

import com.halfsay.flower.mapper.CardMapper;
import com.halfsay.flower.mapper.CardTypeMapper;
import com.halfsay.flower.mapper.CardUseRecordMapper;
import com.halfsay.flower.pojo.Card;
import com.halfsay.flower.pojo.CardType;
import com.halfsay.flower.pojo.CardUseRecord;
import com.halfsay.flower.service.ICardUseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CardUseRecordService implements ICardUseRecordService {
    private CardUseRecordMapper cardUseRecordMapper;
    private CardMapper cardMapper;
    private CardTypeMapper cardTypeMapper;

    @Autowired
    public CardUseRecordService(CardUseRecordMapper cardUseRecordMapper, CardMapper cardMapper, CardTypeMapper cardTypeMapper) {
        this.cardUseRecordMapper = cardUseRecordMapper;
        this.cardMapper = cardMapper;
        this.cardTypeMapper = cardTypeMapper;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int add(CardUseRecord model) {
        model.setUsedate(new Date());
        Card card = cardMapper.selectById(model.getCid());
        if (card == null) return 0;
        if (card.getCstate() == 0) {
            card.setCstate(1);
            card.setStartdate(model.getUsedate());
            CardType cardType = cardTypeMapper.selectById(card.getCtypeid());
            Calendar cal = Calendar.getInstance();
            cal.setTime(card.getStartdate());
            cal.add(Calendar.DATE, cardType.getCardExpireDay());
            card.setEnddate(cal.getTime());
            cardMapper.updateById(card);
            return cardUseRecordMapper.insert(model);
        } else if (card.getCstate() == 1) {
            if (model.getUsedate().after(card.getEnddate())) {
                card.setCstate(2);
                cardMapper.updateById(card);
                return 0;
            } else {
                return cardUseRecordMapper.insert(model);
            }
        } else {
            return 0;
        }
    }

    @Override
    public List<CardUseRecord> getPageList(CardUseRecord model) {
        return cardUseRecordMapper.getPageList(model);
    }

    @Override
    public int delete(String[] ids) {
        return cardUseRecordMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<CardUseRecord> getPagelist(CardUseRecord model) {
        return cardUseRecordMapper.getPageList(model);
    }
}
