import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';

import Header from './components/Header';
import ListPage from './pages/ListPage';
import TrackingPage from './pages/TrackingPage';
import Profile from './components/Profile';
import LogInPage from './pages/LogInPage';
import SignUpPage from './pages/SignUpPage';

class App extends Component {
  render () {
    return (
      <div>
        <Header/>
          <Switch>
            <Route path="/" exact component={ListPage} />
            <Route exact path="/track"  component={TrackingPage} />
            <Route path="/profile"  component={Profile} />
            <Route path="/signUp" component={SignUpPage} />
            <Route path="/login" component={LogInPage} />

          </Switch>

      </div>
    );
  }
}

export default App;
