# Worksheet One

## Java Generics

+ This worksheet examines your existing knowledge on Java Generics to reinforce 
the concepts from your previous studies.
+ It is essential that you commit regularly any changes to your source code 
(to the respective `GitHub Classroom` repository).
+ Where the questions make incremental changes to the code you **do not** need to keep 
separate versions of your code, as your commits will deal with that situation. 
+ Text-based questions should be answered inline by modifying this document.

You are provided with some source files under `src/`.

## Learning Goals

When you have completed this worksheet you should have achieved the following learning goals:

+ To understand the basic benefits of using generics.
+ To understand the difference between type invariance and covariance.
+ To understand when to use upper-bounded and lower-bounded wildcards.

You should be able to finish most of the exercises during the lab session given your prior knowledge of the topic.

## The Exercises

1. Add the following code snippet to the `Example1` class `main` method, 
creating two different storage objects with two different type specialisations:
  
   ```.java
   Storage<BankAccount> bankAccountStorage = new Storage<BankAccount>();
   Storage<String> stringStorage = new Storage<String>();
   ```
   + What are the reasons for using generics here?
   
     ** to ensure type safety **
   
   + What are the benefits?
   
     ** Easier to catch errors at compile time rather than at runtime e.g ClassCastException **

Add the following code to your `Example1` class:

   ```.java
   Object account = new BankAccount(2025);
   bankAccountStorage.setItem(account);

   Object account1 = bankAccountStorage.getItem();
   account1.deposit(15);
   ```
   + Compile and analyse the compiler output.
   
   + What is the cause of the problems reported by the compiler, if any?
   
     ** 
   + java: incompatible types: java.lang.Object cannot be converted to BankAccount.
   + account is declared as type object even though it holds a BankAccount instance.
   + bankAccountStorage is type Storage<BankAccount> so its setItem method only accepts BankAccount objects.
   + java generics checks declared type not runtime so an Object cannot be passed where java expects a BankAccount.
   + this enforces compile time type safety by preventing incompatible type assignments.

   + java: cannot find symbol / Cannot resolve method 'deposit' in 'Object'.
   + account1 is declared as type Object so the compiler can only see the methods defined in Object. 
   + The object class does not have a 'deposit' method so calling account1.deposit results in a compile time error.
   + This prevents unsafe type assignment and invalid method calls at compile time.
   + This shows how using general type such as Object prevents being able to use type specific methods.
   + Using generics preserves type specific information and its methods. 
   + **

Now replace:

   ```
   Object account = new BankAccount(2025);
   ```
   with
   ```
   BankAccount account = new BankAccount(2025);
   ```
   + How does this affect the compilation process?
   
     ** it has fixed the java: incompatible types: java.lang.Object error because account is now type BankAccount  **
   
   + What is the problem, if any?
   
     ** java: cannot find symbol / Cannot resolve method 'deposit' in 'Object' not resolved because account1 is still type Object **
   
Now replace 

   ```.java
   Storage<BankAccount> bankAccountStorage = new Storage<BankAccount>();
   Storage<String> stringStorage = new Storage<String>();
   ```
with
   ```.java
   Storage<BankAccount> bankAccountStorage = new Storage<>();
   Storage<String> stringStorage = new Storage<>();
   ```

   + Does it still compile?

     ** no. still having issues with trying to use the 'deposit' method. compiler error: java: cannot find symbol
     symbol:   method deposit(int)
     location: variable account1 of type java.lang.Object **

Finally, replace

   ```.java
   Storage<BankAccount> bankAccountStorage = new Storage<>();
   Storage<String> stringStorage = new Storage<>();
   ```
with
   ```.java
   var bankAccountStorage = new Storage<>();
   var stringStorage = new Storage<>();
   ```

   + Does it still compile? If not, explain why.

     ** no. stil getting java: cannot find symbol
     symbol:   method deposit(int)
     location: variable account1 of type java.lang.Object error. also there is an issue with:
     // Java added var in Java 10 2018 as local variable type inference.
     // It allows the compiler to figure out the type from the right hand side
     //but because there is no type inside <> java looks at the context later where:
     // 'account' is type Object so java infers --> Storage<Object>


var bankAccountStorage = new Storage<>();
var stringStorage = new Storage<>();
Object account = new BankAccount(2025); // declared type is Object.
bankAccountStorage.setItem(account); // but bankAccountStorage is type <BankAccount>

   **

   + How would you fix the error?

     **  Declare bankAccountStorage as Storage<BankAccount> Storage<BankAccount> bankAccountStorage = new Storage<BankAccount>();
     and declare account1 as type BankAccount **


