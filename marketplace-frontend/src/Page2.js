import React from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import LoginPage from './LoginPage';
import NavBar from './NavBar';

const Page2 = () => {
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const userId = params.get('userId'); // 1

    return (
        <div>
            <NavBar userId={userId} />
            <h1>Welcome to Page 2</h1>
            <p>User ID: {userId}</p>
        </div>
    );
};

export default Page2;
