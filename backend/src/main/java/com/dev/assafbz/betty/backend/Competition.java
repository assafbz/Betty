package com.dev.assafbz.betty.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The object model for the data we are sending through endpoints
 */
@Entity
public class Competition {

    public Long getId() {
        return id;
    }

    @Id
    private Long id;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    private String competitionName;
}