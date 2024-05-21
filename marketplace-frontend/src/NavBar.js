import React from 'react';
import { Link } from 'react-router-dom';
import './NavBar.css';

const NavBar = ({ userId }) => {    
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/articles">Articles</Link>
                </li>
                <li>
                    <Link to={`/me/${userId}`}>My Profile</Link>
                </li>
                <li>
                    <Link to={`/page2?userId=${userId}`}>Page 2</Link>
                </li>
            </ul>
        </nav>
    );
};

export default NavBar;
