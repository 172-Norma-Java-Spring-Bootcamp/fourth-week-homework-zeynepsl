package patika.bootcamp.orderexample.exception;

public class OrderItemServiceOperationException {
	private OrderItemServiceOperationException() {
	}
	
	public static class OrderItemNotFoundException extends BaseException{
		public OrderItemNotFoundException(String message) {
			super(message);
		}
	}
}
