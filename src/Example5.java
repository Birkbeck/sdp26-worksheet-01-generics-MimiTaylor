public class Example5 {
    public static void main(String... args) {
        var account = new SavingsAccount(2024, 3);

        var storage1 = new Storage<SavingsAccount>();
        store(storage1, account);
    }

    public static void store(Storage<SavingsAccount> s, SavingsAccount acc) {
        System.out.println("Storing " + acc.getAccountNumber());
        s.setItem(acc); // s is a "consumer" of the SavingsAccount
    }
}
