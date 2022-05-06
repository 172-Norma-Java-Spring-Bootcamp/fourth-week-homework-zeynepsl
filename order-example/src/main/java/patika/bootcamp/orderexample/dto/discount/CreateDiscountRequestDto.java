package patika.bootcamp.orderexample.dto.discount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiscountRequestDto {
    private String code;
    private Double discountPercent;
    private boolean status;
}
