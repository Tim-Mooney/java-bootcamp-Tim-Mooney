public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(){
        super("Insufficient balance in account");
    }
    public InsufficientBalanceException(String message){
        super(message);
    }
    public InsufficientBalanceException(String message, Throwable cause){
        super(message, cause);
    }
}