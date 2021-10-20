import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.Date;

public class RedisStorage {
    private final String KEY = "ONLINE_USERS";
    private RKeys rKeys;
    private RScoredSortedSet<String> onlineUsers;
    private static final int SLEEP = 1000;
    private static final int SHOW_TIME = 2;


    private RedisStorage(RedissonClient redisson) {
        onlineUsers = redisson.getScoredSortedSet(KEY, new StringCodec("UTF-8"));
        rKeys = redisson.getKeys();
        rKeys.delete(KEY);
    }

    public static RedisStorage init(String configAddress) {
        Config config = new Config();
        config.useSingleServer().setAddress(configAddress);
        return (new RedisStorage(Redisson.create(config)));
    }

    public void addNPerson(int n) {
        for (int i = 1; i <= n; i++) {
            onlineUsers.add(getTs(), String.valueOf(i));
        }
    }
    public void PurchasePriority(String id) {
        System.out.println("> Пользователь " + id + " оплатил платную услугу");
        onlineUsers.add(onlineUsers.firstScore() - 1, id);
    }


    public void showUser(String id) throws InterruptedException {
        System.out.println("-- На главной странице показываем пользователя " + id);
        onlineUsers.add(getTs(), id);
        Thread.sleep(SHOW_TIME);
    }

    private double getTs() {
        return new Date().getTime();
    }

    public int randomInt(int min, int max) {
        return ((int) ((Math.random() * (max - min) + min + 1)));
    }

    public void startingEmulation(int rangeOfUsers) throws InterruptedException {
        int countBuyingUser = onlineUsers.size() / rangeOfUsers;
        int limitStart = rangeOfUsers;
        int limitEnd = 0;
        int[] paidUsers = new int[countBuyingUser];
        int[] usersOutputQueue = new int[countBuyingUser];
        for (int i = 0; i < countBuyingUser; i++) {
            int paidUser = randomInt(limitEnd,limitStart);
            int userOutputQueue = randomInt(limitEnd, limitStart);
            while (paidUser <= userOutputQueue) {
                paidUser = randomInt(limitEnd, limitStart);
                userOutputQueue = randomInt(limitEnd, limitStart);
            }
            paidUsers[i] = paidUser;
            usersOutputQueue[i] = userOutputQueue;
            limitEnd = limitStart;
            limitStart = limitStart  + limitStart * (i+1);

        }

        for (int j = 1; j <= onlineUsers.size(); j++) {
            for (int i = 0; i < paidUsers.length; i++) {
                if (j == usersOutputQueue[i]) {
                    PurchasePriority(String.valueOf(paidUsers[i]));
                }
            }
            showUser(onlineUsers.first());
        }
        for (int paidUser : paidUsers) {
            onlineUsers.add(onlineUsers.getScore(String.valueOf(paidUser - 1)) + 1, String.valueOf(paidUser));
        }
        Thread.sleep(SLEEP);
    }
}

