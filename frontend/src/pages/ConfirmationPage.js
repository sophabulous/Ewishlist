import React, { Component } from 'react';
import Header from '../components/Header';
import data from '../data.json';
import {
  withRouter
} from 'react-router-dom';

class ConfirmationPage extends Component {
    constructor(props) {
      super(props);
      this.handleCancel = this.handleCancel.bind(this);
      this.handleConfirm = this.handleConfirm.bind(this);
    }
    handleCancel(event) {
      event.preventDefault();
      this.props.history.push('/track');
    }
    handleConfirm(event) {
      event.preventDefault();
      this.props.history.push('/list');

      // need to add this item to the bottom of the list
    }
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
                        <button type="submit" className="confirmButton" onClick={this.handleCancel}>Cancel</button>
                        <button type="submit" className="confirmButton" onClick={this.handleConfirm}>Yes!</button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
        );
    }
}

export default withRouter(ConfirmationPage);
