public class Main {
    public static void main(String[] args){
        BankService service = new BankService();

        try {
            service.withdraw(1000, 500);
        } catch(InsufficientBalanceException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}