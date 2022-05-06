package patika.bootcamp.orderexample.exception;

public class BasketServiceOperationException {


	private BasketServiceOperationException() {
		
	}
	
	public static class InsufficientStockOfProduct extends BaseException{
		public InsufficientStockOfProduct(String message) {
			super(message);
		}
	}
	
	public static class BasketNotFound extends BaseException {
		public BasketNotFound(String message) {
			super(message);
		}
	}
}
