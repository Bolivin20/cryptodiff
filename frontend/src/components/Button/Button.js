import style from './Button.module.css';
import React from 'react';



function Button(props) {

    return (
        <div style={{ width: props.width }} className={style.buttonDiv}>
            <button type='submit' className={style.bigButton}>
                {props.text}
            </button>
        </div>

    );
}

export default Button;