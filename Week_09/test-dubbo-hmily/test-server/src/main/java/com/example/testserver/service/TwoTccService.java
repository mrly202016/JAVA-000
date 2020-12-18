package com.example.testserver.service;

import com.example.testapi.domain.AccountDTO;

/**
 *
 */
public interface TwoTccService {
    int update(AccountDTO accountDTO);
    int confirm(AccountDTO accountDTO);
    int cancel(AccountDTO accountDTO);
}
