package io.gs2.core.exception;

import io.gs2.core.domain.StampSheetDomain;

public class TransactionException extends Gs2Exception {

    private StampSheetDomain stampSheet;
    private boolean isWorthRetry;

    public TransactionException(
            StampSheetDomain stampSheet,
            Gs2Exception exception
    ) {
        super(exception.errors);
        this.stampSheet = stampSheet;
        this.isWorthRetry = exception instanceof InternalServerErrorException ||
                exception instanceof QuotaLimitExceededException ||
                exception instanceof ServiceUnavailableException ||
                exception instanceof ConflictException ||
                exception instanceof RequestTimeoutException ||
                exception instanceof UnauthorizedException;
    }

    public boolean isWorthRetry() {
        return isWorthRetry;
    }

    public void retry() {
        this.stampSheet.run();
    }
}
