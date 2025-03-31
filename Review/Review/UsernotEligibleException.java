package Review;

public class UsernotEligibleException extends RuntimeException{
    public UsernotEligibleException(String message) {
        super(message);
    }
}
