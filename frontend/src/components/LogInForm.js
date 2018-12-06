import React, { Component } from 'react';
import { AsyncStorage } from "react"

import {
  withRouter
} from 'react-router-dom';
class LogInForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      Person: [
        {
          username: '',
          password: ''
        }
      ]

    };

    //this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleNameChange = this.handleNameChange.bind(this);

  }
  handleNameChange(event){
    this.setState({
      username: event.target.value,
      password: event.target.value
    })
  }
  /*handleChange(event) {
    //this.preventDefault();
    this.setState({
      username: event.target.value,
      password: event.target.value
    });
  }*/
  handleSubmit(event) {
    const userreq = {
      username: event.target.username.value,
      password: event.target.password.value
    };

    //alert('A name was submitted: ' + this.state.username);
    //this.context.router.push('/');
    event.preventDefault();
    // this.props.history.push('/list');
    fetch('http://localhost:8081/users/token/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userreq)

      })
    .then(response => response.json())
    .then((response) => {
        localStorage.setItem("token",JSON.stringify(response))
        this.props.history.push('/list');
      }, (error) => {
        if (error) {
          // handle error here
          console.log(error)
        }
    });
  }


  render() {
    return (
      <div className="form-section-container">
      <form className="logInForm" onSubmit={this.handleSubmit}>
        <label>
          <p>Username</p>
          <input name="username" type="text" value={this.state.Person.username} onChange={this.handleNameChange} />
        </label>
        <label>
          <p>Password</p>
          <input name="password" type="Password" value={this.state.Person.password} onChange={this.handleNameChange} />
        </label>
        <div className="submitBtn">
          <input type="submit" value="Submit" />
        </div>
      </form>
      </div>
    );
  }
}

export default withRouter(LogInForm);
