import React, { Component } from 'react';
import Header from '../components/Header';
import data from '../data.json';
import { withRouter } from 'react-router-dom';


class DetailPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      notes: ""
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

  htmlDecode(input){
    var e = document.createElement('div');
    e.innerHTML = input;
    return e.childNodes.length === 0 ? "" : e.childNodes[0].nodeValue;
  }

    render () {
      const { itemDetail } = this.props.location.state;
      console.log(itemDetail);
        return (
          <div className="container detailPage">
              <Header />
              <div className="page">
                <h1 className="itemTitle">{itemDetail.productName}</h1>
                <div className="detailContainer">
                  <div className="thumbnailContainer">
                      <img src={itemDetail.productImg}></img>
                  </div>
                  <div className="detailText">
                    <p><strong>Description:</strong></p>
                    <div dangerouslySetInnerHTML={{__html: this.htmlDecode(itemDetail.productDescription)}}></div>
                    <p><strong>Price Change:</strong> (Before) ${(itemDetail.trackedPrice).toFixed(2)} -->
                    <span className="newPrice"> (Now)${(itemDetail.currentPrice).toFixed(2)} </span>
                    </p>
                    <p><strong>Notes: </strong></p>
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
