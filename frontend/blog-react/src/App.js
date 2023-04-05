import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import Layout from './components/layout';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Post from './components/post/Post'
import Login from './components/login/Login'
import Create from './components/create/Create'
import { Routes, Route } from 'react-router-dom';


function App() {
  const [ posts, setPosts ] = useState();
  const [ post, setPost ] = useState();
  const [ comments, setComments ] = useState();

  async function getPosts() {
    try {
      const res = await api.get("api/blog")

      setPosts(res.data)
    } catch(err) { console.log(err) }
  }

  async function getPost(postId) {
    try {
      const res = await api.get(`/api/blog/post/${postId}`);
      const singlePost = res.data;

      setPost(singlePost);
      console.log(singlePost.comments);
      setComments(singlePost.comments);
    } catch (err) { console.log(err) }
  }

  useEffect(() => {
    getPosts();
  },[])

  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home posts={posts}/>}></Route>
          <Route path="/login" element={<Login/>}></Route>
          <Route path="/create" element={<Create/>}></Route>
          <Route path="/post/:postId" element = {<Post getPostData = {getPost} post = {post} comments = {comments} setComments = {setComments}/>}></Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
