import React, { Component } from 'react';
import Header from '../components/Header';
import data from '../data.json';

class ConfirmationPage extends Component {

    render () {
        return (
            <div className="container confirmationPage">
                <Header />
                <div className="page">
                  <h1>Confirm to track this item?</h1>
                  <div className="confirmationContentContainer">
                    <div className="thumbnailContainer">
                        <img src={data.confirmation.productPic}></img>
                    </div>
                    <div className="confirmationText">
                      <h2>{data.confirmation.productName}</h2>
                      <p>Notes: {data.confirmation.notes}</p>
                      <div className="confirmButtonsContainer">
                        <button type="submit" className="confirmButton">Cancel</button>
                        <button type="submit" className="confirmButton">Yes!</button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
        );
    }
}

export default ConfirmationPage;
