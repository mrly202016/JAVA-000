package com.example.testdubbo.api;

import com.example.testdubbo.domain.AccountDTO;

/**
 *
 */
public interface OneTccService {
    int update(AccountDTO accountDTO);
    int confirm(AccountDTO accountDTO);
    int cancel(AccountDTO accountDTO);
}
