package patika.bootcamp.orderexample.exception;

public class OrderServiceOperationException {
	private OrderServiceOperationException() {
	}
	
	public static class BasketIsNotValidForOrderException extends BaseException{
		public BasketIsNotValidForOrderException(String message) {
			super(message);
		}
	}

	public static class OrderNotFound extends BaseException{
		public OrderNotFound(String message) {
			super(message);
		}
	}
}
