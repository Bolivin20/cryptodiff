import style from './Settings.module.css';
import React, {useEffect, useState} from 'react';
import Box from '../../components/Box/Box';
import Page from '../../components/Page/Page';
import Button from '../../components/Button/Button';
import {useNavigate} from 'react-router-dom';
import jwtDecode from "jwt-decode";


function Settings() {
    const navigate = useNavigate();
    const [userEmail, setUserEmail] = useState('');

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = jwtDecode(token);
            if (decodedToken.sub) {
                setUserEmail(decodedToken.sub);
            }
        }
    }, []);

    const handleDelete = () => {
        navigate('/delete');
    };

    const handleChangePassword = () => {
        navigate('/change');
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        window.location.pathname = '/';
    }

    return (
        <Page>
            <div className={style.content}>
                <Box width='30%' padding='2em'>
                    <h1 className={style.userTitle}>
                        Settings
                    </h1>
                    <p className={style.user}>{userEmail}</p>
                    <Button text='Change Password' width='50%' onClick={handleChangePassword}/>
                    <Button text='Delete Account' width='50%' onClick={handleDelete}></Button>
                    <Button text='Log Out' width='50%' onClick={handleLogout}></Button>
                </Box>
            </div>
        </Page>
    );
}

export default Settings;