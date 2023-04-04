import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import styles from './header.css'


const Header = () => {
    return(
        <div className="navbar" style={styles}>
            <a href="/">Home</a>

            <a href="/login">Login</a>
        </div>
    )
}

export default Header