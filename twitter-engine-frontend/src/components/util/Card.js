import React from 'react';
import Linkify from 'react-linkify';

class Card extends React.Component {

    render() {
        return (
            <div className="ui small card">
                <div className="content small">
                    <div className="header"></div>
                    <div className="meta">
                        <span className="right floated time">{this.props.date}</span>
                        <span className="category">@{this.props.screenName}</span>
                    </div>
                    <div className="description">
                        <Linkify>
                            <p>{this.props.desc}</p></Linkify>
                    </div>
                </div>
                <div className="extra content">
                    <div className="right floated author">
                        {this.props.username}  <img className="ui avatar image" src={this.props.image}/>
                    </div>
                </div>
            </div>
        );
    }
}

export default Card;