package com.isurud.server.service;

import com.google.gson.Gson;
import com.isurud.exception.ApiException;
import com.isurud.server.model.HashTag;
import com.isurud.server.model.SearchResponse;
import com.isurud.server.model.StatusEntry;
import com.isurud.server.model.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    @Autowired
    private Logger logger;

    @Autowired
    private Twitter twitter;

    @Value("${item.count}")
    private int itemCount;

    @Value("${items.per.page}")
    private int itemsPerPage;

    @Value("${hashtag.count}")
    private int hashTagCount;

    @Autowired
    private Gson gson;

    public SearchResponse getTweets(String queryString) {
        SearchResponse searchResponse = new SearchResponse();
        List<String> hashCodes = new ArrayList<>();
        List<StatusEntry> statusEntries = new ArrayList<>();

        Query query = new Query(queryString).resultType(Query.ResultType.popular).count(itemsPerPage);

        try {
            logger.info("Getting all tweets");

            List<Status> tweets = getAllTweets(query);

            logger.info("Processing received tweets");

            processTweets(tweets, hashCodes, statusEntries);

            searchResponse.setHashTags(getFilteredHashTags(hashCodes));
            searchResponse.setStatusEntries(statusEntries);

        } catch (TwitterException e) {
            logger.error("Error occurred while fetching data from Twitter");
            throw new ApiException("Error occurred while fetching data from Twitter", e);
        }
        logger.debug("Search response created [{}]", gson.toJson(searchResponse));
        return searchResponse;
    }

    private void processTweets(List<Status> tweets, List<String> hashCodes, List<StatusEntry> statusEntries) {
        for (Status status : tweets) {
            prepareStatuses(statusEntries, status);
            prepareHashTags(hashCodes, status);
        }
    }

    private void prepareHashTags(List<String> hashCodes, Status status) {
        HashtagEntity[] hashTagEntities = status.getHashtagEntities();

        for (int i = 0; i < hashTagEntities.length; i++) {

            String text = hashTagEntities[i].getText();
            hashCodes.add(text);
        }
    }

    private void prepareStatuses(List<StatusEntry> statusEntries, Status status) {
        User user = new User();
        user.setName(status.getUser().getName());
        user.setDescription(status.getUser().getDescription());
        user.setProfilePictureUrl(status.getUser().getProfileImageURL());
        user.setScreenName(status.getUser().getScreenName());

        statusEntries.add(new StatusEntry(user, status.getText(), status.getCreatedAt()));
    }

    private List<Status> getAllTweets(Query query) throws TwitterException {
        logger.debug("Getting first batch of tweets");

        QueryResult queryResult = twitter.search(query);
        List<Status> tweets = queryResult.getTweets();
        int tweetCount = tweets.size();

        logger.debug("Received first batch of tweets [{}]. Fetching next available tweets", gson.toJson(tweets));

        while (queryResult.hasNext() && tweetCount < itemCount) {
            Query nextQuery = queryResult.nextQuery();
            QueryResult nextQueryResult = twitter.search(nextQuery);
            List<Status> nextTweets = nextQueryResult.getTweets();

            logger.debug("Received next batch of tweets [{}]", gson.toJson(nextTweets));

            tweets.addAll(nextTweets);
            queryResult = nextQueryResult;
            tweetCount += nextTweets.size();
        }
        logger.debug("All tweets received [{}]", tweets);

        return tweets;
    }

    private List<HashTag> getFilteredHashTags(List<String> hashCodes) {
        List<HashTag> hashTags = new ArrayList<>();
        Map<String, Long> hashTagMap = hashCodes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        hashTagMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).limit(hashTagCount).forEachOrdered(e -> hashTags.add(new HashTag(e.getKey(), e.getValue())));
        return hashTags;
    }
}
