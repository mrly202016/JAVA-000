package com.example.testdubbo.impl;

import com.example.testdubbo.api.TwoTccService;
import com.example.testdubbo.dao.mappertwo.TwoTccMapper;
import com.example.testdubbo.domain.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * T
 *
 * @author sherk7monster
 */
public class TwoTccServiceImpl implements TwoTccService {
    @Autowired
    TwoTccMapper twoTccMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(AccountDTO accountDTO) {
        return twoTccMapper.update(accountDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int confirm(AccountDTO accountDTO) {
        return twoTccMapper.confirm(accountDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancel(AccountDTO accountDTO) {
        return twoTccMapper.cancel(accountDTO);
    }
}
