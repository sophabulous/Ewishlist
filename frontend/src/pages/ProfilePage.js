import React, { Component } from 'react';
import Profile from '../components/Profile';
import Header from '../components/Header';

class ProfilePage extends Component {

    render () {
        return (
          <div className="container ProfilePage">
              <Header />
              <h1>Profile</h1>
              <Profile />
          </div>
        );
    }
}

export default ProfilePage;
