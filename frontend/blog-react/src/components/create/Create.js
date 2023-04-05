import React from 'react'
import styles from './create.css'
import { useState } from 'react';
import api from '../../api/axiosConfig';



const Create = ({ getPostData, post, comments, setComments }) => {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [content, setContent] = useState("");
    const [categories, setCategories] = useState("");

    const sendCreateRequest = async (e) => {
        console.log("Sending login request...");
        const req = {
            title: title,
            author: author,
            content: content,
            categories: categories,
            Authorization: localStorage.getItem('jwt_token')
        }

        await api.post("/api/blog/create", req)
            .then((data) => {
                console.log(data)
            })
            .catch((err) => {console.log("401 - Unathorized");});
    }

    return(
        <div className="posts" style={styles}>
            <div>
                <label htmlFor="title">Title:</label>
                <input type="text" id="title" value={title} onChange={(e) => setTitle(e.target.value)}/>
            </div>
            <div>
                <label htmlFor="author">Author:</label>
                <input type="text" id="author" value={author} onChange={(e) => setAuthor(e.target.value)}/>
            </div>
            <div>
                <label htmlFor="content">Content:</label>
                <textarea id="content" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
            </div>
            <div>
                <label htmlFor="categories">Categories:</label>
                <input type="text" id="categories" value={categories} onChange={(e) => setCategories(e.target.value)}/>
            </div>
            <div>
                <button id="submit" type="submit" onClick={sendCreateRequest}>Login</button>
            </div>
        </div>
    )
}

export default Create