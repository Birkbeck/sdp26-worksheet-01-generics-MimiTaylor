public class Example1 {

    public static void main(String... args) {

        // Snippet 1
        Storage<BankAccount> bankAccountStorage = new Storage<BankAccount>();
        Storage<String> stringStorage = new Storage<String>();

        // Snippet 2
        Object account = new BankAccount(2025); // declared type is Object.
        bankAccountStorage.setItem(account); // but bankAccountStorage is type <BankAccount>

        Object account1 = bankAccountStorage.getItem(); //account1 declared as type Object
        account1.deposit(15); // cannot resolve method 'deposit' in 'Object' means 'Object' does not have a method called 'deposit'
    }
}
