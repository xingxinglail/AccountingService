package com.github.accounting.config;

import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final String HASH_ALGORITHM_NAME = "SHA-256";

    private static final int HASH_ITERATIONS = 1000;

    private final ShiroLoginFilter shiroLoginFilter;

    @Autowired
    public ShiroConfig(ShiroLoginFilter shiroLoginFilter) {
        this.shiroLoginFilter = shiroLoginFilter;
    }

    @Bean
    public SecurityManager securityManager(Realm realm) {
        val securityManager = new DefaultWebSecurityManager(realm);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        val shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        val shiroFilterDefinitionMap = new LinkedHashMap<String, String>();
        shiroFilterDefinitionMap.put("/v1.0/session", "anon");
        shiroFilterDefinitionMap.put("/v1.0/users", "anon");
        shiroFilterDefinitionMap.put("/**", "authc");

        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("shiroLoginFilter", shiroLoginFilter);
        shiroFilterFactoryBean.setFilters(filtersMap);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher matcher() {
        val matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(HASH_ALGORITHM_NAME);
        matcher.setHashIterations(HASH_ITERATIONS);
        matcher.setHashSalted(true);
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;
    }
}
