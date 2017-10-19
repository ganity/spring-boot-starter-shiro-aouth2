# spring-boot-starter-swagger2
A spring boot starter use shiro with oltu as aouth2 client
## How to build and install

1. git clone the code from github

2. run the command in your console with maven:

```
	mvn install
```

## How to use 

- import the config in you `pom.xml`

```xml
<dependency>
    <groupId>com.github.ganity</groupId>
    <artifactId>spring-boot-starter-shiro-aouth2</artifactId>
    <version>1.0.0</version>
</dependency>
```

- add the follow config in you `application.yml` or `application.properties` 

```shell
oauth2:
  client_id: clientauthcode
  client-secret: 123456
  redirect-uri: http://localhost:8080/redirect
  authz-endpoint: http://localhost:8510/oauth/authorize
  token-endpoint: http://localhost:8510/oauth/token
  resource-url: http://localhost:8516/api/user
  scope: read write
  success-url: /index
  authz-callback-class: com.github.ganity.oauth2.shiro.ShiroAuthzCallback
  error-redirect-uri: /error

shiro:
  realm-class: com.citic.UserRealm
  custom-authc-filter-class: com.citic.AjaxAuthorizationFilter
  login-url: /authorize
  success-url: /index
  unauthorized-url: /unauthorized
  sign-in:
    user-param: username
    password-param: password
    remember-me-param: rememberMe
  hash-iterations: 1024
  hash-algorithm-name: SHA-256
  stored-credentials-hex-encoded: false
  filter-chain-definitions:
    /js/**: anon
    /css/**: anon
    /fonts/**: anon
    /img/**: anon
    /logout: logout
    /favicon.ico: anon
    /index: authc
    /anon: anon
```
- make your UserRealm extends `OAuth2AuthorizingRealm`

in the `doGetAuthorizationInfo` method get your Permission info

```java
public class UserRealm extends OAuth2AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userId = (String) super.getAvailablePrincipal(principals);
        // 获得用户角色
        List<String> userRoles = new ArrayList<String>();
        userRoles.add("USER");
        userRoles.add("role_2");

        // 获取权限
        info.addStringPermission("abcefffdfdf_a_1");
        info.addStringPermission("abcefffdfdf_a_2");
        info.addStringPermission("abcefffdfdf_a_3");
        info.addStringPermission("abcefffdfdf_a_4");
        info.addRoles(userRoles);
        return info;
    }
}
```

- the simple [my-shiro-oltu-oauth2-starter-simple](https://github.com/ganity/my-shiro-oltu-oauth2-starter-simple.git)



