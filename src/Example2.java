public class Example2 {
    public static void main(String[] args) {
        //BankAccount[] array = new BankAccount[2];
        //array[0] = new BankAccount(2024);

        String[] array = new String[2];
        array[0] = "hi there";

        test(array);

    }

    public static void test(Object[] a) {
        System.out.println("in test - before storing into a");
        a[1] = new BankAccount(2025);
        System.out.println("in test - after storing into a");
    }
}
