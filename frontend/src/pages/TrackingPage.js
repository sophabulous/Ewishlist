import React, { Component } from 'react';
import Header from '../components/Header';
import {
  withRouter
} from 'react-router-dom';

class TrackingPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      query: '',
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleTrack = this.handleTrack.bind(this);
  }

  handleInputChange(event) {
    this.setState({
       query: this.search.value
    });
  }

  handleTrack(event) {
    event.preventDefault();
    this.props.history.push('/confirm');
    //alert('yes we did the push');

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
                      <button type="submit" className="trackButton" onClick={this.handleTrack}>Track</button>
                    </div>
                 </form>
               </div>
            </div>
        );
    }
}

export default withRouter(TrackingPage);
