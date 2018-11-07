import React, { Component } from 'react';
import data from '../data.json';

class Profile extends React.Component {

  render() {
    return (
      <div className="simple-section-container Profile">
          <div className="ProfileImg">
                <img src={data.img}></img>
          </div>
          <div className="ProfileDetails">
                <label>
                  <p>Username</p>
                  <input type="text" value={data.username}/>
                </label>
                <label>
                  <p>Password</p>
                  <input type="text" value={data.Password} />
                </label>
          </div>
      </div>
    );
  }
}

export default Profile;
