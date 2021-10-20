public class Account {

    private long money;
    private String accNumber;
    private boolean blockedAccount;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.blockedAccount = false;
    }

    public long getMoney() {
        return money;
    }


    public void withdrawMoney(long money) {
        if (!blockedAccount) {
            if (this.money >= money) {
                this.money -= money;
            } else {
                System.out.println("Недостаточно средств");
            }
        } else {
            System.out.println("Аккаунт " + getAccNumber() + "  заблокирован.");
        }
    }

    public void putMoney(long money) {
        if (!blockedAccount) {
            this.money += money;
        } else {
            System.out.println("Аккаунт " + getAccNumber() + "   заблокирован.");
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlockedAccount() {
        return blockedAccount;
    }

    public void blockAccount() {
        this.blockedAccount = true;
    }
}
