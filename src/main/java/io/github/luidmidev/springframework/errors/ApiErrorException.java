package io.github.luidmidev.springframework.errors;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ProblemDetail;

/**
 * Exception to be thrown when an API based on the Problem Details specification, this
 * exception will be caught by the {@link DefaultExceptionHandler} and the response will be
 * built based on the {@link ProblemDetail} object.
 */
@Getter
public class ApiErrorException extends RuntimeException {


    /**
     * The headers to be used in the response.
     */
    private final HttpHeaders headers;

    /**
     * The problem detail object to be used as the response body.
     */
    private final transient ProblemDetail body;

    /**
     * Create a new instance of the exception with the given {@link ProblemDetail} object.
     * @param body the problem detail object to be used as the response body.
     */
    public ApiErrorException(ProblemDetail body) {
        this(body, new HttpHeaders());
    }

    /**
     * Create a new instance of the exception with the given {@link ProblemDetail} object and
     * @param body the problem detail object to be used as the response body.
     * @param headers the headers to be used in the response.
     */
    public ApiErrorException(ProblemDetail body, HttpHeaders headers) {
        this.body = body;
        this.headers = headers;
    }

    /**
     * Add an extension attribute to the response body before throwing the exception.
     * @param field the field name.
     * @param value the field value.
     * @return the exception instance.
     */
    public ApiErrorException extension(String field, String value) {
        body.setProperty(field, value);
        return this;
    }

}
