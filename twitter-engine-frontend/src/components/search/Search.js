import React from 'react';
import axios from 'axios';
import Spinner from '../util/Spinner';
import ResultSection from './ResultSection';
import './Search.css';
import Info from '../util/Info';
import properties from '../util/properties';
import HashTagSection from './HashTagSection';

class Search extends React.Component {

    state = {term: '', isLoading: false, statusEntries: [], isStarted: false, isError: false, hashTags: []};

    onInputChange = event => {
        this.setState({term: event.target.value});
    };

    onFormSubmit = (event) => {
        event.preventDefault();

        if (this.state.term) {
            this.setState({isLoading: true});

            axios.get(properties.apiUrl, {
                params: {
                    query: encodeURI(this.state.term)
                }
            }).then((response) => {
                const entries = response.data.statusEntries;
                this.setState({
                    isLoading: false,
                    statusEntries: entries,
                    isStarted: true,
                    isError: false,
                    hashTags: response.data.hashTags
                });
            }).catch(reason => {
                console.log(reason);
                this.setState({isError: true, isLoading: false})
            });
        }
    };

    render() {
        return (
            <div className="ui row">
                <div className="ui grid">
                    <div className="column row container search-bar">
                        <form className="ui form column" onSubmit={this.onFormSubmit}>
                            <div className="field">
                                <div className="ui search">
                                    <div className="ui icon input">
                                        <input className=" prompt" value={this.state.term} type="text"
                                               onChange={this.onInputChange}
                                               placeholder={properties.searchPlaceholder}/>
                                        <i className="search icon"></i>
                                    </div>
                                    <div className="results">No results</div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <HashTagSection hashTags={this.state.hashTags}/>
                <div style={{marginTop: '10px'}}>{this.state.isLoading ? <Spinner/> :
                    this.state.isError ? <Info text={properties.backEndError}/> :
                        !this.state.isStarted ? <Info text={properties.welcome}/> :
                            <ResultSection statusEntries={this.state.statusEntries}
                                           isStarted={this.state.isStarted}/>}</div>
            </div>
        )
    }
}

export default Search;