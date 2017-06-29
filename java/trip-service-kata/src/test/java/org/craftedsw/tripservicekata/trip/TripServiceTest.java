package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TripServiceTest {

    public static final User GUEST = null;
    public static final User UNUSED_USER = null;
    public static final User REGISTERED_USER = new User();
    public static final User ANOTHER_FRIEND = new User();
    public static final Trip TO_BRAZIL = new Trip();
    private User loggedInUser;

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService();

        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends() {
        TripService tripService = new TestableTripService();

        loggedInUser = REGISTERED_USER;

        User friend = new User();
        friend.addFriend(ANOTHER_FRIEND);
        friend.addTrip(TO_BRAZIL);
        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips.size(), is(0));
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }
    }
}
