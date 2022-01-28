package io.gs2.core.domain;

public class StampSheetConfiguration {

    public String namespaceName;
    public ExecuteStampTaskEvent stampTaskEventHandler;
    public ExecuteStampSheetEvent stampSheetEventHandler;

    private StampSheetConfiguration(
            String namespaceName,
            ExecuteStampTaskEvent stampTaskEventHandler,
            ExecuteStampSheetEvent stampSheetEventHandler
    ) {
        this.namespaceName = namespaceName;
        this.stampTaskEventHandler = stampTaskEventHandler;
        this.stampSheetEventHandler = stampSheetEventHandler;
    }

    public static StampSheetConfigurationBuilder builder() {
        return new StampSheetConfigurationBuilder();
    }

    public static class StampSheetConfigurationBuilder {

        public String namespaceName;

        StampSheetConfigurationBuilder(){}

        public StampSheetConfigurationBuilder withNamespaceName(String namespaceName) {
            this.namespaceName = namespaceName;
            return this;
        }

        public StampSheetConfiguration build() {
            return new StampSheetConfiguration(
                    namespaceName,
                    Gs2::updateCacheFromStampTask,
                    Gs2::updateCacheFromStampSheet
            );
        }
    }
}
