package patika.bootcamp.orderexample.exception;

public final class ControllerOperationException {

	private ControllerOperationException() {
    }

    public static class CustomerNotValidException extends BaseException {
        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class IDNotValidException extends BaseException {
        public IDNotValidException(String message) {
            super(message);
        }
    }
    
    public static class ProductNotValidException extends BaseException {
        public ProductNotValidException(String message) {
            super(message);
        }
    }
    
    public static class BasketNotValidException extends BaseException{
    	public BasketNotValidException(String message) {
    		super(message);
		}
    }
    
    public static class CategoryNotValidException extends BaseException {
    	public CategoryNotValidException(String message) {
    		super(message);
		}
	}
    
    public static class BrandNotValidException extends BaseException {
    	public BrandNotValidException(String message) {
    		super(message);
		}
	}

    public static class DiscountNotValidException extends BaseException {
    	public DiscountNotValidException(String message) {
    		super(message);
		}
	}
    
    public static class OrderNotValidException extends BaseException {
    	public OrderNotValidException(String message) {
    		super(message);
		}
	}
}
