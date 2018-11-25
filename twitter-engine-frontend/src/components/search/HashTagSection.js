import React from 'react';
import './HashTagSection.css';

class HashTagSection extends React.Component {

    render() {

        const hashTags = this.props.hashTags.map(entry => {
            return <span><label className='hash-tag' style={{background: '#1DA1F2', color: 'white', padding: '2px'}}>#{entry.text}
            </label><label className='hash-tag-count' style={{background: '#93A644', color: 'white', padding: '2px'}}> {entry.count}</label> </span>
        });


        return (<div style={{marginTop: '32px'}}>
                {this.props.hashTags.length > 0 ?
                    <div className="ui segment">
                        <span>{hashTags}</span>
                    </div> : null
                }
            </div>
        );
    }

}

export default HashTagSection;