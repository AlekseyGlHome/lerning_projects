import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTest {
    private Bank superbank = new Bank();
    private final int NUMBER_OF_ACCOUNTS = 10;
    private final int CORES_AMOUNT = 10;
    private final int NUMBER_OF_OPERATIONS_FOR_THREAD = 5_000;
    private final int MONEY_IN_ACCOUNT = 10_000_000;

    @Test
    public void addAccountTest() {
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            superbank.addAccounts(String.valueOf(i), new Account(MONEY_IN_ACCOUNT, String.valueOf(i)));
        }
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            superbank.addAccounts(String.valueOf(i), new Account(MONEY_IN_ACCOUNT, String.valueOf(i)));
        }
        System.out.println("\tРЕЗУЛЬТАТ\n" + NUMBER_OF_ACCOUNTS + " - " + superbank.getAccounts().size());
        Assert.assertEquals(NUMBER_OF_ACCOUNTS, superbank.getAccounts().size());
    }


    @Test
    public void transferMultithreadedTest() throws InterruptedException {

        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            superbank.addAccounts(String.valueOf(i), new Account(MONEY_IN_ACCOUNT, String.valueOf(i)));
        }

        long expectedResult = superbank.getSumAllAccounts();
        long result;

        ExecutorService executor = Executors.newFixedThreadPool(CORES_AMOUNT);
        for (int i = 0; i < CORES_AMOUNT; i++) {
            executor.submit(() -> {

                for (int x = 0; x < NUMBER_OF_OPERATIONS_FOR_THREAD; x++) {
                    String fromAccNum;
                    String toAccNum;
                    do {
                        fromAccNum = String.valueOf((int) (Math.random() * (NUMBER_OF_ACCOUNTS)));
                        toAccNum = String.valueOf((int) (Math.random() * (NUMBER_OF_ACCOUNTS)));
                    } while (fromAccNum.contains(toAccNum));
                    try {
                        superbank.transfer(fromAccNum, toAccNum, Math.round(50050 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        executor.shutdown();
        boolean done = executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("Все ли потоки завершены? " + done);

        result = superbank.getSumAllAccounts();
        System.out.println("\tРЕЗУЛЬТАТ\n" + expectedResult + " - " + result);

        Assert.assertEquals(expectedResult, result);

    }

  /*  @Test
    void getBalance() {
    }*/
}
