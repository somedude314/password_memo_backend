package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by Asif Mahbub on 6/30/2018.
 */
@AllArgsConstructor
public class SessionTimes {
    @Getter @Setter
    private String userID;
    @Getter@Setter
    private String sessionID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionTimes that = (SessionTimes) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(sessionID, that.sessionID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID, sessionID);
    }
}
