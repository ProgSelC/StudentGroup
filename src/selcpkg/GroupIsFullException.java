package selcpkg;

public class GroupIsFullException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage(){
		return "The group is full!";
	}
}
