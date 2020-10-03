package hashTable;

import java.util.*;

public class Twitter {

    //存放每个twitter的发送时间
    Map<Integer,Set<Integer>> person2twitter;
    Map<Integer,Set<Integer>> followList;
    List<Integer> pushTimeList;

    /** Initialize your data structure here. */
    public Twitter() {
        person2twitter = new HashMap<>();
        pushTimeList = new ArrayList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(person2twitter.containsKey(userId)){
            person2twitter.get(userId).add(tweetId);
        }else {
            Set<Integer> newTwitter = new HashSet<>();
            newTwitter.add(tweetId);
            person2twitter.put(userId,newTwitter);
        }
        pushTimeList.add(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = person2twitter.get(userId);
        for(Integer integer:pushTimeList){
            if(set.contains(integer)){
                result.add(integer);
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followerSet = person2twitter.getOrDefault(followerId,new HashSet<>());
        Set<Integer> followeeSet = person2twitter.getOrDefault(followeeId,new HashSet<>());
        followerSet.addAll(followeeSet);
        person2twitter.put(followeeId,followerSet);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followerSet = person2twitter.getOrDefault(followerId,new HashSet<>());
        Set<Integer> followeeSet = person2twitter.getOrDefault(followeeId,new HashSet<>());
        followerSet.removeAll(followeeSet);
        person2twitter.put(followeeId,followerSet);
    }

    public static void main(String[] args) {

    }
}
