import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Outlet, Link } from "react-router-dom";
import './Layout.css';



// const Layout = () => {
//   return (
//     <>
//       <nav>
//         <ul>
//           <li>
//             <Link to="/">Home</Link>
//           </li>
//           <li>
//             <Link to="/dashboard">Dashboard</Link>
//           </li>
//           <li>
//             <Link to="/preferences">Preferences</Link>
//           </li>
//           <li>
//             <Link to="/userlist">Userlist</Link>
//           </li>
//         </ul>
//       </nav>

//       <Outlet />
//     </>
//   )
// };

// export default Layout;

function Layout() {
const [click, setClick] = useState(false);
const [button, setButton] = useState(true);

const handleClick = () => setClick(!click);
const closeMobileMenu = () => setClick(false);

const showButton = () => {
    if (window.innerWidth <= 960) {
    setButton(false);
    } else {
    setButton(true);
    }
};

useEffect(() => {
    showButton();
}, []);

window.addEventListener('resize', showButton);

return (
    <>
    <nav className='navbar'>
        <div className='navbar-container'>
        <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            Movie Maverick
            <i class='fab fa-typo3' />
        </Link>
        <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
        </div>
        <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
            <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Home
            </Link>
            </li>
            <li className='nav-item'>
            <Link
                to='/services'
                className='nav-links'
                onClick={closeMobileMenu}
            >
                Services
            </Link>
            </li>
            <li className='nav-item'>
            <Link
                to='/products'
                className='nav-links'
                onClick={closeMobileMenu}
            >
                Products
            </Link>
            </li>

            <li>
            <Link
                to='/sign-up'
                className='nav-links-mobile'
                onClick={closeMobileMenu}
            >
                Sign Up
            </Link>
            </li>
        </ul>
        {button && <Button buttonStyle='btn--outline'>SIGN UP</Button>}
        </div>
    </nav>
    </>
);
}

export default Layout;
  

  