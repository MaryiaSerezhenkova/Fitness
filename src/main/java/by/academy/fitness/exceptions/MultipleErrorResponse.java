package by.academy.fitness.exceptions;

import java.util.List;

public class MultipleErrorResponse {

    private String logref;
    private List<SingleError> errors;

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<SingleError> getErrors() {
        return errors;
    }

    public void setErrors(List<SingleError> errors) {
        this.errors = errors;
    }
}