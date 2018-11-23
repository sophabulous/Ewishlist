import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';

import Header from './components/Header';
import ListPage from './pages/ListPage';
import TrackingPage from './pages/TrackingPage';
import ProfilePage from './pages/ProfilePage';
import LogInPage from './pages/LogInPage';
import SignUpPage from './pages/SignUpPage';
import ConfirmationPage from './pages/ConfirmationPage';
import DetailPage from './pages/DetailPage';

class App extends Component {
  render () {
    return (
      <div>
        <Header/>
          <Switch>
            <Route path="/" exact component={LogInPage} />
            <Route exact path="/track"  component={TrackingPage} />
            <Route path="/profile"  component={ProfilePage} />
            <Route path="/signup" component={SignUpPage} />
            <Route path="/list" component={ListPage} />
            <Route path="/confirm" component={ConfirmationPage} />
            <Route path="/item" component={DetailPage} />


          </Switch>

      </div>
    );
  }
}

export default App;
