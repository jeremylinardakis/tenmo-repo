package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Map;

public interface AccountDao {

    BigDecimal getBalance(int userId);

//    public User[] getAllAccounts();

    boolean sendFunds (BigDecimal bigDecimal, Long id);

    boolean recieveFunds (BigDecimal bigDecimal, Long id);


}
