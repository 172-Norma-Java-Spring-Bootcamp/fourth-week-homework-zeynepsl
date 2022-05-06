package patika.bootcamp.orderexample.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.BasketExceptionMessage;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.CustomerExceptionMessage;
import patika.bootcamp.orderexample.converter.basket.BasketConverter;
import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.dto.basket.BasketResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.BasketServiceOperationException;
import patika.bootcamp.orderexample.exception.CustomerServiceOperationException;
import patika.bootcamp.orderexample.model.Basket;
import patika.bootcamp.orderexample.model.BasketItem;
import patika.bootcamp.orderexample.model.Customer;
import patika.bootcamp.orderexample.model.Product;
import patika.bootcamp.orderexample.repository.BasketRepository;
import patika.bootcamp.orderexample.service.BasketItemService;
import patika.bootcamp.orderexample.service.BasketService;
import patika.bootcamp.orderexample.service.CustomerService;
import patika.bootcamp.orderexample.service.DiscountService;
import patika.bootcamp.orderexample.service.ProductService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {
	private final BasketRepository basketRepository;
	private final BasketConverter basketConverter;
	private final CustomerService customerService;
	private final ProductService productService;
	private final BasketItemService basketItemService;
	private final DiscountService discountService;

	@Override
	public void createWithBasketDto(BasketDto basketDto) {
		Basket basket = basketConverter.toBasket(basketDto);
		basketRepository.save(basket);
	}

	@Override
	public Basket createWithCustomer(Customer customer) {
		Basket basket = new Basket();
		basket.setCustomer(customer);
		basketRepository.save(basket);
		return basket;
	}
	
	@Override
	public void createWithBasket(Basket basket) {
		basketRepository.save(basket);
	}

	@Override
	public Basket getByCustomerId(Long customerId) {
		Basket basket = basketRepository.findByCustomer_Id(customerId);
		if (basket == null) {
			throw new BasketServiceOperationException.BasketNotFound(BasketExceptionMessage.notFound);
		}
		return basket;// donus turunu degistir
	}

	@Override
	public Basket get(Long basketId) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new BasketServiceOperationException.BasketNotFound(BasketExceptionMessage.notFound));
		return basket;
	}

	@Override
	public BasketResponseDto addProductToBasket(Long productId, Long customerId, Integer amount) throws BaseException {
		Product productToBeAdded = productService.getProduct(productId);
		if(customerService.getCustomerAllInfo(customerId).isDeleted()) {
			throw new CustomerServiceOperationException.CustomerAlreadyDeletedException(CustomerExceptionMessage.notFoundAlreadyDeleted);
		}
		if (productToBeAdded.getStockAmount() < amount) {
			throw new BasketServiceOperationException.InsufficientStockOfProduct(BasketExceptionMessage.stockIsNotEnough);
		}
		Customer customer = customerService.getCustomerAllInfo(customerId);
		Basket basket = basketRepository.findByCustomer_Id(customerId);
		if (basket == null) {
			log.info("obje null");
			basket = new Basket();
			basket.setCustomer(customer);
			basket.setTotalPrice(BigDecimal.ONE);
			basket.setPrice(BigDecimal.ZERO);
			basket.setDiscount(discountService.getDiscount(1L));
		}

		BasketItem basketItem = new BasketItem();
		basketItem.setQuantity(amount);
		basketItem.setProduct(productToBeAdded);
		basketItem.setPrice(productToBeAdded.getPrice());
		basketItem.setTaxPrice(0.18);
		basketItem.setShippingPrice(BigDecimal.valueOf(10));

		basketRepository.save(basket);
		
		basketItem.setBasket(basket);
		basket.getBasketItems().add(basketItem);
		
		basketItemService.createWithBasketItem(basketItem);
		
		basketRepository.save(basket);
		
		customer.setBasket(basket);
		customerService.save(customer);

		log.info("basket id {}", basket.getId());
		log.info("basket item id {}", basketItem.getId());
		log.info("customer {}", customer.getId());
		return basketConverter.toBasketResponseDto(basket);
	}
	
	//sepetteki ilgili urunu, sepette kac tane olursa olsun sepetten kaldirmak:
	@Override
	public void removeProductToBasket(Long productId, Long basketId) throws BaseException {
		List<Long> idsOfItemsToDeleted = new ArrayList<Long>();
		basketItemService.findByBasket_Id(basketId).forEach(basketItem -> {
			if(basketItem.getProduct().getId().equals(productId)) {
				idsOfItemsToDeleted.add(basketItem.getId());//  db deki pk yi bize verecek
			}
		});
		Long[] ids = new Long[idsOfItemsToDeleted.size()];
		for(int i = 0; i < ids.length; i++) {
			ids[i] = idsOfItemsToDeleted.get(i);
		}
		basketItemService.deleteBasketItemsWithIds(ids);
	}

	@Override
	public Basket calculateTotalPriceOfBasket(Basket basket) {
		for (BasketItem basketItem : basket.getBasketItems()) {		
			
			log.info("basketItem.getPrice {}",basketItem.getPrice());
			BigDecimal price = (basketItem.getPrice()).multiply(BigDecimal.valueOf(basketItem.getQuantity()));
			basket.setTotalPrice(basket.getTotalPrice().add(price));
			log.info("price-> {}", basket.getTotalPrice());

			log.info("basketItem.getTaxPrice {}", basketItem.getTaxPrice());
			BigDecimal taxPrice =  BigDecimal.valueOf(basketItem.getTaxPrice()).multiply(basketItem.getPrice()); //18
			log.info("-uygulanacak kdv: {}", taxPrice);	
			BigDecimal totalTax = taxPrice.multiply(BigDecimal.valueOf(basketItem.getQuantity()));// 18 * 5 => 90 tl kdv
			basket.setTaxPrice(basket.getTaxPrice() + totalTax.doubleValue());
			basket.setTotalPrice(basket.getTotalPrice().add(totalTax));

			basketItem.setTaxPrice(totalTax.doubleValue());
			basketItem.setPrice(basketItem.getPrice().add(totalTax));
			log.info("tax-> {}", basket.getTaxPrice());
			log.info("price with tax -> {}", basket.getTotalPrice());

			log.info("basketItem.getShippingPrice {}",basketItem.getShippingPrice());
			BigDecimal shippingPrice = basketItem.getShippingPrice();
			basket.setShippingPrice(basket.getShippingPrice().add(shippingPrice));
			basket.setTotalPrice(basket.getTotalPrice().add(shippingPrice));
			log.info("price with shipping-> {}", basket.getTotalPrice());

			basketItem.setPrice(basketItem.getPrice().add(shippingPrice));
			
			
			BigDecimal oran = BigDecimal.valueOf(basket.getDiscount().getDiscountPercent()).divide(BigDecimal.valueOf(100));
			BigDecimal discountPrice = (oran).multiply(basketItem.getPrice());
			BigDecimal totalDiscountPrice = (discountPrice).multiply(BigDecimal.valueOf(basketItem.getQuantity()));
			
			basket.setTotalPrice(basket.getTotalPrice().subtract(totalDiscountPrice));
			basketItem.setDiscountPrice(discountPrice);
			basketItem.setPrice(basketItem.getPrice().subtract(discountPrice));
			log.info("price with discount-> {}", basket.getTotalPrice());

			//basketItemService.createWithBasketItem(basketItem);
		}
		return basket;
	}

	// sepetteki urunleri bosaltma; kdv, kargo, toplam fiyat sifirlama :
	@Override	
	public void emptyBasket(Long basketId) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new BasketServiceOperationException.BasketNotFound(BasketExceptionMessage.notFound));
		List<BasketItem> basketItems = null;
		basketItems = basketItemService.findByBasket_Id(basketId);
		
		Long[] ids = new Long[basketItems.size()];
		for(int i = 0; i < basketItems.size(); i++) {
			ids[i] = basketItems.get(i).getId();
		}
		basketItemService.deleteBasketItemsWithIds(ids);
	
		log.info("before empty() {}", basket.getTotalPrice());
		basket.setDiscountPrice(BigDecimal.valueOf(0));
		basket.setPrice(BigDecimal.valueOf(0));
		basket.setShippingPrice(BigDecimal.valueOf(0));
		basket.setTaxPrice(0.0);
		basket.setTotalPrice(BigDecimal.valueOf(0));
		basketRepository.save(basket);
		log.info("after empty {}", basket.getTotalPrice());
	}
	

}
