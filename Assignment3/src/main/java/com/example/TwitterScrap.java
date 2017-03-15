package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class TwitterScrap {
	
	/** The actual Twitter stream. It's set up to collect raw JSON data */
	private TwitterStream twitterStream;
	private String[] keywords;
	Properties prop = new Properties();
	FileOutputStream fos;
 
@Autowired
ApplicationContext app;


//@Resource(name="twitterEntityListBean")
//private List<TwitterEntity> en;
	public TwitterScrap() {
 
		//load a properties file
		//sets configuration builder object
		
 
	}
 
	public void startTwitter() {
		try {
			prop.load(new FileInputStream("twitter.properties"));
 
			ConfigurationBuilder cb = new ConfigurationBuilder();
			//Four info that twitter requires to let you access data
			cb.setOAuthConsumerKey(prop.getProperty("CONSUMER_KEY"));
			cb.setOAuthConsumerSecret(prop.getProperty("CONSUMER_SECRET"));
			cb.setOAuthAccessToken(prop.getProperty("ACCESS_TOKEN"));
			cb.setOAuthAccessTokenSecret(prop.getProperty("ACCESS_TOKEN_SECRET"));
			cb.setHttpProxyHost("172.22.218.218");
			cb.setHttpProxyPort(8085);
			cb.setJSONStoreEnabled(true);
			cb.setIncludeEntitiesEnabled(true);
 
			//Gives you entry to twitter data
			twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		//	twitterStream.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		try {
			File f  = new File("twitterstream.json");
			if(f.exists()==false){
				f.mkdirs();
			}
			fos = new FileOutputStream(new File("twitterstream.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		//Store the words you are interested in array
		String keywordString = prop.getProperty("TWITTER_KEYWORDS");
		
		keywords = keywordString.split(",");
		for (int i = 0; i < keywords.length; i++) {
			keywords[i] = keywords[i].trim();
		}
		//en.clear();
		// Set up the stream's listener (defined above),
		twitterStream.addListener(listener);
 
		System.out.println("Starting down Twitter sample stream...");
 
		// Set up a filter to pull out industry-relevant tweets
		FilterQuery query = new FilterQuery().track(keywords);
		twitterStream.filter(query);
 
	}

	public void stopTwitter() {
		 
		System.out.println("Shutting down Twitter sample stream...");
		twitterStream.shutdown();
 
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	StatusListener listener = new StatusListener() {
 
		// The onStatus method is executed every time a new tweet comes in matching the filter.
		public void onStatus(Status status) {
			// The EventBuilder is used to build an event using the headers and
			// the raw JSON of a tweet
			String newLine = "\r\n";
			TwitterEntity entity=(TwitterEntity)app.getBean("twitterEntityBean");
			List<TwitterEntity> en =(ArrayList<TwitterEntity>)app.getBean("twitterEntityListBean");
			entity.setUserName(status.getUser().getScreenName());
			entity.setStatus(status.getText());
			entity.setGeoLocation(status.getGeoLocation());
			entity.setTimestamp(status.getCreatedAt().getTime());
			System.out.println("Entity"+entity);
			en.add(entity);
			System.out.println("List"+en);
//			DAO d= (DAO)app.getBean("DAO");
//			d.save(entity);
			
//			System.out.println(status.getUser().getScreenName() + ": " + status.getText() 
//			+ status.getGeoLocation());
 
	//	System.out.println("timestamp : "+ String.valueOf(status.getCreatedAt().getTime()));
			try {
				fos.write(DataObjectFactory.getRawJSON(status).getBytes());
				fos.write(newLine.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
 
		}
 
		// This listener will ignore everything except for new tweets
		public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
		public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
		public void onScrubGeo(long userId, long upToStatusId) {}
		public void onException(Exception ex) {}
		public void onStallWarning(StallWarning warning) {}
	};
 
	public static void runTwitter() throws InterruptedException {
 
		
		TwitterScrap twitter = new TwitterScrap();
		twitter.startTwitter();
		Thread.sleep(5000);//Data gathered for 5 secs
		twitter.stopTwitter();
		//app.getBean("ListBean");
 
	}

}
