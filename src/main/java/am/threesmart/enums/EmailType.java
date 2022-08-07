package am.threesmart.enums;

public enum EmailType {
    RESET_PASSWORD("RESET_PASSWORD");

    private final String value;

    EmailType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
