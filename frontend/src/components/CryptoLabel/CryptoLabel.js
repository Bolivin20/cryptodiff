import React, {useState, useEffect} from 'react';
import axios from 'axios';
import style from './CryptoLabel.module.css';
import EmptyStar from '../../images/empty_star.svg';
import FilledStar from '../../images/filled_star.svg';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';
import Arrow from '../../images/Arrow.svg';
import {Link, useNavigate} from 'react-router-dom';

const markets = {
    bitstamp: Bitstamp,
    huobi: Huobi,
    binance: Binance,
};

function CryptoLabel(props) {
    const {symbol, pricesMap, buyPrices, sellPrices} = props;
    const [showPrices, setShowPrices] = useState(false);
    const [starClicked, setStarClicked] = useState(false);
    const [jwtToken, setJwtToken] = useState('');
    const navigate = useNavigate();
    const exchanges = Object.keys(pricesMap);
    const prices = Object.values(pricesMap);

    // useEffect(() => {
    //     const token = localStorage.getItem('token');
    //     if (!token) {
    //         navigate('/api/auth/authenticate');
    //     }
    //     setJwtToken(token);
    // }, []);

    const handleStarClick = () => {
        setStarClicked(!starClicked);
        const requestData = {
            symbol: symbol,
        };
        const endpoint = starClicked ? 'delete' : 'add';
        axios
            .post(`http://localhost:8080/user/api/${endpoint}`, requestData, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${jwtToken}`,
                },
            })
            .then((response) => {
                if (response.status === 200) {
                    console.log(`Symbol ${endpoint === 'add' ? 'dodany' : 'usunięty'} pomyślnie.`);
                } else {
                    console.log(`Wystąpił błąd podczas ${endpoint === 'add' ? 'dodawania' : 'usuwanie'} symbolu.`);
                    console.log(response);
                }
            })
            .catch((error) => {
                console.log('Wystąpił błąd podczas wysyłania żądania.');
                console.log(error);
            });
    };

    const capitalizeFirstLetter = (string) => {
        return `${string.charAt(0).toUpperCase()}${string.slice(1)}`;
    };

    const handleArrowClick = () => {
        setShowPrices(!showPrices);
        console.log(exchanges);
        console.log(prices);
    };

    const handleRedirection = () => {
        navigate(`/info/${symbol}`, {state: {sellPrices, buyPrices}});
    };

    return (
        <div className={style.marketContainer}>
            <div className={style.label}>
                <img
                    src={starClicked ? FilledStar : EmptyStar}
                    alt="star-icon"
                    onClick={handleStarClick}
                />
                <p onClick={handleRedirection} className={style.symbol}>{symbol.toUpperCase()}</p>
                <div className={style.market}>
                    <img src={markets[exchanges[0]]} alt="market-icon"/>
                    <p>{capitalizeFirstLetter(exchanges[0])}</p>
                </div>
                <p>${prices[0]}</p>
                <img
                    src={Arrow}
                    alt="arrow-icon"
                    className={showPrices ? style.arrowRotated : ''}
                    onClick={handleArrowClick}
                />
            </div>
            {showPrices && (
                <div>
                    {exchanges.slice(1).map((e) => (
                        <div className={style.label} key={e}>
                            <div className={style.market}>
                                <img src={markets[e]} alt="market-icon"/>
                                <p>{capitalizeFirstLetter(e)}</p>
                            </div>
                            <p>${pricesMap[e]}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default CryptoLabel;
