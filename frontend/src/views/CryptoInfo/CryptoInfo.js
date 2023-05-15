import style from './CryptoInfo.module.css';
import React from 'react';
import Box from '../../components/Box/Box';
import Page from '../../components/Page/Page';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';


function Info() {

    return (
        <Page>
            <div className={style.content}>
                <Box width='40%'>
                    <h1 className={style.cryptoTitle}>BTC</h1>
                    <div className={style.label}>
                        <p>Stock Market</p>
                        <p>Buy Price</p>
                        <p>Sell Price</p>
                    </div>
                    <div className={style.label}>
                        <div className={style.market}>
                            <img src={Binance} alt="binance-icon"></img>
                            <p>Binance</p>
                        </div>
                        <p>$20,614.78</p>
                        <p>$20,614.78</p>
                    </div>
                    <div className={style.label}>
                        <div className={style.market}>
                            <img src={Huobi} alt="binance-icon"></img>
                            <p>Binance</p>
                        </div>
                        <p>$20,614.78</p>
                        <p>$20,614.78</p>
                    </div>
                    <div className={style.label}>
                        <div className={style.market}>
                            <img src={Bitstamp} alt="binance-icon"></img>
                            <p>Binance</p>
                        </div>
                        <p>$20,614.78</p>
                        <p>$20,614.78</p>
                    </div>
                </Box>
                <Box width='40%'>
                    Wykres
                </Box>
            </div>
        </Page>
    );
}

export default Info;