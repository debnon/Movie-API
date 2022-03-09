import React, {useState, useEffect} from "react";
import ReactDOM from "react-dom";

import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Userlist from './components/userlist/Userlist';
import Layout from './components/layout/Layout';
import Login from './components/login/Login';
import Movies from './components/movielist/Movies';
import useToken from './components/useToken';

import './App.css';


export default function App() {

    const { token, setToken } = useToken();

    if(!token) {
        return <Login setToken={setToken} />
    }

    return (
        
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Movies />} />
                    <Route path="userlist" element={<Userlist />}/>
                    <Route path="movielist" element={<Movies />}/>
                </Route>
                <Route path="/login" element={<Login />}/>
            </Routes>
        </BrowserRouter>
        
        
    );
}

ReactDOM.render(<App />, document.getElementById("root"));

// validate the authorization code along with any request (i.e. when it hits the API return unauthorized)


