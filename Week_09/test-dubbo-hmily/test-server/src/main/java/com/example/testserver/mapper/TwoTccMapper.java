package com.example.testserver.mapper;

import com.example.testapi.domain.AccountDTO;
import org.apache.ibatis.annotations.Update;

/**
 *
 */
public interface TwoTccMapper {
    @Update("update `account` set usd_balance = usd_balance - #{usdAmount} ,cny_balance =cny_balance + #{cnyAmount}," +
            " usd_freeze_amount= usd_freeze_amount + #{usdAmount} ,update_time = now()" +
            " where user_id =#{userId}  and  usd_balance > 0  ")
    int update(AccountDTO accountDTO);

    @Update("update `account` set " +
            " usd_freeze_amount= usd_freeze_amount - #{usdAmount}" +
            " where user_id =#{userId}  and usd_freeze_amount >0 ")
    int confirm(AccountDTO accountDTO);

    @Update("update `account` set usd_balance = usd_balance + #{usdAmount} ,cny_balance =cny_balance - #{cnyAmount}," +
            " usd_freeze_amount= usd_freeze_amount -  #{usdAmount} " +
            " where user_id =#{userId}  and usd_freeze_amount >0")
    int cancel(AccountDTO accountDTO);
}
