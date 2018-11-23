import React, { Component } from 'react';
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
    //alert('Sign up finished for: ' + this.state.username);
    event.preventDefault();
    this.props.history.push('/list');
  }

  render() {
    return (
      <div className="form-section-container">
      <form className="SignUpForm" onSubmit={this.handleSubmit}>
        <label>
          <p>Username</p>
          <input type="text" value={this.state.Person.username} onChange={this.handleNameChange} />
        </label>
        <label>
          <p>Email</p>
          <input type="text" value={this.state.Person.email} onChange={this.handleChange} />
        </label>
        <label>
          <p>Password</p>
          <input type="Password" value={this.state.Person.password} onChange={this.handleChange} />
        </label>
        <label>
          <p>Confirm Password</p>
          <input type="Password" value={this.state.Person.confirmpass} onChange={this.handleChange} />
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
