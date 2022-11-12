import {React} from 'react';
import TeamPage from '../pages/TeamPage';

const MatchDetailCard=({match}) =>{
    if(!match)return null;
    return (
      <div className="MatchDetailCard">
        <h1>Latest Matches</h1>
        <h4>Match Details</h4>
        <h2>{match[0].team1} vs {match[0].team2}</h2>

      </div>
    );
  }
  
  export default MatchDetailCard;
  