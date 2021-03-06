import React, { Component } from 'react';
import PropTypes from 'prop-types';
import data from '../data.json';
import { Link } from 'react-router-dom';

class ItemList extends Component {
  // static defaultProps = {
  //   href: PropTypes.string.isRequired
  // }

  constructor(props) {
    super(props);
    const string = localStorage.getItem("token")
    const userreq = JSON.parse(string)
    this.state = { items: []};

    //this.handleItem = this.handleItem.bind(this);
  }
  componentDidMount() {
      const string = localStorage.getItem("token")
      const userreq = JSON.parse(string)
      fetch('http://localhost:9090/tracking/all', {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userreq)

        })
      .then(response => response.json())
      .then((response) => {
          this.setState({ items: response })
          console.log(response);
        }, (error) => {
          if (error) {
            // handle error here
            console.log(error)
          }
      });
  }

  /*  handleItem(event) {
      event.preventDefault();
      //localStorage.setItem("Item", this.state.items.)
      console.log(event.target.value);
    } */

    render () {
        return (
          <div className="listContainer">
          { this.state.items.map((item) =>
              <Link to={{
                pathname: '/item_' + item.productId,
                state: {
                  itemDetail: item
                }
              }}>
                <div className="listItemSection">
                    <div className="thumbnailContainer">
                      <img src={item.productImg}></img>
                    </div>
                    <div className="listItemContentContainer">
                      <h2>{item.productName}</h2>
                      <p>Current Price: ${(item.currentPrice).toFixed(2)}</p>
                      <p>Tracking Price: ${(item.trackedPrice).toFixed(2)}</p>
                    </div>
                </div>
              </Link>
          )}
          </div>
        );
    }
}

export default ItemList;
