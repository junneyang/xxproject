package com.xcompany.xproject.auth.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {  
	
	
    @Override  
    public OAuth2AccessToken enhance(  
     OAuth2AccessToken accessToken,   
     OAuth2Authentication authentication) {  
    	
    	//randomAlphabetic(4)
        Map<String, Object> additionalInfo = new HashMap<>();  
        additionalInfo.put("organization", authentication.getName() + String.valueOf(new Random().nextInt()));  
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);  
        return accessToken;  
    }  

}
