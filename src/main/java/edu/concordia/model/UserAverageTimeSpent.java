package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Asif Mahbub on 7/6/2018.
 */
@AllArgsConstructor
public class UserAverageTimeSpent {
    @Getter
    @Setter
    private String userID;

    @Getter@Setter
    private double averageTimeInMins;
}
