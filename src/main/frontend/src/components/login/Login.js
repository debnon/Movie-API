import React, { useState } from 'react';
import PropTypes from 'prop-types';


import './Login.css';

// const fetchUserToken =  () => {
//     axios.post("http://localhost:8080/api/v1/user/authenticate").then(res => {
//         console.log(res);
//         // const data = res.data;
//         setUserProfiles(res.data);
//     });
// };
async function loginUser(credentials) {
    return fetch('http://localhost:8080/api/v1/user/authenticate', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}

export default function Login({ setToken }) {

    const [username, setUserName] = useState();
    const [password, setPassword] = useState();

    const handleSubmit = async e => {
        e.preventDefault();
        const token = await loginUser({
            "emailID": username,
            "password": password
        });
        console.log(token);
        if (token["jwt-token"]) {
            setToken(token);
        } else {
            console.log("Invalid login");
        }
        
    }

    return(
    <div className="login-wrapper">
        <h1>Please Log In</h1>
        <form onSubmit={handleSubmit}>
        <label>
            <p>Username</p>
            <input type="text" onChange={e => setUserName(e.target.value)}/>
        </label>
        <label>
            <p>Password</p>
            <input type="password" onChange={e => setPassword(e.target.value)}/>
        </label>
        <div>
            <button type="submit">Submit</button>
        </div>
        </form>
    </div>
    )
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
}