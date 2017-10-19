package com.github.ganity.oauth2.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.ByteSource;

public abstract class OAuth2AuthorizingRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        // 根据用户名检索用户信息，此时不用判断密码是否相同
        String cp = new Sha256Hash(username, username, 1024).toBase64();
        // 登录,如果token中的密码经过加密后和检索出来的密码不符合，会登录失败
        AuthenticationInfo auth = new SimpleAuthenticationInfo(username, cp, ByteSource.Util.bytes(username), getName());
        return auth;
    }

}
