import React, {useState, useEffect} from "react";
import ReactDOM from "react-dom";

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Dashboard from './components/dashboard/Dashboard';
import Preferences from './components/preferences/Preferences';
import Userlist from './components/userlist/Userlist';
import Layout from './components/layout/Layout';
import Home from './components/Home';
import Login from './components/login/Login';

import './App.css';



export default function App() {

    const [token, setToken] = useState();

    if(!token) {
        return <Login setToken={setToken} />
    }

    return (
        
        
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Home />} />
                    <Route path="dashboard" element={<Dashboard />}/>
                    <Route path="preferences" element={<Preferences />}/>
                    <Route path="userlist" element={<Userlist />}/>
                </Route>
            </Routes>
        </BrowserRouter>
        
        
    );
}

ReactDOM.render(<App />, document.getElementById("root"));
// export default App;
