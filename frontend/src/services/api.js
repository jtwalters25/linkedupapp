// src/services/api.js
import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const getProfile = async () => {
  const response = await axios.get(`${API_URL}/profile`);
  return response.data;
};

export const getEvents = async () => {
  const response = await axios.get(`${API_URL}/events`);
  return response.data;
};
