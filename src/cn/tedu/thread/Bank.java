package cn.tedu.thread;

public class Bank {
    public boolean getMoney(int money) {
        //1.检查余额
        int account = getAccount();
        //2.判断余额
        if (account >= money) {
            //重新记账
            account = account - money;
            saveAccount(account);
            return true;//允许出钞
        }
        return false;//不允许出钞
    }

    private void saveAccount(int account) {
    }

    private int getAccount() {
        return 200;
    }
}
