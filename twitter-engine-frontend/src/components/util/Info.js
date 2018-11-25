import React from 'react';

class Info extends React.Component {

    render() {
        return (
            <div className="ui grid container center aligned page grid">
                <div className="row">
                    <label>{this.props.text}</label>
                </div>
                <div className="row">
                    <i className="large twitter icon" style={{color: '#1DA1F2', marginTop: '-20px'}}></i>
                </div>
            </div>
        );
    }
}

export default Info;