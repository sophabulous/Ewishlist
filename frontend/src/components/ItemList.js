import React, { Component } from 'react';
import data from '../data.json';

class ItemList extends Component {
  constructor(props) {
    super(props);
    const string = localStorage.getItem("token")
    const userreq = JSON.parse(string)
    this.state = { items: []};
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
          }, (error) => {
            if (error) {
              // handle error here
              console.log(error)
            }
        });
    }

    render () {
        return (
            <div className="listContainer">
                { this.state.items.map((item) =>
                    <div className="listItemSection">
                        <div className="thumbnailContainer">
                        </div>
                        <div className="listItemContentContainer">
                          <h2><a href={item.url}>{item.productName}</a></h2>
                          <p>Current Price: {item.currentPrice}</p>
                          <p>Tracking Price: {item.trackedPrice}</p>
                        </div>
                    </div>
                )}
            </div>
        );
    }
}

export default ItemList;
