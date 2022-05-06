package patika.bootcamp.orderexample.exception;

public final class ServiceOperationException {

	

	private ServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }
    
    public static class CustomerAlreadyDeletedException extends BaseException {
    	public CustomerAlreadyDeletedException(String message) {
			super(message);
		}
	}
    
    public static class ProductNotFoundException extends BaseException{
    	public ProductNotFoundException(String message) {
    		super(message);
		}
    }
    
	public static class CategoryNotFoundException extends BaseException{
		public CategoryNotFoundException(String message) {
			super(message);
		}
	}

	public static class BrandNotFoundException extends BaseException {
		public BrandNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class BasketItemNotFoundException extends BaseException{
		public BasketItemNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class DiscountNotFoundException extends BaseException{
		public DiscountNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class DiscountAlreadyPassived extends BaseException {
		public DiscountAlreadyPassived(String message) {
			super(message);
		}
	}
}
