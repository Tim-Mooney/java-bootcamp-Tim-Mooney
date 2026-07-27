public class BankService {
    public void withdraw(double amount, double balance) throws InsufficientBalanceException {
        if(amount > balance){
            throw new InsufficientBalanceException("Cannot withdraw " + amount + ". Amount available "+ balance);
        }
    }
}