package ma.s.gcm.exception;

public class BureauEtudeExceptionMapper {

    private BureauEtudeExceptionMapper() {
    }

    public static ExceptionDto toExceptionDto(BureauEtudeException exception) {
        return new ExceptionDto()
                .setCode(exception.getExceptionCode().name())
                .setMessage(exception.getMsg())
                .setTicketId(exception.getTickedId())
                .setTime(exception.getTime())
                .setStatus(exception.getExceptionCode().getStatus())
                .setErrors(exception.getErrors());
    }
}
