import style from './NavBar.module.css';
import Logo from '../Logo/Logo';
import React from 'react';


function NavBar() {

    return (
        <div className={style.menuBar}>
            <Logo fontSize='2em' height='50em' />
            <div className={style.tabs}>
                <p>Ranking</p>
                <p>test@crypto.diff</p>
                <p>Log Out</p>
            </div>
        </div>
    );
}

export default NavBar;