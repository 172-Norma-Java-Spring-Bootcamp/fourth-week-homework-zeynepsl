package patika.bootcamp.orderexample.exception;

public class CustomerServiceOperationException {
	private CustomerServiceOperationException() {
	}
	
	public static class CustomerAlreadyDeletedException extends BaseException{
		public CustomerAlreadyDeletedException(String message) {
			super(message);
		}
	}
	
	public static class CustomerNotFoundException extends BaseException{
		public CustomerNotFoundException(String message) {
			super(message);
		}
	}
}
