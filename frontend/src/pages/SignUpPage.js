import React, { Component } from 'react';
import Header from '../components/Header';
import SignUpForm from '../components/SignUpForm';

class SignUpPage extends Component {

    render () {
        return (
          <div className="container SignUpPage">
              <Header />
              <div className="page">
                <h1>Sign Up</h1>
                <SignUpForm />
              </div>
          </div>
        );
    }
}

export default SignUpPage;
