import React, { Component } from 'react';
import data from '../data.json';

class ItemList extends Component {
    render () {
        return (
            <div className="listContainer">
                { data.itemList.map((item) =>
                    <div className="listItemSection">
                        <div className="thumbnailContainer">
                            <img src={item.sectionLogoURL}></img>
                        </div>
                        <div className="listItemContentContainer">
                          <h2>{item.sectionTitle}</h2>
                          <p>{item.sectionBody}</p>
                        </div>
                    </div>
                )}
            </div>
        );
    }
}

export default ItemList;
