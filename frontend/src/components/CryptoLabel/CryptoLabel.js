import style from './CryptoLabel.module.css';
import EmptyStar from '../../images/empty_star.svg';
import FilledStar from '../../images/filled_star.svg';
import Binance from '../../images/Binance.svg';
import Huobi from '../../images/Huobi.svg';
import Bitstamp from '../../images/Bitstamp.svg';
import Arrow from '../../images/Arrow.svg';
import React, {useState, useEffect} from 'react';
import {useNavigate} from 'react-router-dom';


function provideCorrectIcon(market) {
    if (market === 'bitstamp') {
        return Bitstamp;
    } else if (market === 'huobi') {
        return Huobi;
    } else if (market === 'binance') {
        return Binance;
    }
}

function CryptoLabel(props) {
    const {symbol, pricesMap} = props;
    const [showPrices, setShowPrices] = useState(false);
    const [starClicked, setStarClicked] = useState(false);
    const exchanges = Object.keys(pricesMap);
    const prices = Object.values(pricesMap);
    const [jwtToken, setJwtToken] = useState('');
    const navigate = useNavigate();

    // useEffect(() => {
    //     const token = localStorage.getItem('token');
    //     if (!token) {
    //         navigate('/api/auth/authenticate');
    //     }
    //     setJwtToken(token);
    // }, []);

    const handleStarClick = () => {
        setStarClicked(!starClicked);
        if (!starClicked) {
            const requestData = {
                symbol: symbol,
            };
            fetch('http://localhost:8080/user/api/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${jwtToken}`,
                },
                body: JSON.stringify(requestData),
            })
                .then((response) => {
                    if (response.ok) {
                        console.log('Symbol dodany pomyślnie.');
                    } else {
                        console.log('Wystąpił błąd podczas dodawania symbolu.');
                        console.log(response);
                    }
                })
                .catch((error) => {
                    console.log('Wystąpił błąd podczas wysyłania żądania.');
                });
        } else {
            fetch('http://localhost:8080/user/api/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${jwtToken}`,
                },
                body: JSON.stringify({symbol: symbol}),
            })
                .then((response) => {
                    if (response.ok) {
                        console.log('Subskrypcja usunięta pomyślnie.');
                    } else {
                        console.log('Wystąpił błąd podczas usuwania subskrypcji.');
                        console.log(symbol);
                    }
                })
                .catch((error) => {
                    console.log('Wystąpił błąd podczas wysyłania żądania.');
                    console.log(error);
                });
        }
    };

    const capitalizeFirstLetter = (string) => {
        return `${string.charAt(0).toUpperCase()}${string.slice(1)}`;
    };

    return (
        <div className={style.marketContainer}>
            <div className={style.label}>
                <img
                    src={starClicked ? FilledStar : EmptyStar}
                    alt="star-icon"
                    onClick={handleStarClick}
                />
                <p>{symbol.toUpperCase()}</p>
                <div className={style.market}>
                    <img src={provideCorrectIcon(exchanges[0])} alt="market-icon"/>
                    <p>{capitalizeFirstLetter(exchanges[0])}</p>
                </div>
                <p>${prices[0]}</p>
                <img
                    src={Arrow}
                    alt="arrow-icon"
                    style={{height: '2.1em', gridColumnStart:6, gridColumnEnd: 7}}
                    onClick={() => {
                        setShowPrices(!showPrices);
                    }}
                />
            </div>
            {showPrices && (
                <div>
                    {exchanges.slice(1).map((e) => (
                        <div className={style.extraLabel}>
                            <div className={style.market}>
                                <img src={provideCorrectIcon(e)} alt="market-icon"/>
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

