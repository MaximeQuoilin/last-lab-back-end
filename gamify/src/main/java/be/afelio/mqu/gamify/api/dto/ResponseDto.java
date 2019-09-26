package be.afelio.mqu.gamify.api.dto;

public class ResponseDto<T> {

	private ResponseDtoStatus status;
	private String message;
	private T payload;
	
	public ResponseDto() {}
	
	public ResponseDto(ResponseDtoStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDtoStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseDtoStatus status) {
		this.status = status;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
}
