package ma.s.gcm.exception;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ExceptionDto {

    private String code;
    private String message;
    private String ticketId;
    private int status;
    private LocalDateTime time;
    private Set<Error> errors;
    private Set<FieldError> fieldErrors;

    public ExceptionDto() {
        this.errors = new HashSet<>();
        this.fieldErrors = new HashSet<>();
        this.time = LocalDateTime.now();
        this.ticketId = UUID.randomUUID().toString();
    }

    public String getCode() {
        return code;
    }

    public ExceptionDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ExceptionDto setTicketId(String ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public Set<Error> getErrors() {
        return errors;
    }

    public ExceptionDto setErrors(Set<Error> errors) {
        this.errors = errors;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ExceptionDto setStatus(int status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ExceptionDto setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Set<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public ExceptionDto setFieldErrors(Set<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }
}
