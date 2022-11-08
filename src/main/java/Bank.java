import java.util.HashMap;
import java.util.Random;

public class Bank {
    private HashMap<String, Account> accounts;  //ид клиента и счет клиента

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    private synchronized boolean isFraud(String fromAccountNumb, String toAccountNumb, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return new Random().nextBoolean();
    }

    public void transfer(String fromClient, String toClient, long amount) throws InterruptedException {
        Account from = accounts.get(fromClient);  //счет с которого отправляем
        Account to = accounts.get(toClient);  //счет на который перечисляем

        if (!from.isBlocked() && !to.isBlocked()) {
            if (amount <= 50_000) {
                simpleTransfer(from, to, amount);
            } else {
                if (isFraud(from.getAccount(), to.getAccount(), amount)) {
                    from.setBlocked(true);
                    to.setBlocked(true);

                    System.out.println("Перевод заблокирован со счета №" + from.getAccount()
                            + " на счет №" + to.getAccount()+ "!");
                } else {
                    simpleTransfer(from, to, amount);
                }
            }
        }
    }

    private void simpleTransfer(Account from, Account to, long amount) throws InterruptedException {
        if (from.getMoney() >= amount) {
            from.setMoney(from.getMoney()-amount);
            to.setMoney(to.getMoney()+amount);
            System.out.println(amount + " со счета №" + from.getAccount() + " успешно переведена на счет №" + to.getAccount() + ".");
        } else {
            System.out.println("Недостаточно средств для перевода");
        }
    }
}
