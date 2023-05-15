import style from './CryptoLabel.module.css';
import React from 'react';
import EmptyStar from '../../images/empty_star.svg';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';
import Arrow from '../../images/Arrow.svg';

function provideCorrectIcon(market) {
    if (market === "bitstamp") {
        return Bitstamp;
    } else if (market === "huobi") {
        return Huobi;
    } else if (market === "binance") {
        return Binance;
    }
}


function CryptoLabel(props) {
    const {symbol, pricesMap} = props;
    const [market, price] = Object.entries(pricesMap)[0];
    const capitalized = market.charAt(0).toUpperCase() + market.slice(1);
    const marketIcon = provideCorrectIcon(market);


    return (
        <div className={style.label}>
            <img src={EmptyStar} alt="star-icon"/>
            <p>{symbol.toUpperCase()}</p>
            <div className={style.market}>
                <img src={marketIcon} alt="market-icon"/>
                <p>{capitalized}</p>
            </div>
            <p>${price}</p>
            <img src={Arrow} alt="arrow-icon" style={{height: "2.1em"}}/>
        </div>
    );
}

export default CryptoLabel;
  
  