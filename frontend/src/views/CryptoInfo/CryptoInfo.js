import style from './CryptoInfo.module.css';
import React from 'react';
import Box from '../../components/Box/Box';
import Page from '../../components/Page/Page';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';
import {useParams, useLocation} from 'react-router-dom';


function Info() {

    const markets = {
        bitstamp: Bitstamp,
        huobi: Huobi,
        binance: Binance,
    };

    const {id} = useParams();
    const location = useLocation();
    const {sellPrices, buyPrices} = location.state;

    function groupData(exchanges, buyPrices, sellPrices, buyExchanges) {
        const groupedArray = [];
        for (let i = 0; i < exchanges.length; i++) {
            let index = buyExchanges.indexOf(exchanges[i]);
            groupedArray.push({mp: exchanges[i], buyPrice: buyPrices[i], sellPrice: sellPrices[index]});
        }
        return groupedArray;
    }

    const correctData = groupData(Object.keys(buyPrices), Object.values(buyPrices), Object.values(sellPrices), Object.keys(sellPrices));
    console.log(correctData);

    const capitalizeFirstLetter = (string) => {
        return `${string.charAt(0).toUpperCase()}${string.slice(1)}`;
    };

    return (
        <Page>
            <div className={style.content}>
                <Box width='40%'>
                    <h1 className={style.cryptoTitle}>{id.toUpperCase()}</h1>
                    <div className={style.label}>
                        <p>Stock Market</p>
                        <p>Buy Price</p>
                        <p>Sell Price</p>
                    </div>
                    {
                        correctData.map((crypto) => {
                            return (
                                <div className={style.label}>
                                    <div className={style.market}>
                                        <img src={markets[crypto.mp]} alt="market-icon"></img>
                                        <p>{capitalizeFirstLetter(crypto.mp)}</p>
                                    </div>
                                    <p>${crypto.buyPrice}</p>
                                    <p>${crypto.sellPrice}</p>
                                </div>
                            );
                        })
                    }
                </Box>
                <Box width='40%'>
                    Wykres
                </Box>
            </div>
        </Page>
    );
}

export default Info;