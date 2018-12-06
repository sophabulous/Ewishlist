import React, { Component } from 'react';
import Header from '../components/Header';
import {
  withRouter
} from 'react-router-dom';

class TrackingPage extends Component {

  constructor(props) {
    super(props);
    //user login token
    const string = localStorage.getItem("token")
    const token = JSON.parse(string)
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
    //this.props.history.push('/confirm');
    //alert('yes we did the push');
    const string = localStorage.getItem("token")
    const token = JSON.parse(string)
    const query = this.search.value
    //console.log([query, token]);
    //userreq to be passed to backend to add the prodcut for the user
    const userreq = {
      url: query,
      loginToken: token
    }
    console.log(JSON.stringify(userreq));

    fetch('http://localhost:9090/tracking/all', {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type' : 'application/json',
      },
      body: JSON.stringify(userreq)
    })

    .then(response => response.json())
    .then((response) => {
      console.log(response)
    }, (error) => {
      if(error) {
        //handle error here
        console.log(error);
      }
    });
    //once added the product for tracking take back to the listpage
    //this.props.history.pushState(null, '/list');
    this.props.history.push('/list')
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
