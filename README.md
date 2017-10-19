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

- run your Application


