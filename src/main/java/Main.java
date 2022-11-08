import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final int COUNT_CLIENT = 100;
    private static final int MAX_SUM_FOR_TRANSFER = 100_000;

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Account> accounts = new HashMap<>();
        for(var i = 1; i <= COUNT_CLIENT; i++) {
            accounts.put(String.valueOf(i), new Account((long) (Math.random()*99_000 + 1_000), String.valueOf(i)));
        }

        Bank bank = new Bank(accounts);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        String from = String.valueOf((int) (Math.random()*COUNT_CLIENT));
                        String to = String.valueOf((int) (Math.random()*COUNT_CLIENT));
                        if (!from.equals(to)) {
                            bank.transfer(from, to, (int) (Math.random() * MAX_SUM_FOR_TRANSFER));
                        }
                    } catch (Exception e) {
                            //throw new RuntimeException(e);
                    }
                }
            }
        };

        List<Thread> taskList = new ArrayList<>();
        for (var i = 0; i <5; i++) {
            taskList.add(new Thread(runnable));
        }

        for (Thread thread : taskList) {
            thread.start();
            thread.join();
        }

        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
