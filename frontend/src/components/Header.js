import React, { Component } from 'react';
import SideBar from "../components/sidebar";
import data from '../data.json';

class Header extends Component {
    render () {
        return (
            <header id="header" className="headerContainer">
              <SideBar pageWrapId={"menuIconContainer"} outerContainerId={"header"} />
              <div id="menuIconContainer">
              </div>
              <h3>{data.siteName}</h3>
              <a href="#" className="trackLinkInHeader">Track a new item!</a>
            </header>
        );
    }
}

export default Header;
