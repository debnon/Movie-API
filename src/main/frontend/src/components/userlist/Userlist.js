import React, {useState, useEffect} from "react";
import axios from "axios";



const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles =  () => {
        const retrieveToken = JSON.parse(localStorage.getItem("token"))["jwt-token"];

        axios.get("http://localhost:8080/api/v1/admin/user", {
            headers: {
                Authorization: 'Bearer ' + retrieveToken
              }
        }).then(res => {
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
            <pre><h1>{userProfile.id}: {userProfile.lastname}, {userProfile.firstname}</h1></pre>
            

            <p>Username: {userProfile.username}</p>
            <p>Contact Number: {userProfile .contactnumber}</p>
        </div>
        )
    })
};

export default function Userlist() {
    return(
        <div className="Userlist">
        <h1>Application</h1>
                <UserProfiles />
            </div>
    );
  }