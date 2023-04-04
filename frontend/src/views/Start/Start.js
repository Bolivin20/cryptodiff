import style from './Start.module.css';
import Input from '../../components/Input/Input';
import Box from '../../components/Box/Box';
import Lupa from '../../images/lupa.svg';
import EmptyStar from '../../images/empty_star.svg';
import React from 'react';
import Page from '../../components/Page/Page';
import CryptLabel from '../../components/CryptoLabel/CryptoLabel';
import Arrow from '../../images/arrow.svg';


function Start() {

    return (
        <Page>
            <div className={style.searchBar}>
                <div className={style.category}>
                    <p>Buy</p>
                    <hr />
                </div>
                <div className={style.category}>
                    <p>Sell</p>
                    <hr />
                </div>
                <Input placeholder='search...' type='text' inputIcon={Lupa} width='30%'></Input>
            </div>
            <div className={style.content}>
                <Box>
                    <div className={style.tableLable}>
                        <img src={EmptyStar} alt="star-icon"></img>
                        <p>Cryptocurrency</p>
                        <p>Stock Market</p>
                        <p>Buy Price</p>
                        <img src={Arrow} alt="arrow-icon" style={{ visibility: 'hidden' }}></img>
                    </div>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                    <CryptLabel></CryptLabel>
                </Box>
            </div>
        </Page>
    );
}

export default Start;