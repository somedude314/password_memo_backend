package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Asif Mahbub on 6/29/2018.
 */
@AllArgsConstructor
public class MemorizationRecord {
    @Getter@Setter
    private String userID;
    @Getter@Setter
    private int noOfMemorization;
}
