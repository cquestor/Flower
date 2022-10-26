package com.halfsay.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsay.flower.mapper.CardTypeMapper;
import com.halfsay.flower.pojo.CardType;
import com.halfsay.flower.service.ICardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardTypeService implements ICardTypeService {
    private CardTypeMapper cardTypeMapper;

    @Autowired
    public CardTypeService(CardTypeMapper cardTypeMapper) {
        this.cardTypeMapper = cardTypeMapper;
    }

    @Override
    public List<CardType> getList() {
        return cardTypeMapper.selectList(null);
    }

    @Override
    public CardType ckCardName(String cardName) {
        QueryWrapper<CardType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_name", cardName);
        return cardTypeMapper.selectOne(queryWrapper);
    }

    @Override
    public int add(CardType cardType) {
        return cardTypeMapper.insert(cardType);
    }

    @Override
    public int update(CardType cardType) {
        return cardTypeMapper.updateById(cardType);
    }

    @Override
    public int delete(Integer id) {
        return cardTypeMapper.deleteById(id);
    }

    @Override
    public CardType findById(Integer id) {
        return cardTypeMapper.selectById(id);
    }
}
