package com.halfsay.flower.service.impl;

import com.halfsay.flower.mapper.CardMapper;
import com.halfsay.flower.pojo.Card;
import com.halfsay.flower.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CardService implements ICardService {
    private CardMapper cardMapper;

    @Autowired
    public CardService(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    @Override
    public int delete(String[] ids) {
        return cardMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int add(Card card) {
        card.setCreateddate(new Date());
        return cardMapper.insert(card);
    }

    @Override
    public int update(Card card) {
        return cardMapper.updateById(card);
    }

    @Override
    public Card findById(Long id) {
        return cardMapper.selectById(id);
    }

    @Override
    public List<Card> getPageList(Card model) {
        return cardMapper.getPageList(model);
    }
}
