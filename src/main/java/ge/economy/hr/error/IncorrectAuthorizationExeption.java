package ge.economy.hr.error;

/**
 * @author invisible
 */
public class IncorrectAuthorizationExeption extends HrTypeException {

    public IncorrectAuthorizationExeption() {
    }

    public IncorrectAuthorizationExeption(String message) {
        super(message);
    }
}
