import React, { useState } from 'react';
import PropTypes from 'prop-types';
import logo from '../../graphics/logo2.png';


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
        
        if (token["jwt-token"]) {
            setToken(token);
        } else {
            console.log("Invalid login");
        }
        
    }

    
    // return(
    // <div className="login-wrapper">
    //     <h1>Please Log In</h1>
    //     <form onSubmit={handleSubmit}>
    //     <label>
    //         <p>Username</p>
    //         <input type="text" onChange={e => setUserName(e.target.value)}/>
    //     </label>
    //     <label>
    //         <p>Password</p>
    //         <input type="password" onChange={e => setPassword(e.target.value)}/>
    //     </label>
    //     <div>
    //         <button type="submit">Submit</button>
    //     </div>
    //     </form>
        
            
        
    // </div>

    
    // )

    return (
        <div>
    
    <div class="h1">
    <img src={logo} alt="M logo" />
    <h1 class="h">movie maverick</h1>
    <p class="pr">Maverick helps you find the movies you were always looking for</p>
    </div>  
            <form class="main" onSubmit={handleSubmit}>
            <input type="text" placeholder="Email address or phone number" class="txt" onChange={e => setUserName(e.target.value)}/><br/>
            <input type="password" placeholder="Password" class="txt" onChange={e => setPassword(e.target.value)}/><br/>
            <input type="submit" value="Log In" class="login-btn"/><br/>
            <div class="a-link">
            <a href="" class="link">Forgotten Password?</a>
            </div>
            <div class="ca">
                <a href="" class="pca">Create New Account</a>
            </div>
            </form>
    </div>
    )
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
}