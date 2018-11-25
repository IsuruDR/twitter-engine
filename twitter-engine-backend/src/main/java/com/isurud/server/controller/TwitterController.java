package com.isurud.server.controller;

import com.google.gson.Gson;
import com.isurud.server.model.SearchResponse;
import com.isurud.server.service.TwitterService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class TwitterController {

    @Autowired
    private Logger logger;

    @Value("${item.count}")
    private int itemCount;

    @Value("${items.per.page}")
    private int itemsPerPage;

    @Value("${hashtag.count}")
    private int hashTagCount;

    @Autowired
    private Gson gson;

    @Autowired
    private TwitterService twitterService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public SearchResponse getTweets(@RequestParam("query") String queryString) {
        logger.info("Searching twitter with search term {}", queryString);

        SearchResponse tweets = twitterService.getTweets(queryString);

        logger.info("Returning search results");

        return tweets;
    }

}
