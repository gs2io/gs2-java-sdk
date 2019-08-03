package io.gs2.net;

import io.gs2.exception.Gs2Exception;

public abstract class Gs2Response {
    protected String message;
    protected Gs2Exception exception;

    public Gs2Response(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public Gs2Exception getGs2Exception()
    {
        return exception;
    }
}
