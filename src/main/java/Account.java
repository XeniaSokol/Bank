public class Account {
    private volatile long money;
    private String account;
    private boolean isBlocked = false;

    public Account(long money, String account) {
        this.money = money;
        this.account = account;
    }

    /*
     *Проверка баланса счета
     * @return
     */
    public long getMoney(){
        if (!isBlocked) {
            return money;
        } else {
            System.out.println("Счет "+account + " заблокирован");
         return 0;
        }
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isBlocked(){
        return isBlocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + getMoney() +
                ", account='" + account +'}';
    }

    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }
}
