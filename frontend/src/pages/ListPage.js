import React, { Component } from 'react';
import ItemList from '../components/ItemList';
import Header from '../components/Header';
import Footer from '../components/Footer';

class ListPage extends Component {

    render () {
        return (
            <div className="container">
                <Header />
                <div className="page listPage">
                  <div className="topRow">
                    <h1>MyList</h1>
                    <a className="trackNewItemLink" href="/track" >Track New Item</a>
                  </div>
                  <ItemList/>
                </div>
                <Footer />
            </div>
        );
    }
}

export default ListPage;
