import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useRef } from 'react';
import styles from './post.css'
import { useParams } from 'react-router-dom';
import api from '../../api/axiosConfig';
import { Form, Button } from 'react-bootstrap';


const Post = ({ getPostData, post, comments, setComments }) => {

    const postText = useRef();
    const postId = useParams().postId;

    const addComment = async (e) => {
        e.preventDefault();

        const com = postText.current;

        try {
            const res = await api.post("/api/comment", {commentBody:com.value,postId:postId});

            const updatedComments = [...comments, {body:com.value}];
    
            com.value = "";
    
            setComments(updatedComments);
        }
        catch(err) { console.log(err) }
    }

    useEffect(() => {
        getPostData(postId);
    },[]);

    return(
        <div className="content" style={styles}>
            <div key={post?.id} className="post">
                <span className="post-header">
                    <h1>{post?.title}</h1>
                    <span className="post-header-data">
                        <p>{post?.author}</p>
                        <p>{post?.date.split("T")[0]} {post?.date.split("T")[1].split(".")[0]}</p>
                    </span>
                </span>
                <span className="post-content">
                    <p>{post?.content}</p>
                 </span>
                    <span className="post-footer">
                        <span className="post-categories">
                            {
                                post?.categories?.map((category, index) => {
                                    return (
                                        <p key={index}>{category}</p>
                                    )
                                })
                            }
                        </span>
                    </span>
                </div>
            <div className="comments">
                {
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Control ref={postText} as="textarea" rows={3} maxLength="150"></Form.Control>
                    </Form.Group>
                    <Button variant="outline-info" onClick={addComment}>Write comment</Button>
                </Form>
                }
                {
                    comments?.map((comment, index) => {
                        return(
                            <div key={index} className="comment">
                                <p>{comment.body}</p>
                            </div>
                        )
                    })
                }
            </div>
        </div>

    )
}

export default Post