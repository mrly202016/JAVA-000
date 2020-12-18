package com.example.testserver.service.impl;

import com.example.testapi.domain.AccountDTO;
import com.example.testserver.mapper.TwoTccMapper;
import com.example.testserver.service.TwoTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
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
