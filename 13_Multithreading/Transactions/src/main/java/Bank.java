import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {


    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private int fraudLimit = 50000;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        boolean controlFlagTopAccount = Long.parseLong(fromAccountNum) > Long.parseLong(toAccountNum);
        Account topAccount = controlFlagTopAccount ? fromAccount : toAccount;
        Account lowAccount = controlFlagTopAccount ? toAccount : fromAccount;
        if (fromAccountNum.contains(toAccountNum)) {
            System.out.println("Ошибка перевода. Причина: Указан один и тот же лицевой счет");
        } else {
            synchronized (topAccount) {
                synchronized (lowAccount) {
                    transactionMoneyFromTo(fromAccount, toAccount, amount);
                }
            }
        }
    }

    private void transactionMoneyFromTo(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        if (!isBlockedAcc(fromAccount, toAccount)) {
            if (fromAccount.getMoney() < amount) {
                System.out.println("Ошибка перевода. Недостаточно средств.");
                return;
            }

            System.out.println("Перевод " + amount + " с " + fromAccount.getAccNumber() + " на " + toAccount.getAccNumber());
            fromAccount.withdrawMoney(amount);
            toAccount.putMoney(amount);
            if (amount > fraudLimit) {
                if (isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount)) {
                    System.out.println("\tБлокируем счета " + fromAccount.getAccNumber() + " и " + toAccount.getAccNumber());
                    fromAccount.blockAccount();
                    toAccount.blockAccount();
                }
            }

        }
    }

    private boolean isBlockedAcc(Account fromAccount, Account toAccount) {
        if (fromAccount.isBlockedAccount()) {
            System.out.println("Ошибка перевода. Причина: Блокировка текущего аккаунта " + fromAccount.getAccNumber());
            return true;
        } else if (toAccount.isBlockedAccount()) {
            System.out.println("Ошибка перевода. Причина: Блокировка аккаунта получателя " + toAccount.getAccNumber());
            return true;
        }
        return false;
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long balance = 0;
        for (int i = 0; i < accounts.size(); i++) {
            balance += accounts.get(String.valueOf(i)).getMoney();
        }
        return balance;
    }

    public void addAccounts(String personalAcc, Account account) {
        if (!accounts.containsKey(personalAcc)) {
            accounts.put(personalAcc, account);
        } else {
            System.out.println("Неверно введен личный счет. Такой уже существует в базе.");
        }
    }
}
