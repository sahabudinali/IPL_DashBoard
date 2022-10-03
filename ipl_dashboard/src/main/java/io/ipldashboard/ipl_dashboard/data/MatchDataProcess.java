package io.ipldashboard.ipl_dashboard.data;

import io.ipldashboard.ipl_dashboard.Model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDataProcess implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcess.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setMatchWinner(matchInput.getWinner());
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());
        // set team1 and team2 depending on the inning order.
        String firstInningTeam, secondInningTeam;

        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        } else {
            secondInningTeam = matchInput.getToss_winner();
            firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }
        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }

}
