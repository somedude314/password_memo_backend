package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Asif Mahbub on 6/30/2018.
 */
@AllArgsConstructor
public class UserTimeSpentInEachSession {

    @Getter @Setter
    private String userID;
    @Getter @Setter
    private String sessionID;
    @Getter @Setter
    private long timeSpent;

}
