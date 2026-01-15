public class Example5 {
    public static void main(String... args) {
        var account = new SavingsAccount(2024, 3);

        var storage1 = new Storage<SavingsAccount>();
        store(storage1, account);

        var storage2 = new Storage<BankAccount>();
        store(storage2, account);

        var storage3 = new Storage<Object>();
        store(storage3, account);
    }

// We cannot use Storage<T extends BankAccount> because Object is not a subtype of SavingsAccount or BankAccount.
// store() "consumes" a SavingsAccount, so use a wildcard "? super"
// (PECS tells us which wildcard to use. In this case: Consumer → super)
    /* Object is a supertype of BankAccount, BankAccount is a supertype of SavingsAccount
    But because generics are invariant, the following are all unrelated types:
    Storage<SavingsAccount>
    Storage<BankAccount>
    Storage<Object>
    none of them is a subtype of another*/

        public static void store(Storage<? super SavingsAccount> s, SavingsAccount acc) {
        System.out.println("Storing " + acc.getAccountNumber());
        s.setItem(acc); // s is a "consumer" of the SavingsAccount
    }
}
