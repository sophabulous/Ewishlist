import React, { Component } from 'react';
import { AsyncStorage } from "react"

import {
  withRouter
} from 'react-router-dom';
class LogInForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({
      username: event.target.value,
      password: event.target.value
    });
  }
  handleSubmit(event) {
    const userreq = {
      username: this.state.username,
      password: this.state.password,
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
          <input name="username" type="text" value={this.state.username} onChange={this.handleChange} />
        </label>
        <label>
          <p>Password</p>
          <input name="password" type="password" value={this.state.password} onChange={this.handleChange} />
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
