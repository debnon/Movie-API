import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Outlet, Link } from "react-router-dom";
import './Layout.css';
import logo from '../../graphics/wheel4.svg';
import useToken from '../useToken';

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

    // removing JWT on signout
    const { token, setToken } = useToken();

    const removeToken = userToken => {
        localStorage.removeItem('token');
        setToken(null);
        window.location.reload(false)
      };

    return (
        <>
        <nav className='navbar'>
            <div className='navbar-container'>
            <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
                <img src={logo} alt="M logo" />
                <i className='fab fa-typo3' />
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
                    to='/dashboard'
                    className='nav-links'
                    onClick={closeMobileMenu}
                >
                    Info
                </Link>
                </li>
                <li className='nav-item'>
                <Link
                    to='/userlist'
                    className='nav-links'
                    onClick={closeMobileMenu}
                >
                    Userlist
                </Link>
                </li>

            </ul>
            <Link to='/'>
            {button && <Button buttonStyle='btn--outline'  onClick={removeToken}>SIGN OUT</Button>}
            </Link>
            </div>
        </nav>
        <Outlet />
        </>
    );
}

export default Layout;
  

  