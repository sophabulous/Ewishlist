import React, { Component } from 'react';
import ItemList from '../components/ItemList';
import Header from '../components/Header';
import Footer from '../components/Footer';

class ListPage extends Component {

    render () {
        return (
            <div className="container">
                <Header />
                <h1>MyList</h1>
                <ItemList/>
                <Footer />
            </div>
        );
    }
}

export default ListPage;
