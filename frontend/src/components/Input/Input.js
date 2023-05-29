import style from './Input.module.css';
import React, {useState} from 'react';
import Eye from '../../images/eye.svg';

function Input(props) {
    const {placeholder, type, title, inputIcon, width, value, onChange} = props;
    const [passwordVisible, setPasswordVisible] = useState(false);

    const togglePasswordVisibility = () => {
        setPasswordVisible((prevVisible) => !prevVisible);
    };

    let secondIcon;
    if (type === 'password') {
        const eyeIcon = Eye;
        secondIcon = (
            <img
                className={style.eyeIcon}
                src={eyeIcon}
                alt="eye icon"
                onClick={togglePasswordVisibility}
            />
        );
    }

    const inputContainerStyle = type === 'password' ? { justifyContent: 'space-evenly' } : {};

    return (
        <div style={{width: width}}>
            <p className={style.inputTitle}>{title}</p>
            <div className={style.inputContainer} style={inputContainerStyle}>
                <img src={inputIcon} alt="letter icon"/>
                <input
                    type={passwordVisible ? 'text' : type}
                    placeholder={placeholder}
                    className={style.inputStyle}
                    value={value}
                    onChange={onChange}
                />
                {secondIcon}
            </div>
        </div>
    );
}

export default Input;
