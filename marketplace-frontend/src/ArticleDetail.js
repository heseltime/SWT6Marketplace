import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getArticleById, calculateShippingCost } from './apiService';
import NavBar from './NavBar';
import './ArticleDetail.css'; 

const ArticleDetail = () => {
    const { id } = useParams();
    const [article, setArticle] = useState(null);
    const [quantity, setQuantity] = useState(1);
    const [zipCode, setZipCode] = useState('');
    const [shippingCost, setShippingCost] = useState(null);
    const [cartMessage, setCartMessage] = useState('');

    useEffect(() => {
        const fetchArticle = async () => {
            try {
                const result = await getArticleById(id);
                setArticle(result);
            } catch (error) {
                console.error("Error fetching article:", error);
            }
        };

        fetchArticle();
    }, [id]);

    const handleAddToCart = () => {
        setCartMessage(`Added ${quantity} of ${article.name} to the cart.`);
    };

    const handleCalculateShipping = async (e) => {
        e.preventDefault();
        try {
            const cost = await calculateShippingCost(zipCode, quantity);
            setShippingCost(cost);
        } catch (error) {
            console.error("Error calculating shipping cost:", error);
        }
    };

    if (!article) return <div>Loading...</div>;

    return (
        <div>
            <NavBar />
            <h1>{article.name}</h1>
            <p>{article.shortDescription}</p>
            <p>Price: ${article.price}</p>
            <p>Stock: {article.stockAmount}</p>
            <p>Rating: {article.rating}</p>
            <p>Shipment Countries: {article.shipmentCountries}</p>
            <p>Shipment Cost: ${article.shipmentCost}</p>

            <div className="add-to-cart">
                <input 
                    type="number" 
                    value={quantity} 
                    onChange={(e) => setQuantity(e.target.value)} 
                    min="1" 
                    max={article.stockAmount}
                />
                <button onClick={handleAddToCart}>Add to Cart</button>
                {cartMessage && <p className="cart-message">{cartMessage}</p>}
            </div>

            <form onSubmit={handleCalculateShipping} className="shipping-form">
                <h3>Calculate Shipping Cost</h3>
                <input 
                    type="text" 
                    value={zipCode} 
                    onChange={(e) => setZipCode(e.target.value)} 
                    placeholder="Enter ZIP code" 
                    required 
                />
                <button type="submit">Calculate</button>
                {shippingCost !== null && <p>Shipping Cost: ${shippingCost}</p>}
            </form>
        </div>
    );
};

export default ArticleDetail;