2. In Java, arrays are **covariant**, which means that an array of type `T1[]` can be used in a context where 
an array of type `T2[]` is expected (in other words, `T1[]` is a subtype of `T2[]`) whenever `T1` is a subtype of `T2`. Consider class `Example2`: 
its method `test` is declared to accept an array of any objects, in particular, an array of `BankAccount` 
(as `BankAccount` is a subtype of `Object`). 
Inside method `test`, the parameter array `a` has type `Object[]` and so, we can store a new `BankAccount` in 
the parameter array. Compile and run the code to ensure it can be executed successfully.

Replace now

   ```.java
   BankAccount[] array = new BankAccount[2];
   array[0] = new BankAccount(2024);
   ```
with
   ```.java
   String[] array = new String[2];
   array[0] = "hi there";
   ```

+ Compile and run the code again. Does it still compile? Does it run? What is the output?

  ** Yes it compiled but encountered a runtime error: 
+ "Example2
  in test - before storing into a
+Exception in thread "main" java.lang.ArrayStoreException: BankAccount
  at Example2.test(Example2.java:15)
  at Example2.main(Example2.java:9)"
 
+ It was able to compile  because arrays are covariant --> java allowed an array of String to be passed to a method that expects array of Object.
Runtime error happened because at runtime, in the test method we tried to do this:  "a[1] = new BankAccount(2025)"
+ But at runtime, JVM checks and finds that "a" is actually a String[] which cannot hold a the BankAccount object "new BankAccount(2025)" 
**

3. Generics in Java, in contrast, are **invariant**, which means that the type parameters need to match exactly:
`C<T1>` is **not** a subtype of `C<T2>` when `T1` is a subtype of `T2` (unless, of course, `T1` is the same as `T2`).
Consider class `Example3`, which is very similar to `Example2` except that it uses the `Storage` generic class
instead of an array. Compile and run the code to ensure it can be executed successfully.

Replace now

   ```.java
   Storage<BankAccount> storage = new Storage<>();
   storage.setItem(new BankAccount(2024));
   ```
with
   ```.java
   Storage<String> storage = new Storage<>();
   storage.setItem("Happy 2025!");
   ```

+ Compile the code again. Does it still compile? Why not?

  ** No it does not compile. compiler error: java: incompatible types: Storage<java.lang.String> cannot be converted to Storage<BankAccount>.
+  The test() method expects a Storage<BankAccount>, but we are passing a Storage<String>. 
+ Because Java generics are invariant --> Storage<String> is not a subtype of Storage<BankAccount>.
+ So the call is rejected at compile time to preserve type safety. 
+ **

4. Study the code in class `Example4`.

+ What is the type of variable `storage1`?

  ** YOUR ANSWER HERE **

+ Compile and run the code to make sure it can be executed successfully. What is the printed output?

  ** YOUR ANSWER HERE **

Add the following 3 lines to method `main`:

   ```.java
   var storage2 = new Storage<SavingsAccount>();
   storage2.setItem(new SavingsAccount(2025, 2));
   process(storage2);
   ```

+ What is the type of variable `storage2`?

  ** YOUR ANSWER HERE **

+ Compile the code. Does it compile? If not, what is the problem?

  ** YOUR ANSWER HERE **

+ Fix the compile-time error, without changing the contents of method `main` and without any code duplication. 
In other words, assume that users of your 
method `process` may want to invoke it with `Storage<BankAccount>`, `Storage<SavingsAccount>` or indeed with
any `Storage<T>` for a subtype `T` of `BankAccount`.

5. Study the code in class `Example5`.

+ What is the type of variable `storage1`? 

  ** YOUR ANSWER HERE **

+ Compile and run the code to make sure it can be executed successfully. What is the printed output?

  ** YOUR ANSWER HERE **

Add the following 4 lines to method `main`:

   ```.java
   var storage2 = new Storage<BankAccount>();
   store(storage2, account);

   var storage3 = new Storage<Object>();
   store(storage3, account);
   ```

+ What is the type of variable `storage2`? What is the type of variable `storage3`?

  ** YOUR ANSWER HERE **

+ Compile the code. Does it compile? If not, what is the problem?

  ** YOUR ANSWER HERE **

+ Fix the compile-time error, without changing the contents of method `main` and without any code duplication.
  In other words, assume that users of your
  method `store` may want to invoke it with `Storage<SavingsAccount>` or with
  any `Storage<T>` for a supertype `T` of `SavingsAccount` (in particular, with `Storage<BankAccount>` and `Storage<Object>`).

The last two exercises illustrate the PECS abbreviation: **P**roducer **e**xtends, **C**onsumer **s**uper.

------

