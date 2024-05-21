/*import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;*/

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './LoginPage';
import Page2 from './Page2';
import CustomerDetail from './CustomerDetail';
import ArticleList from './ArticleList';
import NavBar from './NavBar';
import ArticleDetail from './ArticleDetail';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/articles" element={<ArticleList />} />
                <Route path="/articles/:id" element={<ArticleDetail />} />
                <Route path="/me/:id" element={<CustomerDetail />} />
                <Route path="/page2" element={<Page2 />} />
                <Route path="/" element={<LoginPage />} />
                <Route path="*" element={<div>Not Found</div>} />
            </Routes>
        </Router>
    );
};

export default App;

