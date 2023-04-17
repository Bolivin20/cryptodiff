import style from '../Login/Login.module.css';
import Logo from '../../components/Logo/Logo';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import Letter from '../../images/letter.svg';
import Padlock from '../../images/padlock.svg';
import Google from '../../images/google.svg';
import { Link } from 'react-router-dom';
import React from 'react';


function Signup() {

    return (
        <div className={style.app}>
            <section className={style.bgImage}>
                <Link className={style.logoLink} to='/'><Logo /></Link>
                <div className={style.description}>
                    Check for the best offers from three biggest
                    crypto stock markets.
                </div>
            </section>
            <section className={style.sidePanel}>
                <h1>Sign Up</h1>
                <Input placeholder="test@crypto.diff" type="text" title="Email Address" inputIcon={Letter} />
                <Input placeholder="••••••••••••" type="password" title="Password" inputIcon={Padlock} />
                <Input placeholder="••••••••••••" type="password" title="Repeate Password" inputIcon={Padlock} />
                <Button text='Sign Up'></Button>
                <p className={style.buttonText}>Already have an account ? <Link to='/login' className={style.link}>Sign in</Link></p>
                <div className={style.orBlock}>
                    <hr></hr>
                    Or
                    <hr></hr>
                </div>
                <div className={style.googleButton}>
                    <img src={Google} alt="letter icon"></img>
                    Log in with Google
                </div>
            </section>
        </div>
    );
}

export default Signup;