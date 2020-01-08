package no.plasmid.nyhende.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import no.plasmid.nyhende.exception.NyhendeException;
import org.springframework.http.HttpStatus;

import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorJson {

    private Integer statusCode;
    private String message;
    private String timestamp;

    public ErrorJson(NyhendeException e) {
        this(e.getHttpStatus(), e.getMessage());
    }

    public ErrorJson(HttpStatus status, String message) {
       this(status.value(), message);
    }

    public ErrorJson(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = Calendar.getInstance().toString();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
