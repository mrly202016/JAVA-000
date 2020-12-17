package com.example.testdubbo.impl;

import com.example.testdubbo.api.OneTccService;
import com.example.testdubbo.dao.mapperone.OneTccMapper;
import com.example.testdubbo.domain.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * T
 *
 * @author sherk7monster
 */
@Service
public class OneTccServiceImpl implements OneTccService {
    @Autowired
    OneTccMapper oneTccMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(AccountDTO accountDTO) {
        return oneTccMapper.update(accountDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int confirm(AccountDTO accountDTO) {
        return oneTccMapper.confirm(accountDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancel(AccountDTO accountDTO) {
        return oneTccMapper.cancel(accountDTO);
    }
}
