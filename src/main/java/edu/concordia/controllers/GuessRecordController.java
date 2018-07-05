package edu.concordia.controllers;

import edu.concordia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Asif Mahbub on 6/29/2018.
 */
@RestController
@RequestMapping("/api")
public class GuessRecordController {
    @Autowired
    private GuessRepository repo;

    @GetMapping("/guess/{id}")
    public Guess guessRecord(@PathVariable int id){
        return repo.getOne(id);
    }

    @GetMapping("/memo")
    public List<MemorizationRecord> getMemorizationRecord(){
        List<Guess> guesses = repo.findAll();

        Map<String, List<Guess>> guessesByUser = guesses
                .stream()
                .filter(e-> e.getFullText().equals(e.getUserInput()))
                .collect(Collectors.groupingBy(Guess::getUserID));
        List<MemorizationRecord> memorizationRecords = new ArrayList<>();
        guessesByUser
                .entrySet()
                .stream()
                .forEach(e-> memorizationRecords.add(new MemorizationRecord(e.getKey(),e.getValue().size())));
        return  memorizationRecords;

    }
    @GetMapping("/guessTimeTotal")
        public List<UserTotalTimeSpent> getUserTimeSpentTotal(){
        List<Guess> guesses = repo.findAll();

        Map<String, Map<String, List<Guess>>> guessesByUserEachSession = guesses
                .stream()
                .collect(Collectors.groupingBy(Guess::getUserID,Collectors.groupingBy(Guess::getSessionID)));

   //     System.out.println(guessesByUserEachSession);
        Comparator<Guess> comparator = new TimeStampComarator();
        List<UserTotalTimeSpent> usersAndTotalTimeSpent = new ArrayList<>();
        int timeSpentInMins = 0;

        for (String userId:
             guessesByUserEachSession.keySet()) {
            Map<String, List<Guess>> guessBySession = guessesByUserEachSession.get(userId);
            for (String sessions:
                 guessBySession.keySet()) {
                List<Guess> guesses1 = guessBySession.get(sessions);
                Guess max = Collections.max(guesses1,comparator);
                Guess min = Collections.min(guesses1,comparator);
                int timeSpent = (int) ((max.getTimestamp().getTime()-min.getTimestamp().getTime())/60000);
                timeSpentInMins+=timeSpent;
//                System.out.println(max.getTimestamp().toString());
//                System.out.println(min.getTimestamp().toString());
//                System.out.println("time spent: "+ timeSpent);
//
//                System.out.println(userId+" "+sessions+" "+"no of memo: "+guesses1.size());
            }
            usersAndTotalTimeSpent.add(new UserTotalTimeSpent(userId,timeSpentInMins));
        }
       // System.out.println("total Time spent: "+timeSpentInMins);
        return  usersAndTotalTimeSpent;

    }

    @GetMapping("/guessTimeSession")
    public List<UserTimeSpentInEachSession> getUserTimeSpentInEachSessions(){
        List<Guess> guesses = repo.findAll();

        Map<String, Map<String, List<Guess>>> guessesByUserEachSession = guesses
                .stream()
                .collect(Collectors.groupingBy(Guess::getUserID,Collectors.groupingBy(Guess::getSessionID)));

        //     System.out.println(guessesByUserEachSession);
        Comparator<Guess> comparator = new TimeStampComarator();
        List<UserTimeSpentInEachSession> usersAndSessionTimeSpent = new ArrayList<>();


        for (String userId:
                guessesByUserEachSession.keySet()) {
            Map<String, List<Guess>> guessBySession = guessesByUserEachSession.get(userId);
            for (String sessions:
                    guessBySession.keySet()) {
                int timeSpentInMins = 0;
                List<Guess> guesses1 = guessBySession.get(sessions);
                Guess max = Collections.max(guesses1,comparator);
                Guess min = Collections.min(guesses1,comparator);
                int timeSpent = (int) ((max.getTimestamp().getTime()-min.getTimestamp().getTime())/60000);
                timeSpentInMins+=timeSpent;
                usersAndSessionTimeSpent.add(new UserTimeSpentInEachSession(userId,sessions,timeSpentInMins));
            }

        }
        return  usersAndSessionTimeSpent;

    }

    @PostMapping("/guess")
    public void saveGuessRecord(@RequestBody Guess record){
        repo.save(record);
    }

}
class TimeStampComarator implements Comparator<Guess> {

    @Override
    public int compare(Guess o1, Guess o2) {
        long t1 = o1.getTimestamp().getTime();
        long t2 = o2.getTimestamp().getTime();
        if(t2 > t1)
            return -1;
        else if(t1 > t2)
            return 1;
        else
            return 0;
    }
}
