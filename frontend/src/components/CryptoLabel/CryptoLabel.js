import style from './CryptoLabel.module.css';
import React from 'react';
import EmptyStar from '../../images/empty_star.svg';
import Binance from '../../images/Binance.svg';
import Arrow from '../../images/arrow.svg';



function CryptoLabel(props) {
    const { symbol, pricesMap } = props;
    const [market, price] = Object.entries(pricesMap)[0];
    
    return (
      <div className={style.label}>
        <img src={EmptyStar} alt="star-icon" />
        <p>{symbol.toUpperCase()}</p>
        <div className={style.market}>
          <p>{market}</p>
        </div>
        <p>${price.toFixed(2)}</p>
        <img src={Arrow} alt="arrow-icon" />
      </div>
    );
  }
  
  export default CryptoLabel;
  
  