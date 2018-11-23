import React, { Component } from 'react';
import Header from '../components/Header';

class TrackingPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      query: '',
    };

    this.handleInputChange = this.handleInputChange.bind(this);
  }

  handleInputChange(event) {
    this.setState({
       query: this.search.value
    });
  }

    render () {
        return (
            <div className="container trackingPage">
                <Header />
                <div className="page">
                  <h1>Track a New Item</h1>
                  <form>
                    <div className="trackingFormContent">
                      <input
                        className="inputField"
                        placeholder="Enter the URL of the item you want to track"
                        ref={input => this.search = input}
                        onChange={this.handleInputChange}
                     />
                      <button type="submit" className="trackButton">Track</button>
                    </div>
                 </form>
               </div>
            </div>
        );
    }
}

export default TrackingPage;
