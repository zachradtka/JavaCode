package com.zachradtka;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * Hello world!
 *
 */
public class App {
   public static void main(String[] args) throws TwitterException {

      TwitterStream twitterStream = new TwitterStreamFactory().getInstance();


      StatusListener listener = new StatusListener() {


         public void onStatus(Status status) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
         }

         public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
         }

         public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:"
                  + upToStatusId);
         }

         public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning:" + warning);
         }

         public void onException(Exception ex) {
            ex.printStackTrace();
         }

         @Override
         public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            // TODO Auto-generated method stub
         }
      };

      
      
      FilterQuery fq = new FilterQuery();
      String[] lang = {"en"};
      String[] track = {"iphone"};
      fq.language(lang);
      fq.track(track);
      // filter() method internally creates a thread which manipulates TwitterStream and calls these
      // adequate listener methods continuously.

      twitterStream.addListener(listener);
      twitterStream.filter(fq);
   }
}
