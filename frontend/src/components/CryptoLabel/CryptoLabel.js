import style from './CryptoLabel.module.css';
import EmptyStar from '../../images/empty_star.svg';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';
import Arrow from '../../images/Arrow.svg';
import React, { useState } from 'react';
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
    const [showPrices, setShowPrices] = useState(false);
    const exchanges = Object.keys(pricesMap);
   // const marketIcon = provideCorrectIcon(market);
    const prices = Object.values(pricesMap);

    return (
      <div className='market-container'>
        <div className={style.label}>
            <img src={EmptyStar} alt="star-icon"/>
            <p>{symbol.toUpperCase()}</p>
            <div className={style.market}>
                <img src={provideCorrectIcon(exchanges[0])} alt="market-icon"/>
                <p>{exchanges[0]}</p>
            </div>
            <p>${prices[0]}</p>
            <img src={Arrow} alt="arrow-icon" style={{height: "2.1em"}} 
            onClick={() => { setShowPrices(!showPrices); }}/>
        </div>
          {
            showPrices && (
              <div>
                {exchanges.slice(1).map((e) => { 
                  return (
                    <div className={style.label}>
                      <p>{symbol.toUpperCase()}</p>
                      <div className={style.market}>
                        <p>{e}</p>
                      </div>
                      <p>{pricesMap[e]}</p>
                    </div>
                  );
                })}
              </div>
            )
          }
          </div>
    );
}

export default CryptoLabel;
  
  