package ma.s.gcm.exception;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BureauEtudeException extends RuntimeException {

    private ExceptionCode exceptionCode;
    private String tickedId;
    private String msg;
    private String technicalMessage;
    private LocalDateTime time;
    private Set<Error> errors = new HashSet<>();

    public BureauEtudeException(ExceptionCode exceptionCode) {
        this(exceptionCode, null, null, null);
    }

    public BureauEtudeException(ExceptionCode exceptionCode, String message) {
        this(exceptionCode, message, null, null);
    }

    public BureauEtudeException(ExceptionCode exceptionCode, String message, String technicalMessage) {
        this(exceptionCode, message, technicalMessage, null);
    }

    public BureauEtudeException(ExceptionCode exceptionCode, String message, String technicalMessage, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.technicalMessage = technicalMessage;
        this.exceptionCode = exceptionCode;
        tickedId = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public BureauEtudeException setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        return this;
    }

    public String getTickedId() {
        return tickedId;
    }

    public BureauEtudeException setTickedId(String tickedId) {
        this.tickedId = tickedId;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BureauEtudeException setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public BureauEtudeException setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public BureauEtudeException setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Set<Error> getErrors() {
        return errors;
    }

    public BureauEtudeException setErrors(Set<Error> errors) {
        this.errors = errors;
        return this;
    }
}