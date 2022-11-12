import { React, useEffect, useState } from 'react';
import MatchDetailCard from '../components/MatchDetailCard';
import MatchSmallCard from '../components/MatchSmallCard';

const TeamPage = () => {

    const[team, setTeam]=useState({matchs:[]});

     useEffect(
        // creating a function which basically calls to the rest api.
        () =>{
          const fetchmacthes=async()=>{
                // fetch returns promis that's why we have to use the await.
               const response=await fetch('http://localhost:8080/team/Rajasthan%20Royals');
               const data=await response.json();
            //    console.log(data);
            setTeam(data);
          };
         fetchmacthes();

        },[]
        // we have used empty array just because to call the fetchmatches functiom only first time when the 
        // pages loaded.
     );

    return (
        <div className="TeamPage">
            <h1>{team.teamName}</h1>
            <MatchDetailCard match={team.matches}></MatchDetailCard>
           {team.matches?.map(match => <MatchSmallCard match={match}/>)} 
        </div>
    );
}

export default TeamPage;
