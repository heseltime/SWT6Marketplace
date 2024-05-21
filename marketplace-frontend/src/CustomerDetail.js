import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getCustomerById, updateCustomer } from './apiService';
import NavBar from './NavBar';

const CustomerDetail = () => {
    const { id } = useParams();
    const userId = 1; // otherwise from params

    const [customer, setCustomer] = useState(null);
    const [formData, setFormData] = useState({
        name: '',
        billingAddress: '',
        email: '',
        shipmentAddress: ''
    });
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const result = await getCustomerById(id);
            setCustomer(result);
            setFormData(result);
        };
        fetchData();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateCustomer(customer.customerId, formData);
            setSuccessMessage('Customer details updated successfully.');
        } catch (error) {
            setSuccessMessage('Failed to update customer details.'); // handle error appropriately
        }
    };

    if (!customer) return <div>Loading...</div>;

    return (
        <div>
            <NavBar />
            <h1>Edit Customer</h1>
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} />
                </div>
                <div>
                    <label>Billing Address:</label>
                    <input type="text" name="billingAddress" value={formData.billingAddress} onChange={handleChange} />
                </div>
                <div>
                    <label>Email:</label>
                    <input type="email" name="email" value={formData.email} onChange={handleChange} />
                </div>
                <div>
                    <label>Shipment Address:</label>
                    <input type="text" name="shipmentAddress" value={formData.shipmentAddress} onChange={handleChange} />
                </div>
                <button type="submit">Save</button>
            </form>
        </div>
    );
};

export default CustomerDetail;
