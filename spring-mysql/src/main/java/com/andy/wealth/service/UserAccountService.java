package com.andy.wealth.service;

import com.andy.wealth.dao.UserAccountMapper;
import com.andy.wealth.entity.UserAccount;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserAccountService implements UserAccountInterface{

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Transactional
    public void transfer(Long from, Long to, Long amount) {
        this.doTransfer(from, to, amount);
    }

    @Transactional
    public void doTransfer(Long from, Long to, Long amount) {
        UserAccount fromUser = getUserAccount(from);
        UserAccount toUser = getUserAccount(to);

        fromUser.setAmount(fromUser.getAmount() - amount);
        userAccountMapper.updateByPrimaryKey(fromUser);

        toUser.setAmount(toUser.getAmount() + amount);
        userAccountMapper.updateByPrimaryKey(toUser);

        throw new RuntimeException("Manually Error");

    }

    private UserAccount getUserAccount(Long userId) {
        Example fromExample = new Example(UserAccount.class);
        Example.Criteria criteria = fromExample.createCriteria();
        criteria.andEqualTo("userId", userId);
        UserAccount user = userAccountMapper.selectOneByExample(fromExample);
        return user;
    }
}
