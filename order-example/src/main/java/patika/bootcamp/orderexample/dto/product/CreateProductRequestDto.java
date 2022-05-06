package patika.bootcamp.orderexample.dto.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {
	private String name;
    private BigDecimal price;
    private String image;
    private Integer stockAmount;
    private Long brandId;
    private Long categoryId;
}
