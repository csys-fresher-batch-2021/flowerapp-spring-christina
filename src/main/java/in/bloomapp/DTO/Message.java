package in.bloomapp.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(value = Include.NON_NULL)
@Data
@NoArgsConstructor
public class Message {

	private String errorMessage;

	private String infoMessage;

}