import {React} from 'react';

const MatchSmallCard=({match}) =>{
  return (
    <div className="MatchSmallCard">
      <p>{match.team1} vs {match.team2}</p>
    </div>
  );
}

export default MatchSmallCard;
