package no.plasmid.nyhende.exception;

import no.plasmid.nyhende.json.ErrorJson;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMapper {
    private final static Logger LOG = LoggerFactory.getLogger(ExceptionMapper.class);

    @ExceptionHandler(NyhendeException.class)
    @ResponseBody
    public ResponseEntity<ErrorJson> handleNyhendeException(NyhendeException e) {
        OrientDBTransactionWrapper.getInstance().rollback();

        LOG.error("Error message: ", e.getMessage());
        LOG.debug("Stack: ", e.getStackTrace());
        return new ResponseEntity<>(new ErrorJson(e), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJson handleAnyException(Exception e) {
        OrientDBTransactionWrapper.getInstance().rollback();

        LOG.error(e.getMessage(), e);

        return new ErrorJson(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again");
    }

}
