package io.gs2.core.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.exception.*;
import io.gs2.core.model.GeneralError;

public class Gs2RestResponse extends Gs2Response {

    public Gs2RestResponse(String message, int statusCode)
    {
        super(message);

        String errorMessage;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            GeneralError error = mapper.readValue(message, GeneralError.class);
            errorMessage = error.getMessage();
        }
        catch (Exception e)
        {
            errorMessage = message;
        }

        switch (statusCode) {
            case 200:
                break;
            case 400:
                exception = new BadRequestException(errorMessage);
                break;
            case 502:
                exception = new BadGatewayException(errorMessage);
                break;
            case 401:
                exception = new UnauthorizedException(errorMessage);
                break;
            case 409:
                exception = new ConflictException(errorMessage);
                break;
            case 500:
                exception = new InternalServerErrorException(errorMessage);
                break;
            case 404:
                exception = new NotFoundException(errorMessage);
                break;
            case 402:
                exception = new QuotaLimitExceededException(errorMessage);
                break;
            case 408:
                exception = new RequestTimeoutException(errorMessage);
                break;
            case 503:
                exception = new ServiceUnavailableException(errorMessage);
                break;
            default:
                System.out.println(statusCode);
                exception = new UnknownException(errorMessage != null ? errorMessage : "");
                break;
        }
    }
}
