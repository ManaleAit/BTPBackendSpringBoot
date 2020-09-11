package ma.s.gcm.exception;

public enum ExceptionCode {

    API_VALIDATION(400, "Invalid request payload"),
    API_UNIQUE(404, "Data must be unique"),
    API_DATA_ERRORS(400, "Database server error "),
    API_MISSING_PARAMETER(400, "Mandatory request parameter is missing"),
    API_BAD_REQUEST(400, "Invalid request syntax"),
    PAGE_NOT_FOUND(400, "Requested page not found"),
    API_UNAUTHORIZED(401, "Request not authenticated"),
    API_FORBIDDEN(403, "Client does not have sufficient permission"),
    API_RESOURCE_NOT_FOUND(404, "Resource is not found"),
    API_ERROR_INTERNAL(500, "Internal server error."),
	API_ERROR_TYPE_JOUR_FERIE(404, "NOT AMMOWED TYPE OF PUBLIC HOLIDAY"),
	API_ERROR_DELETE_JOUR_FERIE(404, "NOT AMMOWED DELETED TYPE OF PUBLIC HOLIDAY"),
	API_ERROR_CONGE(400, "the length of leave must be inferior 30"),
	API_DATE_NULL(400, "the Date is Null");
	
    private final int status;

    private final String code;

    ExceptionCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
