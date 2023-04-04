import style from './CryptoLabel.module.css';
import React from 'react';
import EmptyStar from '../../images/empty_star.svg';
import Binance from '../../images/Binance.svg';
import Arrow from '../../images/arrow.svg';



function CryptoLabel(props) {

    return (
        <div className={style.label}>
            <img src={EmptyStar} alt="star-icon"></img>
            <p>BTC</p>
            <div className={style.market}>
                <img src={Binance} alt="binance-icon"></img>
                <p>Binance</p>
            </div>
            <p>$20,614.78</p>
            <img src={Arrow} alt="arrow-icon"></img>
        </div>

    );
}

export default CryptoLabel;