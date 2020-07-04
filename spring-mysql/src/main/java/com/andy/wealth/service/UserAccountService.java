package com.andy.wealth.service;

import com.andy.wealth.dao.UserAccountMapper;
import com.andy.wealth.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserAccountService implements UserAccountInterface{

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Transactional
    public void transfer(Long from, Long to, Long amount) {
        UserAccount fromUser = getUserAccount(from);
        UserAccount toUser = getUserAccount(to);

        fromUser.setAmount(fromUser.getAmount() - amount);
        userAccountMapper.updateByPrimaryKey(fromUser);

        if (1 == 1)
            throw new RuntimeException("Manuall error");

        toUser.setAmount(toUser.getAmount() + amount);
        userAccountMapper.updateByPrimaryKey(toUser);
    }

    private UserAccount getUserAccount(Long userId) {
        Example fromExample = new Example(UserAccount.class);
        Example.Criteria criteria = fromExample.createCriteria();
        criteria.andEqualTo("userId", userId);
        UserAccount user = userAccountMapper.selectOneByExample(fromExample);
        return user;
    }
}
