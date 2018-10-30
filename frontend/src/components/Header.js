import React, { Component } from 'react';
import data from '../data.json';

class Header extends Component {
    render () {
        return (
            <header className="banner-section-container">
              <h3>{data.siteName}</h3>
            </header>
        );
    }
}

export default Header;
