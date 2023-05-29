import style from './NavBar.module.css';
import Logo from '../Logo/Logo';
import React from 'react';
import {Link, useLocation} from 'react-router-dom';
import Button from "../Button/Button";

function handleLogout() {
    localStorage.removeItem('token');
    window.location.pathname = '/';
}

function NavBar() {
    const location = useLocation();
    const isRankingPage = location.pathname === '/';
    const isSettingsPage = location.pathname === '/settings';
    const isLoggedIn = localStorage.getItem('token'); // Check if JWT token exists

    return (
        <div className={style.menuBar}>
            <Link className={style.logoLink} to='/'>
                <Logo fontSize='2em' height='50em'/>
            </Link>
            <div className={style.tabs}>
                <Link className={`${style.logoLink} ${isRankingPage ? style.activeLink : ''}`} to='/'>
                    <p>Ranking</p>
                </Link>
                {isLoggedIn && (
                    <Link className={`${style.logoLink} ${isSettingsPage ? style.activeLink : ''}`} to='/settings'>
                        <p>Settings</p>
                    </Link>
                )}
                {isLoggedIn ? (
                    <Button onClick={handleLogout} text='Log Out' width='100%' marginTop='0em'/>
                ) : (
                    <Link className={style.logoLink} to='/api/auth/authenticate'>
                        <Button text='Sign In' width='100%' marginTop='0em'/>
                    </Link>
                )}
            </div>
        </div>
    );
}

export default NavBar;
