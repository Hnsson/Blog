import React from 'react'
import styles from './create.css'
import { useState } from 'react';
import api from '../../api/axiosConfig';



const Create = ({ getPostData, post, comments, setComments }) => {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [content, setContent] = useState("");
    const [categories, setCategories] = useState("");
    const [selectedItem, setSelectedItem] = useState("");
    const [selectedItems, setSelectedItems] = useState([]);

    const sendCreateRequest = async (e) => {
        e.preventDefault();
        console.log(selectedItems)
        


        console.log("Sending login request...");
        const req = {
            title: title,
            author: author,
            content: content,
            categories: selectedItems.join(","),
            Authorization: localStorage.getItem('jwt_token')
        }

        await api.post("/api/blog/create", req)
            .then((data) => {
                console.log(data)
            })
            .catch((err) => {console.log("Could not create post!");});
    }

    const handleSelect = (e) => {
        const value = e.target.value;
        if(!selectedItems.includes(value)) {
            setSelectedItems([...selectedItems, value]);
        }
    }

    return(
        <div className="create-posts" style={styles}>
            <div className="create-title">
                <label htmlFor="title">Title:</label>
                <input maxLength="30" type="text" id="title" value={title} onChange={(e) => setTitle(e.target.value)}/>
            </div>
            <div className="create-author">
                <label htmlFor="author">Author:</label>
                <input maxLength="30" type="text" id="author" value={author} onChange={(e) => setAuthor(e.target.value)}/>
            </div>
            <div className="create-content">
                <label htmlFor="content">Content:</label>
                <textarea id="content" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
            </div>
            <div className="create-category">
                {/* <label htmlFor="categories">Categories:</label> */}
                {/* <input type="text" id="categories" value={categories} onChange={(e) => setCategories(e.target.value)}/> */}
                <select value={selectedItem} onChange={handleSelect}>
                    <option value="">Select a category to add</option>
                    <option value="tech">tech</option>
                    <option value="life">life</option>
                    <option value="internet">internet</option>
                    <option value="tutorial">tutorial</option>
                </select>
                <ul>
                    <p>Current categories:</p>
                    {selectedItems.map((item, index) => (
                        <li key={index}>{item}</li>
                    ))}
                </ul>
            </div>
            <div className="create-submit">
                <button id="submit" type="submit" onClick={sendCreateRequest}>Create</button>
            </div>
        </div>
    )
}

export default Create