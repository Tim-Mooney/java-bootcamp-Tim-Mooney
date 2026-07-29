/*
 * Northstar CRM customer
 * correlation note: lab-request-001
 */

public record NorthstarCustomer(String id, String fullName, Status status) {

    public NorthstarCustomer {
        java.util.Objects.requireNonNull(id, "id must not be null");
        java.util.Objects.requireNonNull(fullName, "fullName must not be null");
        java.util.Objects.requireNonNull(status, "status must not be null");
    }

    public enum Status {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }

    // Returns true if customer's status is ACTIVE
    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    // Return a copy with a new status
    public NorthstarCustomer withStatus(Status newStatus) {
        java.util.Objects.requireNonNull(newStatus, "newStatus must not be null");
        return new NorthstarCustomer(id, fullName, newStatus);
    }

    // Return a copy with updated fullName
    public NorthstarCustomer withFullName(String newFullName) {
        java.util.Objects.requireNonNull(newFullName, "fullName must not be null");
        return new NorthstarCustomer(id, newFullName, status);
    }

    // Display-friendly string
    public String displayName() {
        return fullName + " (" + id + ")";
    }

    // Mask the fullName for limited display, preserving first letter (if present)
    public String maskedName() {
        if (fullName == null || fullName.isEmpty()) return fullName;
        String first = fullName.substring(0, 1);
        String rest = fullName.substring(1).replaceAll(".", "*");
        return first + rest;
    }

    // Example instantiation (comment only):
    // var customer = new NorthstarCustomer("CUS-1001", "Amina Khan", Status.ACTIVE);
}
