import style from './Start.module.css';
import Input from '../../components/Input/Input';
import Box from '../../components/Box/Box';
import Lupa from '../../images/lupa.svg';
import EmptyStar from '../../images/empty_star.svg';
import Page from '../../components/Page/Page';
import CryptoLabel from '../../components/CryptoLabel/CryptoLabel';
import Arrow from '../../images/Arrow.svg';
import React, {useEffect, useState} from 'react';
import axios from 'axios';

function Start() {
    const [cryptoData, setCryptoData] = useState([]);
    const [allData, setAllData] = useState([]);
    const [buyData, setBuyData] = useState([]);
    const [sellData, setSellData] = useState([]);
    const [selectedOption, setSelectedOption] = useState('buy');
    const [searchValue, setSearchValue] = useState('');
    const [loading, setLoading] = useState(true);

    const handleSearch = (event) => {
        const value = event.target.value;
        setSearchValue(value);
    };

    useEffect(() => {
        async function fetchData() {
            try {
                const buyResponse = await axios.get('http://localhost:8080/api/prices/asc');
                const sellResponse = await axios.get('http://localhost:8080/api/prices/desc');
                const buyData = buyResponse.data;
                const sellData = sellResponse.data;
                setBuyData(buyData);
                setSellData(sellData);
                setCryptoData(selectedOption === 'buy' ? buyData : sellData);
                setAllData(selectedOption === 'buy' ? buyData : sellData);
                setLoading(false);
            } catch (error) {
                console.log('Wystąpił błąd podczas pobierania danych.');
                console.log(error);
            }
        }

        fetchData();
    }, []);

    useEffect(() => {
        const filteredData = allData.filter((item) =>
            item.symbol.toLowerCase().includes(searchValue.toLowerCase())
        );
        setCryptoData(filteredData);
    }, [searchValue, allData]);

    function handleBuyButtonClick() {
        setSelectedOption('buy');
        setCryptoData(buyData);
        setSearchValue('');
    }

    function handleSellButtonClick() {
        setSelectedOption('sell');
        setCryptoData(sellData);
        setSearchValue('');
    }

    return (
        <Page>
            <div className={style.searchBar}>
                <div className={style.category}>
                    <p onClick={handleBuyButtonClick}>Buy</p>
                    <hr style={{visibility: selectedOption === 'buy' ? 'visible' : 'hidden'}}/>
                </div>
                <div className={style.category}>
                    <p onClick={handleSellButtonClick}>Sell</p>
                    <hr style={{visibility: selectedOption === 'sell' ? 'visible' : 'hidden'}}/>
                </div>
                <Input
                    placeholder='search...'
                    type='text'
                    value={searchValue}
                    inputIcon={Lupa}
                    onChange={handleSearch}
                    width='30%'
                />
            </div>
            <div className={style.content}>
                <Box>
                    <div className={style.tableLable}>
                        <img src={EmptyStar} alt="star-icon"/>
                        <p>Cryptocurrency</p>
                        <p>Stock Market</p>
                        <p>Buy Price</p>
                        <img src={Arrow} alt="arrow-icon" style={{visibility: 'hidden', height: "2.1em"}}/>
                    </div>
                    {loading ? (
                        <div className={style.loading}>Loading...</div>
                    ) : (
                        cryptoData.map((crypto) => {

                            return (
                                <CryptoLabel
                                    key={crypto.symbol}
                                    symbol={crypto.symbol}
                                    pricesMap={crypto.pricesMap}
                                />
                            );
                        })
                    )}
                </Box>
            </div>
        </Page>
    );
}

export default Start;
