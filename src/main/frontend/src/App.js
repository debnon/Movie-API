import React, {useState, useEffect} from "react";
import axios from "axios";

import logo from './logo.svg';
import './App.css';

const Userprofiles = () => {

    const fetchUserProfiles =  () => {
        axios.get("http://localhost:8080/api/v1/admin").then(res => {
            console.log(res);
        });
    };

// similar to component mount, [] calls this effect when the user array changes 
    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return <h1> Hello </h1>
};

function App() {
  return (
    <div className="App">
      <Userprofiles />
    </div>
  );
}

export default App;
