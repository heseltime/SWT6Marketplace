import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

// Customer mangement operations
export const getCustomerById = async (id) => {
    const response = await axios.get(`${API_BASE_URL}/customers/${id}`);
    return response.data;
};

export const updateCustomer = async (id, customerData) => {
    const response = await axios.put(`${API_BASE_URL}/customers/${id}`, customerData);
    return response.data;
};

export const deleteCustomer = async (id) => {
    const response = await axios.delete(`${API_BASE_URL}/customers/${id}`);
    return response.data;
};

export const getAllCustomers = async () => {
    const response = await axios.get(`${API_BASE_URL}/customers`);
    return response.data;
};

export const createCustomer = async (customerData) => {
    const response = await axios.post(`${API_BASE_URL}/customers`, customerData);
    return response.data;
};

// functions for Article operations
export const getArticleById = async (id) => {
    const response = await axios.get(`${API_BASE_URL}/articles/${id}`);
    return response.data;
};

export const updateArticle = async (id, articleData) => {
    const response = await axios.put(`${API_BASE_URL}/articles/${id}`, articleData);
    return response.data;
};

export const deleteArticle = async (id) => {
    const response = await axios.delete(`${API_BASE_URL}/articles/${id}`);
    return response.data;
};

export const getAllArticles = async () => {
    const response = await axios.get(`${API_BASE_URL}/articles`);
    return response.data;
};

export const createArticle = async (articleData) => {
    const response = await axios.post(`${API_BASE_URL}/articles`, articleData);
    return response.data;
};

// ArticleDetail info display
export const calculateShippingCost = async (zipCode, quantity) => {
    const response = await axios.post(`${API_BASE_URL}/calculateShipping`, {
        zipCode,
        quantity
    });
    return response.data.shippingCost;
};

// Article search functions
export const searchArticlesByPriceRange = async (minPrice, maxPrice) => {
    const response = await axios.get(`${API_BASE_URL}/articles/search/byPriceRange`, {
        params: { minPrice, maxPrice }
    });
    return response.data;
};

export const searchArticlesByName = async (name) => {
    const response = await axios.get(`${API_BASE_URL}/articles/search/byName`, {
        params: { name }
    });
    return response.data;
};

export const searchArticlesByDescription = async (description) => {
    const response = await axios.get(`${API_BASE_URL}/articles/search/byDescription`, {
        params: { description }
    });
    return response.data;
};
