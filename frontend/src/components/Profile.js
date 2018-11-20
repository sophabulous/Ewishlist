import React, { Component } from 'react';
import data from '../data.json';

class Profile extends React.Component {

  render() {
    return (
      <div className="listContainer">
          <div className="profileImg">
                <img src={data.img}></img>
          </div>
          <div className="profileDetails">
                <label>
                  <p>Username</p>
                  <input type="text" value={data.username}/>
                </label>
                <label>
                  <p>Password</p>
                  <input type="text" value={data.password} />
                </label>
          </div>
      </div>
    );
  }
}

export default Profile;
