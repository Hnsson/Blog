import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useRef } from 'react';
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
        <div className="content">
            <div className="post">
                <div key={post?.id} className="post">
                    <h1>{post?.title}</h1>
                    <p>{post?.author}</p>
                    <p>{post?.date}</p>
                    <p>{post?.categories}</p>
                    <p>{post?.content}</p>
                </div>
            </div>
            <div className="comments">
                {
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Control ref={postText} as="textarea" rows={3}></Form.Control>
                    </Form.Group>
                    <Button variant="outline-info" onClick={addComment}>Send</Button>
                </Form>
                }
                {
                    comments?.map((comment) => {
                        return(
                            <div className="comment">
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