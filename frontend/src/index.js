import React, {Component} from 'react';
import ReactDOM from 'react-dom';

import { BrowserRouter } from 'react-router-dom';
import App from './App';
//importing all the pages for routing **currently not using all of these**
import LogInPage from './pages/LogInPage';
import SignUpPage from './pages/SignUpPage';
import ProfilePage from './pages/ProfilePage';
import TrackingPage from './pages/TrackingPage';
import ListPage from './pages/ListPage';
import ConfirmationPage from './pages/ConfirmationPage';

import './sass/style.scss'

const app = (
  <BrowserRouter>
    <App />
  </BrowserRouter>
);


ReactDOM.render(app, document.getElementById('root'));
