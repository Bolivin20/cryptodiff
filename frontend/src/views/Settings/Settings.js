import style from './Settings.module.css';
import React from 'react';
import Box from '../../components/Box/Box';
import Page from '../../components/Page/Page';
import Button from '../../components/Button/Button';


function Settings() {

    return (
        <Page>
            <div className={style.content}>
                <Box width='30%' padding='2em'>
                    <h1 className={style.userTitle}>
                        test@crypto.diff
                    </h1>
                    <Button text='Change Password' width='50%'></Button>
                    <Button text='Delete Account' width='50%'></Button>
                    <Button text='Log Out' width='50%'></Button>
                </Box>
            </div>
        </Page>
    );
}

export default Settings;