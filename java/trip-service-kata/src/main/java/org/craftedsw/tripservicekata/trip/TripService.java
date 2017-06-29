package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (getLoggedUser() == null)
            throw new UserNotLoggedInException();

        return user.isFriendWith(getLoggedUser())
                ? tripsBy(user)
                : noTrips();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
