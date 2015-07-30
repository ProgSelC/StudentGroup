package selcpkg;

public class DuplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Error trying to add duplicate record!";
	}
}
