public class Main {
    private static final String URL = "redis://127.0.0.1:6379";
    private static int countUsers = 20;
    private static int rangeOfUsers = 10;

    public static void main(String[] args)  {
        RedisStorage redisStorage = RedisStorage.init(URL);
        redisStorage.addNPerson(countUsers);

        while (true) {
            try {
                redisStorage.startingEmulation(rangeOfUsers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
