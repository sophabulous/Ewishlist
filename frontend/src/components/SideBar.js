import React, { Component } from 'react';
import { slide as Menu } from "react-burger-menu";

export default props => {
  return (
    // Pass on our props
    <Menu {...props}>
      <a className="menu-item" href="/list">
        Home
      </a>

      <a className="menu-item" href="/track">
        Track an Item
      </a>

      <a className="menu-item" href="/profile">
        My Profile
      </a>
    </Menu>
  );
};
