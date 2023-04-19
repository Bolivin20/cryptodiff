import style from './NavBar.module.css';
import Logo from '../Logo/Logo';
import React from 'react';
import { Link } from 'react-router-dom';


function NavBar() {

    return (
        <div className={style.menuBar}>
            <Link className={style.logoLink} to='/'><Logo fontSize='2em' height='50em' /></Link>
            <div className={style.tabs}>
            <Link className={style.logoLink} to='/'><p>Ranking</p></Link>
            <Link className={style.logoLink} to='/settings'><p>test@crypto.diff</p></Link>
                <p>Log Out</p>
            </div>
        </div>
    );
}

export default NavBar;