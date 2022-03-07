import React, {useState, useEffect} from "react";
import axios from "axios";
import './Movies.css';



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
        var weburl = "https://image.tmdb.org/t/p/original/".concat(movieProfile.poster);
        return (
        <div key={index}>
            <pre><h1>{movieProfile.id}: {movieProfile.description}, {movieProfile.releaseDate}</h1></pre>
            
            {/* <img src={weburl}
alt="Movie!" className="imghandle"/> */}
<div class="hero-container">
		<div class="main-container">
			<div class="poster-container">
				<a href="#"><img src={weburl} class="poster" /></a>
			</div>
			<div class="ticket-container">
				<div class="ticket__content">
					<h4 class="ticket__movie-title">{movieProfile.title}</h4>
					<p class="ticket__movie-slogan">
						More human than human is our motto.
					</p>
					{/* <p class="ticket__current-price">$28.00</p>
					<p class="ticket__old-price">$44.99</p>
					<button class="ticket__buy-btn">Buy now</button> */}
				</div>
			</div>
		</div>

		
	</div>
            
            <p>Rating: {movieProfile.rating}</p>
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