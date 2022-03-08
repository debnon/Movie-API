import React, {useState, useEffect} from "react";
import axios from "axios";

import MovieCards1 from "./MovieCards1";
import MovieCards2 from "./MovieCards2";
import './Movies.css';



const MovieProfiles = () => {

    const [movieProfiles, setMovieProfiles] = useState([]);

    const fetchMovieProfiles =  () => {
        const retrieveToken = JSON.parse(localStorage.getItem("token"))["jwt-token"];
        
        // axios.get("http://localhost:8080/api/v1/movie", {
        //     headers: {
        //         Authorization: 'Bearer ' + retrieveToken
        //       }
        // }).then(res => {
        //     console.log(res);

        //     // const data = res.data;
        //     setMovieProfiles(res.data);
        // });

        axios.get("http://localhost:8080/api/v1/movie/curated", {
            headers: {
                Authorization: 'Bearer ' + retrieveToken
              }
        }).then(res => {
            console.log(res);

            // const data = res.data;
            setMovieProfiles(res.data);
        });
    };

    

// similar to componentDidMount/componentDidUpdate, [] calls this effect when the movie array changes 
    useEffect(() => {
        fetchMovieProfiles();
    }, []);

    return movieProfiles.map((movieProfile, index) => {
        var weburl = "https://image.tmdb.org/t/p/original/".concat(movieProfile.poster);
        return (
            <div class="card"><img src={weburl} class="poster" /></div>
        )
    })
    
};

export default function Movielist() {
    return(
        <>
        <form class="example">
  <input type="text" placeholder="Search.." name="search"/>
  <button type="submit"><i class="fa fa-search"></i></button>
</form>
        <section>
  <h2>Top rated movies</h2>
  <div class="cards-wrapper">
  {/* <MovieProfiles /> */}
  <MovieCards1 />
  </div>
</section>

<section>
  <h2>User top picks</h2>
  <div class="cards-wrapper">
  {/* <MovieProfiles /> */}
  <MovieCards2 />
  </div>
</section>
         {/* <div className="movielist">
         <h1>Application</h1>
                 <MovieProfiles />
             </div> */}
             </>
    );
  }