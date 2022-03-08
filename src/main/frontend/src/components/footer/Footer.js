import React from 'react';

import logo from "../../graphics/tmdb.svg";
import movieroll from "../../graphics/wheel3.svg";
import './Footer.css';


const Footer = () => {
    return (
    <footer class="footer">
    <div class="footer_addr">
      
          
      <h2>Contact</h2>
      
      <address>
        {/* 5534 Somewhere In. The World 22193-10212<br/> */}
            
        <a class="footer_btn" href="mailto:inquiries@movieroll.co.uk">Email Us</a>
      </address>
    </div>
    
    <ul class="footer_nav">
      
      
      <li class="nav_item nav_item--extra">
        
        
        <img class="movie_logo"src={movieroll} alt="M logo" />
      </li>
      
      
        
        <img class="tmdb_logo"src={logo} alt="M logo" />
      
    </ul>
    
    

    <div class="legal">
      <p>&copy; 2022 - All rights reserved.</p>
      <div class="legal_links">
        <span>Made with <span class="heart">♥</span> (and Spring Boot / React)</span>
      </div>
      
    </div>
    
  </footer>)
  };
  
  export default Footer;
  