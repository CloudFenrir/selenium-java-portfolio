package com.cloudfenrir.tests.api;

import com.cloudfenrir.api.BookingClient;
import com.cloudfenrir.api.models.Booking;
import com.cloudfenrir.api.models.BookingResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingApiTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test
    public void createBookingShouldReturnSameData() {
        Booking request = Booking.builder()
                .firstName("Luis")
                .lastName("Romero")
                .totalPrice(150)
                .build();

        Response response = bookingClient.createBooking(request);
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        BookingResponse body = response.as(BookingResponse.class);     // deserialization
        Assert.assertTrue(body.getBookingId() > 0, "Booking id should be positive");
        Assert.assertEquals(body.getBooking().getFirstName(), "Luis");
        Assert.assertEquals(body.getBooking().getTotalPrice(), 150);
        Assert.assertEquals(body.getBooking().getBookingDates().getCheckin(),
                request.getBookingDates().getCheckin());
    }

    @Test
    public void getBookingShouldReturnCreatedBooking() {
        Booking request = Booking.builder()
                .firstName("Perla")
                .lastName("Romero")
                .totalPrice(200)
                .build();
        int id = createBookingAndGetId(request);

        Response getResponse = bookingClient.getBooking(id);
        Assert.assertEquals(getResponse.getStatusCode(), 200, "Unexpected status code");

        Booking getBody = getResponse.as(Booking.class);
        Assert.assertEquals(getBody.getTotalPrice(), request.getTotalPrice());
        Assert.assertEquals(getBody.getFirstName(), request.getFirstName());
    }

    @Test
    public void deleteBookingShouldRemoveIt() {
        Booking request = Booking.builder().build();
        int id = createBookingAndGetId(request);
        String token = bookingClient.getToken();

        Response deleteResponse = bookingClient.deleteBooking(id, token);
        Assert.assertEquals(deleteResponse.getStatusCode(), 201, "Unexpected status code");

        Response getResponse = bookingClient.getBooking(id);
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Booking still exists after delete");
    }

    @Test
    public void getNonExistentBookingShouldReturn404() {
        int notExistingId = Integer.MAX_VALUE;
        Response getResponse = bookingClient.getBooking(notExistingId);
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Booking with provided id exists");
    }

    private int createBookingAndGetId(Booking booking) {
        Response response = bookingClient.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 200, "Booking creation failed");
        return response.as(BookingResponse.class).getBookingId();
    }

}
