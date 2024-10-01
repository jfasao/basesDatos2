package exceptions;

import java.util.Map;

public class ApiRestException extends RuntimeException {
	
	 private Map<String, String> errors;

	    public ApiRestException(Map<String, String> errors) {
	        this.errors = errors;
	    }

	    public Map<String, String> toMap() {
	        return Map.copyOf(errors);
	    }
	

}
