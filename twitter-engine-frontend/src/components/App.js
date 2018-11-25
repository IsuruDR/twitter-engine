import React, { Component } from 'react';
import Search from './search/Search';
import './App.css';
class App extends Component {
  render() {
    return (
      <div className="ui container">
          <Search/>
      </div>
    );
  }
}

export default App;
