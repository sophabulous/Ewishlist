import React, { Component } from 'react';
import Header from '../components/Header';
import LogInForm from '../components/LogInForm';
import SignUpPage from '../pages/SignUpPage';
import data from '../data.json';

class LogInPage extends Component {

    render () {
        return (
            <div className="container logInPage">
                <Header />
                <div className="page">
                  <h1>Log In</h1>
                  <LogInForm />
                  <span className="dontHaveAccount">
                    {data.dontHaveAccountText}
                    <a href="/signup">{data.signUpText}</a>
                  </span>
                </div>
            </div>
        );
    }
}

export default LogInPage;
