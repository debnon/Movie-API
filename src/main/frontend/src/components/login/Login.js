import React, { useState } from 'react';
import PropTypes from 'prop-types';
import logo from '../../graphics/logo2.png';
import Register from './Register';



import './Login.css';
// import './Register.css';

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

    // handle post request with email/username and password
    // return and save JWT
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

    const [isShowRegister, setIsShowRegister] = useState(true);

    const handleRegisterClick = () => {
        setIsShowRegister((isShowRegister) => !isShowRegister);
    };

    return (
        <div>
            {/* <NavBar handleRegisterClick={handleRegisterClick} /> */}
                
    
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
                <a onClick={handleRegisterClick} class="pca">Create New Account</a>
                
            </div>
            </form>
            <Register isShowRegister={isShowRegister} />
    </div>
    )
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
}