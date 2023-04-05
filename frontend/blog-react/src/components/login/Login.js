import React from 'react'
import styles from './login.css'
import { useState } from 'react';
import api from '../../api/axiosConfig';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const sendLoginRequest = async (e) => {
        console.log("Sending login request...");
        const req = {
            username: username,
            password: password
        }

        await api.post("/api/login", req)
            .then((data) => {
                localStorage.setItem('jwt_token', data.data);
            })
            .catch((err) => {console.log("401 - Unathorized");});
    }

    return(
        <div className="posts" style={styles}>
            <div>
                <label htmlFor="username">Username</label>
                <input type="email" id="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
            </div>
            <div>
                <label htmlFor="password">Password</label>
                <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            </div>
            <div>
                <button id="submit" type="submit" onClick={sendLoginRequest}>Login</button>
            </div>
        </div>
    )
}

export default Login