package ge.economy.hr.error;

/**
 * @author invisible
 */
public class OperationNotPermittedExeption extends HrTypeException {

    public OperationNotPermittedExeption() {
    }

    public OperationNotPermittedExeption(String message) {
        super(message);
    }
}
