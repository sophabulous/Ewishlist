import React, { Component } from 'react';
import data from '../data.json';

class ItemList extends Component {
    render () {
        return (
            <div className="simple-section-container">
                { data.itemList.map((item) =>
                    <div className="simpleSection">
                        <div className="sectionLogoContainer">
                            <img src={item.sectionLogoURL}></img>
                        </div>
                        <h2>{item.sectionTitle}</h2>
                        <h3>{item.sectionHeading}</h3>
                        <p>{item.sectionBody}</p>
                    </div>
                )}
            </div>
        );
    }
}

export default ItemList;
