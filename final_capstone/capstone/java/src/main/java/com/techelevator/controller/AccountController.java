package com.techelevator.controller;


import com.techelevator.dao.AccountDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping ("/tenmo/")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping (path = "balance", method = RequestMethod.GET)
    public BigDecimal getBalance (Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(userId);
    }


    @RequestMapping(path = "users", method = RequestMethod.GET)
    public List<User> getAllUsers (Principal principal) {
        return userDao.findAll(principal.getName());
    }

}



