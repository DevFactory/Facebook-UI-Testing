package core;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.StringContains;

public class ErrorMessageMatcher {

    public static Matcher<String> containsError(ErrorMessage errorMessage) {
        return StringContains.containsString(errorMessage.toString());
    }

    public static Matcher<String> equalToError(ErrorMessage errorMessage) {
        return IsEqual.equalTo(errorMessage.toString());
    }
}
