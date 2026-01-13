public class Example1 {

    public static void main(String... args) {

        // Snippet 1
        Storage<BankAccount> bankAccountStorage = new Storage<BankAccount>();
        Storage<String> stringStorage = new Storage<String>();

        // Java added var in Java 10 2018 as local variable type inference.
        // It allows the compiler to figure out the type from the right hand side
        //but because there is no type inside <> java thinks it is Storage<Object>
        //var bankAccountStorage = new Storage<>();
        //var stringStorage = new Storage<>();

        // Snippet 2
        //Object account = new BankAccount(2025); // declared type is Object.
        //bankAccountStorage.setItem(account); // but bankAccountStorage is type <BankAccount>

        BankAccount account = new BankAccount(2025);
        bankAccountStorage.setItem(account);

        //Object account1 = bankAccountStorage.getItem(); //account1 declared as type Object
        BankAccount account1 = bankAccountStorage.getItem(); // fixed code by declaring account1 as type BankAccount. Now it can use method 'deposit
        account1.deposit(15); // cannot resolve method 'deposit' in 'Object' means 'Object' does not have a method called 'deposit'
    }
}
