package ie.tus.gym;

/**
 * Custom unchecked exception.
 * Demonstrates:
 * - Exception subclassing
 * - Overloaded constructors
 */
public class InvalidSessionException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidSessionException() { super(); }
    public InvalidSessionException(String msg) { super(msg); }
}
