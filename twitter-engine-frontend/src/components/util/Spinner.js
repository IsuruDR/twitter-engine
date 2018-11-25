import React from 'react';

class Spinner extends React.Component {

    render() {
        return (
            <div className="ui active inverted dimmer">
                <div className="ui text loader">{this.props.message}</div>
                <i style={{marginTop: '100px', color: '#1DA1F2'}} className="large twitter icon"></i>
            </div>
        );
    }
}

Spinner.defaultProps = {
    message: 'Loading..'
};

export default Spinner;