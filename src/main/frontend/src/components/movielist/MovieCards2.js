import React, {useState, useEffect} from "react";
import axios from "axios";

const MovieCards2 = () => {

    const [movieProfiles, setMovieProfiles] = useState([]);

    const fetchMovieProfiles =  () => {
        const retrieveToken = JSON.parse(localStorage.getItem("token"))["jwt-token"];

        axios.get("http://localhost:8080/api/v1/movie/curated?listNumber=2", {
            headers: {
                Authorization: 'Bearer ' + retrieveToken
              }
        }).then(res => {

            setMovieProfiles(res.data);
        });
    };

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

export default MovieCards2;