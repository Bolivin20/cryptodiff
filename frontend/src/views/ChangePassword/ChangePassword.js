import style from './ChangePassword.module.css';
import React from 'react';
import Box from '../../components/Box/Box';
import Page from '../../components/Page/Page';
import Button from '../../components/Button/Button';
import Input from '../../components/Input/Input';
import Padlock from '../../images/padlock.svg';


function Change() {

    return (
        <Page>
            <div className={style.content}>
                <Box width='30%' padding='2em'>
                    <div className={style.align}>
                        <Input placeholder="••••••••••••" type="password" title="Current Password" inputIcon={Padlock} />
                        <Input placeholder="••••••••••••" type="password" title="New Password" inputIcon={Padlock} />
                        <Input placeholder="••••••••••••" type="password" title="Repeate Password" inputIcon={Padlock} />
                        <Button text='Change Password' width='60%'></Button>
                    </div>
                </Box>
            </div>
        </Page>
    );
}

export default Change;