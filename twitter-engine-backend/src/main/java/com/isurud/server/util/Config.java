package com.isurud.server.util;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class Config {

    @Value("${twitter.consumer.key}")
    private String consumerKey;


    @Value("${twitter.consumer.secret}")
    private String consumerSecret;


    @Value("${twitter.access.token}")
    private String accessToken;


    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public Twitter getTwitter() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        return twitterFactory.getInstance();
    }

}
