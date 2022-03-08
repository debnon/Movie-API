import React, {useState, useEffect} from "react";
import axios from "axios";
import "./Userlist.css";



const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);


    const retrieveToken = JSON.parse(localStorage.getItem("token"))["jwt-token"];
    const fetchUserProfiles =  () => {
    axios.get("http://localhost:8080/api/v1/admin/user", {
                headers: {
                    Authorization: 'Bearer ' + retrieveToken
                  }
            }).then(res => {
                console.log(res);
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
            <table>
                {/* <tr>
                    <th>id</th>
                    <th>firstname</th>
                    <th>lastname</th>
                    <th>username</th>
                    <th>contactnumber</th>
                </tr> */}
                <tr>
                    <td width="10%">{userProfile.id}</td>
                    <td width="10%">{userProfile.firstname}</td>
                    <td width="10%">{userProfile.lastname}</td>
                    <td width="10%">{userProfile.username}</td>
                    <td width="10%">{userProfile.contactnumber}</td>
                    {/* <td><button>Update</button>&nbsp;&nbsp;&nbsp;<button>Delete</button></td> */}
                </tr>
            {/* <pre><h1>{userProfile.id}: {userProfile.lastname}, {userProfile.firstname}</h1></pre>
            <p>Username: {userProfile.username}</p>
            <p>Contact Number: {userProfile .contactnumber}</p> */}
            </table>
        </div>
        )
    })
};

export default function Userlist() {
    return(
        <div className="Userlist">
        <h1 align = "center">Application</h1><br></br>

        <h3>Users List</h3>
        <table>
        <tr>
                    <th width="10%">id</th>
                    <th width="10%">firstname</th>
                    <th width="10%">lastname</th>
                    <th width="10%">username</th>
                    <th width="10%">contactnumber</th>
                    {/* <th>Edit</th> */}
                </tr> 
                </table>
                <UserProfiles />
            </div>
    );
  }