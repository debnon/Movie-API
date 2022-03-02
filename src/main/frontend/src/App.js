import React, {useState, useEffect} from "react";
import axios from "axios";

import logo from './logo.svg';
import './App.css';

const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles =  () => {
        axios.get("http://localhost:8080/api/v1/admin").then(res => {
            console.log(res);
            // const data = res.data;
            setUserProfiles(res.data);
        });
    };

// similar to componentDidMount/componentDidUpdate, [] calls this effect when the user array changes 
    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return userProfiles.map((userProfile, index) => {
        return (
        <div key={index}>
            <h1>{userProfile.username}</h1>
            <p>{userProfile.id}</p>
        </div>
        )
    })
};

function App() {
  return (
      <div className="App">
          <UserProfiles />
      </div>
    
  );
}

export default App;
