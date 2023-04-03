import React from 'react'
import styles from './home.css'

const Home = ({posts}) => {
    return(
        <div className="posts" style={styles}>
            {
                posts?.map((post) => {
                    return(
                        <div key={post.id} className="post">
                            <span class="post-header">
                                <h1>{post.title}</h1>
                                <span class="post-header-data">
                                    <p>{post.author}</p>
                                    <p>{post.date.split("T")[0]} {post.date.split("T")[1].split(".")[0]}</p>
                                </span>
                            </span>
                            <span class="post-content">
                                <p>{post.content}</p>
                            </span>
                            <span class="post-footer">
                                <span class="post-categories">
                                    {
                                        post.categories?.map((category) => {
                                            return (
                                                <p>{category}</p>
                                            )
                                        })
                                    }

                                </span>
                                <a href={"/post/" + post.postId}>Comments</a>
                            </span>
                        </div>
                    )
                })
            }
        </div>
    )
}

export default Home