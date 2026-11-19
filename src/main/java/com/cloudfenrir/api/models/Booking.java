package com.cloudfenrir.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName = "Test";
        private String lastName = "Last Name";
        private int totalPrice = 100;
        private boolean depositPaid = true;
        private String additionalNeeds = "Needs water";
        private BookingDates bookingDates = new BookingDates("2026-12-01", "2026-12-05");

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder totalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder depositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public Builder additionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
            return this;
        }

        public Builder bookingDates(BookingDates bookingDates) {
            this.bookingDates = bookingDates;
            return this;
        }

        public Booking build() {
            Booking booking = new Booking();
            booking.setFirstName(firstName);
            booking.setLastName(lastName);
            booking.setTotalPrice(totalPrice);
            booking.setDepositPaid(depositPaid);
            booking.setAdditionalNeeds(additionalNeeds);
            booking.setBookingDates(bookingDates);
            return booking;
        }

    }

}
