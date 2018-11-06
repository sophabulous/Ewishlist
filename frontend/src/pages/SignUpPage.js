import React, { Component } from 'react';
import Header from '../components/Header';
import SignUpForm from '../components/SignUpForm';

class SignUpPage extends Component {

    render () {
        return (
          <div className="container SignUpPage">
              <Header />
              <h1>Sign Up</h1>
              <SignUpForm />
          </div>
        );
    }
}

export default SignUpPage;
