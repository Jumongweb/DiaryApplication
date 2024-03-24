package semicolon.exceptions;

public class RemoveUserRequest extends RuntimeException {
    public RemoveUserRequest(String message){
        super(message);
    }
}

