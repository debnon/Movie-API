import React from 'react';
import './Popup.css';

const Popup = () => {
    const popup = document.querySelector('.popup');
    console.log("yo"); 
    function showPopup() {
        console.log("yo");  
         popup.classList.add('open');
    }
    function hidePopup() {
        console.log("no");    
        popup.classList.remove('open');
    }

    
    return (
        <>
        <button onClick={showPopup}>
        Open Popup
      </button>
      <div class="popup">
        <div class="blocker" onClick={hidePopup}></div>
        <div class="contents">
          This is popup
        </div>
      </div>
      </>
    )
}

export default Popup;