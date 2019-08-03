package io.gs2.net;

import io.gs2.exception.Gs2Exception;

public class Gs2ClientErrorResponse extends Gs2Response
{
    public Gs2ClientErrorResponse(Gs2Exception exception)
    {
        super("");
        this.exception = exception;
    }
}
