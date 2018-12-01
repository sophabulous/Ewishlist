import React, { Component } from 'react';
import axios from 'axios';
import {
  withRouter
} from 'react-router-dom';

class SignUpForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      Person: [
        {
          username: '',
          email: '',
          password: '',
          confirmpass: ''
        }
      ]

    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({
      Person: [
        {
          username: event.target.value,
          email: event.target.value,
          password: event.target.value,
          confirmpass: event.target.value
        }
      ]
    });
  }

  handleSubmit(event) {
    // alert('Sign up finished for: ' + this.state.username);
    event.preventDefault();
    const user = event.target.username.value
    const pass = event.target.password.value
    const email = event.target.email.value

    const userreq = {
      username: user,
      password: pass,
      email: email
    };
    fetch('http://localhost:8081/users/manage', {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userreq)

      })
     .then((data) => {
      }, (error) => {
        if (error) {
          // handle error here
          console.log(error)
        }
      });
     //redirect to login page
    this.props.history.push('/');
  }

  render() {
    return (
      <div className="form-section-container">
      <form className="SignUpForm" onSubmit={this.handleSubmit}>
        <label>
          <p>Username</p>
          <input name="username" type="text" value={this.state.Person.username} onChange={this.handleNameChange} />
        </label>
        <label>
          <p>Email</p>
          <input name="email" type="text" value={this.state.Person.email} onChange={this.handleChange} />
        </label>
        <label>
          <p>Password</p>
          <input name="password" type="Password" value={this.state.Person.password} onChange={this.handleChange} />
        </label>
        <label>
          <p>Confirm Password</p>
          <input name="confirmpass" type="Password" value={this.state.Person.confirmpass} onChange={this.handleChange} />
        </label>
        <div className="submitBtn">
          <input type="submit" value="Sign me Up!" />
        </div>
      </form>
      </div>
    );
  }
}

export default withRouter(SignUpForm);
