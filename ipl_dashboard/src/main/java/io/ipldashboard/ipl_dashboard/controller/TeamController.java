package io.ipldashboard.ipl_dashboard.controller;

import io.ipldashboard.ipl_dashboard.Model.Match;
import io.ipldashboard.ipl_dashboard.Model.Team;
import io.ipldashboard.ipl_dashboard.Repository.MatchRepository;
import io.ipldashboard.ipl_dashboard.Repository.TeamRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository=matchRepository;
    }

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
     Team team= this.teamRepository.findByTeamName(teamName);
     team.setMatches(this.matchRepository.findLatestMatchByTeam(teamName,4));
     return team;
    }
}
