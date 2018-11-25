import './ResultSection.css';
import React from 'react';
import Card from "../util/Card";
import moment from 'moment';
import Info from '../util/Info';
import properties from '../util/properties';

class ResultSection extends React.Component {

    render() {

        const statuses = this.props.statusEntries.map(status => {
            return <Card date={this.timeAgo(status.createdAt)} screenName={status.user.screenName}
                         image={status.user.profilePictureUrl} username={status.user.name} desc={status.text}/>;
        });

        console.log(this.props.statusEntries.length > 0 && !this.props.isStarted);
        console.log(this.props.statusEntries.length);
        console.log(this.props.isStarted);

        return (
            <div>
                {(this.props.statusEntries.length == 0 && this.props.isStarted) ? <Info text = {properties.noTweetsFound}/> :
                    (<div>
                        <div className="card-grid">{statuses}</div>
                        {this.props.isStarted ? (<div className="ui container center aligned page" style={{marginBottom: '10px'}}>
                            <div className="row">
                            <i className="large twitter icon" style={{color: '#1DA1F2'}}></i>
                            <i className="large twitter icon" style={{color: '#1DA1F2'}}></i>
                            <i className="large twitter icon" style={{color: '#1DA1F2'}}></i>
                            </div>
                        </div>) : <div></div>
                        }
                    </div>)}
            </div>
        );
    }

    timeAgo = (date) => {
        return moment(date).startOf('hour').fromNow();
    }
}

export default ResultSection;