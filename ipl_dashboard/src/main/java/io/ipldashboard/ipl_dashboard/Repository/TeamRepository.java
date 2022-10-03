package io.ipldashboard.ipl_dashboard.Repository;

import io.ipldashboard.ipl_dashboard.Model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>{

    Team findByTeamName(String teamName);
}
