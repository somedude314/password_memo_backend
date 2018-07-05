package edu.concordia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Asif Mahbub on 6/29/2018.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Guess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter
    private int id;// guess id

    @Getter@Setter
    private String userID;
    @Getter@Setter
    private String sessionID;
    @Getter@Setter
    private int nodeID;
    @Getter@Setter
    private String userInput;
    @Getter@Setter
    private String nodeText;
    @Getter@Setter
    private String fullText;
    @Getter@Setter
    private int memCount;
    @Getter
    private Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());

    public Guess(String userID, String sessionID, int nodeID, String userInput, String nodeText, String fullText, int memCount) {
        this.userID = userID;
        this.sessionID = sessionID;
        this.nodeID = nodeID;
        this.userInput = userInput;
        this.nodeText = nodeText;
        this.fullText = fullText;
        this.memCount = memCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guess guess = (Guess) o;
        return id == guess.id &&
                nodeID == guess.nodeID &&
                memCount == guess.memCount &&
                Objects.equals(userID, guess.userID) &&
                Objects.equals(sessionID, guess.sessionID) &&
                Objects.equals(userInput, guess.userInput) &&
                Objects.equals(nodeText, guess.nodeText) &&
                Objects.equals(fullText, guess.fullText) &&
                Objects.equals(timestamp, guess.timestamp);
    }
    {

    }
    public int hashCode() {

        return Objects.hash(id, userID, sessionID, nodeID, userInput, nodeText, fullText, memCount, timestamp);
    }


}
