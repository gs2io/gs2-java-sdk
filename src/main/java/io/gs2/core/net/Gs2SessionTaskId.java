package io.gs2.core.net;

public class Gs2SessionTaskId {

    private static final int INVALID_ID_VALUE = 0;
    private static final int LOGIN_ID_VALUE = 1;
    private static final int RESERVED_ID_VALUE_MAX = 10000;

    public static final Gs2SessionTaskId INVALID_ID = new Gs2SessionTaskId(INVALID_ID_VALUE);
    public static final Gs2SessionTaskId LOGIN_ID = new Gs2SessionTaskId(LOGIN_ID_VALUE);

    private int value;

    private Gs2SessionTaskId(int value) {
        this.value = value;
    }

    public Gs2SessionTaskId(String value) {
        try {
            this.value = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            this.value = INVALID_ID_VALUE;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gs2SessionTaskId)) {
            return false;
        }
        return ((Gs2SessionTaskId) object).value == this.value;
    }

    public static class Generator {
        private int valueCounter = INVALID_ID_VALUE;

        Gs2SessionTaskId issue() {
            if (++valueCounter <= RESERVED_ID_VALUE_MAX) {
                valueCounter = RESERVED_ID_VALUE_MAX + 1;
            }

            return new Gs2SessionTaskId(valueCounter);
        }
    }
}
