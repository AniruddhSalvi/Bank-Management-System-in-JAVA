import java.util.Scanner;

class InvalidPinNumberException extends Exception {
    public InvalidPinNumberException(String message) {
        super(message);
    }
}
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankDetails {
    private String name, acctype;
    private double bal;
    private int pin;
    private int accno;
    Scanner sc = new Scanner(System.in);

    public void openAcc() {
        System.out.println("\nEnter the Account holder name: ");
        name = sc.nextLine();
        System.out.println("Enter the type of Account to create: ");
        acctype = sc.nextLine();
        accno = (int) (Math.random() * 1000000);
        System.out.println("Enter your desired PIN number: ");
        pin = sc.nextInt();
        System.out.println("----- Account is SUCCESSFULLY created..THANK YOU for Banking with us!! -----\n");
        System.out.println();
    }
    public void showAcc() {
        System.out.println("\n----- Here are your BANK DETAILS -----");
        System.out.println("\nAccount Holder Name : " + name);
        System.out.println("Type Of Account : " + acctype);
        System.out.println("Account number: " + accno);
        System.out.println("Available Balance: " + bal);
    }
    public void deposit() {
        double amt;
        System.out.println("Enter amount to Deposit");
        amt = sc.nextDouble();
        bal = bal + amt;
        System.out.println("Deposited Amount Successfully!\nCurrent Balance: "+bal);
    }
    public void withdrawal(double amt) throws InsufficientFundsException {
        if(bal >= amt) {
            bal -= amt;
            System.out.println("Withdrawal Successful!\nRemaining Balance: "+bal);
        } else {
            throw new InsufficientFundsException("Insufficient Funds!");
        }
        System.out.println("Available Balance : "+bal);
    }
    public boolean search(int acc_no, int PIN) throws InvalidPinNumberException {
        if(accno == acc_no && pin == PIN) {
            showAcc();
            return true;
        } else {
            throw new InvalidPinNumberException("Invalid Pin Number");
        }
    }
}
public class Banking {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many customers account to be created: ");
        int n=sc.nextInt();

        BankDetails c[]=new BankDetails[n];
        for (int i=0;i<c.length;i++){
            c[i]=new BankDetails();
            c[i].openAcc();
        }
        
        int a;
        do {
            System.out.println("\n\n\t\t*-*-*-*-* WELCOME TO HDFC WORLD *-*-*-*-*");
            System.out.println("\t\t1) Display the Account Details of all customers\n\t\t2) Search by Account Number\n\t\t3) Deposit Amount\n\t\t4) Withdraw Amount\n\t\t5) Exit");
            System.out.print("\t\tEnter choice:");
            a=sc.nextInt();
            System.out.println();
            switch(a) {
                case 1: for(int i=0;i<c.length;i++) {
                            c[i].showAcc();
                        }
                        break;
                case 2: System.out.println("Enter the account number: ");
                        int acc_no = sc.nextInt();
                        System.out.println("Enter your PIN: ");
                        int PIN = sc.nextInt();
                        boolean found=false;

                        for(int i=0;i<c.length;i++) {
                            try {
                                found = c[i].search(acc_no, PIN);
                            }
                            catch (InvalidPinNumberException e) {
                                System.err.println("Error: "+e.getMessage());
                            }
                            if(found==true) {
                                break;
                            }
                        }
                        break;
                case 3: System.out.println("Enter the account number: ");
                        acc_no = sc.nextInt();
                        System.out.println("Enter your PIN: ");
                        PIN = sc.nextInt();
                        found=false;

                        for(int i=0;i<c.length;i++) {
                            try {
                                found = c[i].search(acc_no, PIN);
                            } catch (InvalidPinNumberException e){
                                System.err.println("Error:"+e.getMessage());
                            }
                            if(found==true) {
                                c[i].deposit();
                                break;
                            }
                        }
                        break;
                case 4: System.out.println("Enter the account number: ");
                        acc_no = sc.nextInt();
                        System.out.println("Enter your PIN: ");
                        PIN = sc.nextInt();
                        found=false;
                        System.out.println("Enter amount to withdraw: ");
                        double amt=sc.nextDouble();

                        for(int i=0;i<c.length;i++) {
                            try {
                                found = c[i].search(acc_no, PIN);
                                c[i].withdrawal(amt);
                            } catch (InvalidPinNumberException e) {
                                System.err.println("Error: "+e.getMessage());
                            } catch (InsufficientFundsException e) {
                                System.err.println("Error: "+e.getMessage());
                            }
                            if(found==true) {
                                break;
                            }
                        }
                        break;
                case 5: System.out.println("Thank you for using HDFC WORLD.");
                        break;
                default: System.out.println("Not a Valid input.");
            }  
        } while(a != 5);
        sc.close();
    }
}