package com.github.ganity.oauth2.shiro;

import com.github.ganity.oauth2.AuthzCallback;
import com.github.ganity.oauth2.OAuth2AccessToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Map;

public class ShiroAuthzCallback implements AuthzCallback {
    @Override
    public void callback(OAuth2AccessToken accessToken, Map<String, String> principal) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(principal.get(PRINCIPAL), principal.get(PRINCIPAL).toCharArray());
        token.setRememberMe(false);
        try {
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            subject.login(token);

            Session session = subject.getSession();
            session.setAttribute(AUTH_ACCESS_TOKEN, accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public OAuth2AccessToken logout() {

        Subject subject = SecurityUtils.getSubject();

        try {
            Session session = subject.getSession();
            OAuth2AccessToken accessToken = (OAuth2AccessToken)session.getAttribute(AUTH_ACCESS_TOKEN);
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
