package io.ipldashboard.ipl_dashboard.Model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String teamName;
    private long totalMatch;
    private long totalWin;

    @Transient
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Team(String teamName, long totalMatch) {
        this.teamName = teamName;
        this.totalMatch=totalMatch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTotalMatch() {
        return totalMatch;
    }

    public void setTotalMatch(long totalMatch) {
        this.totalMatch = totalMatch;
    }

    public long getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(long totalWin) {
        this.totalWin = totalWin;
    }

    public Team() {
    }
}
