import React, { Component } from 'react';
import Header from '../components/Header';
import LogInForm from '../components/LogInForm';
import data from '../data.json';

class LogInPage extends Component {

    render () {
        return (
            <div className="container logInPage">
                <Header />
                <h1>Log In</h1>
                <LogInForm />
                <span className="dontHaveAccount">
                  {data.dontHaveAccountText}
                  <a href="#">{data.signUpText}</a>
                </span>
            </div>
        );
    }
}

export default LogInPage;
