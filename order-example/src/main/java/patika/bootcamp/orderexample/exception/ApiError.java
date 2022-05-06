package patika.bootcamp.orderexample.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;
	
	private ApiError() {
		timestamp = LocalDateTime.now();
	}
	
	public ApiError(String message) {
		this();
		this.message = message;
	}
}
