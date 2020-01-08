package no.plasmid.nyhende.exception;

import org.springframework.http.HttpStatus;

public abstract class NyhendeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public NyhendeException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public NyhendeException(HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public NyhendeException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public NyhendeException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
