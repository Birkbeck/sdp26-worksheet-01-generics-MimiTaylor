public class Example3 {
    public static void main(String[] args) {
        Storage<BankAccount> storage = new Storage<>();
        storage.setItem(new BankAccount(2024));
        test(storage);
    }

    public static void test(Storage<BankAccount> s) {
        System.out.println("in test - before storing into s");
        s.setItem(new BankAccount(2025));
        System.out.println("in test - after storing into s");
    }
}
