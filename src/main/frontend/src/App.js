import React, {useState, useEffect} from "react";
import ReactDOM from "react-dom";
import axios from "axios";

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Dashboard from './components/dashboard/Dashboard';
import Preferences from './components/preferences/Preferences';
import Userlist from './components/userlist/Userlist';
import Layout from './components/Layout';
import Home from './components/Home';

import './App.css';



export default function App() {
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
