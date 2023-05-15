import style from './Start.module.css';
import Input from '../../components/Input/Input';
import Box from '../../components/Box/Box';
import Lupa from '../../images/lupa.svg';
import EmptyStar from '../../images/empty_star.svg';
import Page from '../../components/Page/Page';
import CryptoLabel from '../../components/CryptoLabel/CryptoLabel';
import Arrow from '../../images/arrow.svg';
import React, { useEffect, useState } from 'react';

function Start() {
  const [cryptoData, setCryptoData] = useState([]);
  const [selectedOption, setSelectedOption] = useState('asc');

  const handleSearch = async (event) => {
      const response = await fetch(`http://localhost:8080/api/prices/${selectedOption === 'asc' ? 'asc' : 'desc'}`);
      const data = await response.json();
      const filteredData = data.filter(item => item.symbol.toLowerCase().includes(event.target.value));
      setCryptoData(filteredData);
      console.log(filteredData);
    };

  useEffect(() => {
    async function fetchCryptoData() {
      const response = await fetch(`http://localhost:8080/api/prices/${selectedOption === 'asc' ? 'asc' : 'desc'}`);
      const data = await response.json();
      setCryptoData(data);
      console.log(data);
    }

    fetchCryptoData();
  }, [selectedOption]);

  function handleBuyButtonClick() {
    setSelectedOption('asc');
  }

  function handleSellButtonClick() {
    setSelectedOption('desc');
  }

  return (
    <Page>
      <div className={style.searchBar}>
        <div className={style.category}>
          <p onClick={handleBuyButtonClick}>Buy</p>
          <hr />
        </div>
        <div className={style.category}>
          <p onClick={handleSellButtonClick}>Sell</p>
          <hr />
        </div>
        <Input placeholder='search...' type='text' inputIcon={Lupa} onChange={handleSearch} width='30%'></Input>
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
          <div>
            {cryptoData.map((crypto, index) => (
              <CryptoLabel
                key={index}
                symbol={crypto.symbol}
                pricesMap={crypto.pricesMap}
              />
            ))}
          </div>
        </Box>
      </div>
    </Page>
  );
}

export default Start;
