package io.ipldashboard.ipl_dashboard.data;
import io.ipldashboard.ipl_dashboard.Model.Match;
import io.ipldashboard.ipl_dashboard.Model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    //entitymanager is basically JPA way of interacting with the database.

    private final EntityManager em;

   // private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
             // team name and team instances purpose of doing this to get the unique team from each of the row.
            Map<String, Team>teamdata=new HashMap<>();
            //jpql allow to write the query
            em.createQuery("select distinct m.team1, count(*) from Match m group by m.team1",Object[].class)
                    .getResultList()
                    .stream()
                    .map(e->new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamdata.put(team.getTeamName(), team));
            em.createQuery("select distinct m.team2, count(*) from Match m group by m.team2",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team=teamdata.get( (String) e[0]);
                        team.setTotalMatch(team.getTotalMatch() + (long) e[1]);
                    });

            em.createQuery("select distinct m.MatchWinner, count(*) from Match m group by m.MatchWinner",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team=teamdata.get( (String) e[0]);
                        if(team!=null) team.setTotalWin((long)e[1]);
                    });
            teamdata.values().forEach(team -> em.persist(team));
            teamdata.values().forEach(team->System.out.println(team.getTeamName() + " Total Wins "+team.getTotalWin() ));
//            jdbcTemplate.query("SELECT team1, team2, date FROM match",
//                    (rs, row) -> "team 1 "+rs.getString(1)+ " Team2 "+ rs.getString(2)+" Date "+rs.getString(3)
//            ).forEach(str->System.out.println(str));
        }
    }
}