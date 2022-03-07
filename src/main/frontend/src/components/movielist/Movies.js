import React, {useState, useEffect} from "react";
import axios from "axios";



const MovieProfiles = () => {

    const [movieProfiles, setMovieProfiles] = useState([]);

    const fetchMovieProfiles =  () => {
        const retrieveToken = JSON.parse(localStorage.getItem("token"))["jwt-token"];
        
        axios.get("http://localhost:8080/api/v1/movie", {
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
        return (
        <div key={index}>
            <pre><h1>{movieProfile.id}: {movieProfile.description}, {movieProfile.releaseDate}</h1></pre>
            <p>Movie name: {movieProfile.title}</p>
            <p>Contact Number: {movieProfile.rating}</p>
        </div>
        )
    })
};

export default function Movielist() {
    return(
        <div className="movielist">
        <h1>Application</h1>
                <MovieProfiles />
            </div>
    );
  }