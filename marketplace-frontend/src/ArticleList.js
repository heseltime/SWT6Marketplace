import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllArticles, searchArticlesByName, searchArticlesByDescription, searchArticlesByPriceRange } from './apiService';
import NavBar from './NavBar';
import './ArticleList.css';

const ArticleList = () => {
    const [articles, setArticles] = useState([]);
    const [searchType, setSearchType] = useState('name'); 
    const [searchQuery, setSearchQuery] = useState('');
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');

    const userId = 1; 

    useEffect(() => {
        const fetchArticles = async () => {
            try {
                const result = await getAllArticles();
                setArticles(result);
            } catch (error) {
                console.error("Error fetching articles:", error);
            }
        };

        fetchArticles();
    }, []);

    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            let result;
            if (searchType === 'name') {
                result = await searchArticlesByName(searchQuery);
            } else if (searchType === 'description') {
                result = await searchArticlesByDescription(searchQuery);
            } else if (searchType === 'priceRange') {
                result = await searchArticlesByPriceRange(minPrice, maxPrice);
            }
            setArticles(result);
        } catch (error) {
            console.error("Error searching articles:", error);
        }
    };

    return (
        <div>
            <NavBar userId={userId} />
            <h1>Articles</h1>
            <form onSubmit={handleSearch} className="search-form">
                <div className="search-type">
                    <label>
                        <input
                            type="radio"
                            value="name"
                            checked={searchType === 'name'}
                            onChange={(e) => setSearchType(e.target.value)}
                        />
                        Name
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="description"
                            checked={searchType === 'description'}
                            onChange={(e) => setSearchType(e.target.value)}
                        />
                        Description
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="priceRange"
                            checked={searchType === 'priceRange'}
                            onChange={(e) => setSearchType(e.target.value)}
                        />
                        Price Range
                    </label>
                </div>
                {searchType === 'priceRange' ? (
                    <div className="search-fields">
                        <input
                            type="number"
                            placeholder="Min Price"
                            value={minPrice}
                            onChange={(e) => setMinPrice(e.target.value)}
                        />
                        <input
                            type="number"
                            placeholder="Max Price"
                            value={maxPrice}
                            onChange={(e) => setMaxPrice(e.target.value)}
                        />
                    </div>
                ) : (
                    <div className="search-fields">
                        <input
                            type="text"
                            placeholder="Search"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                    </div>
                )}
                <button type="submit">Search</button>
            </form>
            <ul className="article-list">
                {articles.map(article => (
                    <li key={article.articleId} className="article-item">
                        <Link to={`/articles/${article.articleId}`}>
                            <h2>{article.name}</h2>
                        </Link>
                        <p>{article.shortDescription}</p>
                        <p>Price: ${article.price}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ArticleList;
