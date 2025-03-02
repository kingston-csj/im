package pers.kinson.im.infrastructure.security;

import jforgame.commons.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.business.entity.User;

@Component
public class AuthenticAccountRepository {

    @Autowired
    AccountServiceClient accountServiceClient;

    public AuthenticAccount findByUserId(String userId) {
        User account = accountServiceClient.findById(NumberUtil.longValue(userId));
        if (account == null) {
            return null;
        }
        return new AuthenticAccount(account);
    }
}