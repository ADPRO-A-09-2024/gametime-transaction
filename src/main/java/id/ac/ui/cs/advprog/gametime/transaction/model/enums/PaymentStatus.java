package id.ac.ui.cs.advprog.gametime.transaction.model.enums;

public enum PaymentStatus {
    WAITING("WAITING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");
    private final String value;

    private PaymentStatus(String value){
        this.value = value;
    }

    public static boolean contains(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
