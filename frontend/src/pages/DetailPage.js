import React, { Component } from 'react';
import Header from '../components/Header';
import data from '../data.json';
import {
  withRouter
} from 'react-router-dom';


class DetailPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      notes: data.detail.notes
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSave = this.handleSave.bind(this);

  }

  handleChange(event){
    this.setState({
      notes: event.target.value
    });

  }

  handleSave(event) {
    //need to save the notes
    //to be done later
  }

    render () {
        return (
          <div className="container detailPage">
              <Header />
              <div className="page">
                <h1>{data.detail.productName}</h1>
                <div className="detailContainer">
                  <div className="thumbnailContainer">
                      <img src={data.detail.productPic}></img>
                  </div>
                  <div className="detailText">
                    <h2>Description: {data.detail.description}</h2>
                    <h2> Price Change: {data.detail.oldPrice} -> <span className="newPrice">{data.detail.newPrice} </span></h2>
                    <h2>Notes: </h2>
                      <div contenteditable="true" className="notes" onChange={this.handleChange}>
                        {this.state.notes}
                        </div>

                    <div className="confirmButtonsContainer">
                      <button type="submit" className="confirmButton" onclick={this.handleSave}>Save</button>
                      <button type="submit" className="confirmButton">Delete</button>
                    </div>
                  </div>
                </div>
             </div>
          </div>
        );
    }
}

export default DetailPage;
