package ma.s.gcm.exception;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class BureauEtudeMatcher extends TypeSafeMatcher<BureauEtudeException> {

    private final ExceptionCode exeExceptionCode;
    private ExceptionCode foundExceptionCode;

    private BureauEtudeMatcher(ExceptionCode exceptionCode) {
        this.exeExceptionCode = exceptionCode;
    }

    public static BureauEtudeMatcher hasType(ExceptionCode item) {
        return new BureauEtudeMatcher(item);
    }

    @Override
    protected boolean matchesSafely(final BureauEtudeException exception) {
        foundExceptionCode = exception.getExceptionCode();
        return exeExceptionCode.equals(exception.getExceptionCode());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected type is ")
                .appendValue(exeExceptionCode.name())
                .appendText(" but ")
                .appendValue(foundExceptionCode)
                .appendText(" was found");
    }
}
