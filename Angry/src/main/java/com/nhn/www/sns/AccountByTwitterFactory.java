package com.nhn.www.sns;

import org.springframework.stereotype.Component;

import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import com.nhn.www.sns.SnsApiAccount.TwitterApiAccount;



@Component
public class AccountByTwitterFactory {
    TwitterFactory factory;
    ConfigurationBuilder builder = new ConfigurationBuilder();

    public AccountByTwitterFactory() {

    }
    public AccountByTwitterFactory(String language) {
        TwitterApiAccount api;
        try{
            api = TwitterApiAccount.valueOf(language.toUpperCase());
        }catch(Exception e){
            api = TwitterApiAccount.JP;
        }
        builder.setOAuthConsumerKey(api.getConsumerKey());
        builder.setOAuthConsumerSecret(api.getConsumerSecret());
        Configuration configuration = builder.build();
        this.factory = new TwitterFactory(configuration);
    }

    public TwitterFactory getFactory() {
        return factory;
    }
}
