package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserTotalTimeSpent {
    @Getter
    @Setter
    private String userID;

    @Getter@Setter
    private int timeInMins;
}
