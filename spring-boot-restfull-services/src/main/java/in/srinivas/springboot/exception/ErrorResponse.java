package in.srinivas.springboot.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	private LocalDateTime timeStamp;
	private String message;
	private String path;
	private String errorCode;

}
