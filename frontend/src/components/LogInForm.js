import React, { Component } from 'react';

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
    //alert('A name was submitted: ' + this.state.username);
    //this.context.router.push('/');
    event.preventDefault();
    this.props.history.push('/list');
  }

  /*handleClick(event) {
    this.props.push('/');
  }*/

  render() {
    return (
      <div className="form-section-container">
      <form className="logInForm" onSubmit={this.handleSubmit}>
        <label>
          <p>Username</p>
          <input type="text" value={this.state.username} onChange={this.handleChange} />
        </label>
        <label>
          <p>Password</p>
          <input type="text" value={this.state.password} onChange={this.handleChange} />
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
