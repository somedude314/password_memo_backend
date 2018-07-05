package edu.concordia.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Asif Mahbub on 6/29/2018.
 */
public interface GuessRepository extends JpaRepository<Guess, Integer> {



}
