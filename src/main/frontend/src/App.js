import React, {Component} from 'react';
import logo from './logo.gif';
import './App.css';
import {Table} from "react-bootstrap";

class App extends Component {

    symbol;
    price_24h;
    volume_24h;
    last_trade_price;

    state = {
        coins: []
    };


    async componentDidMount() {
        const response = await fetch('/coins');
        const body = await response.json();
        this.setState({coins: body});
    }

    render() {
        const {coins} = this.state;
        return (
            <div className="App">
                <header className="App-header">

                    <img src={logo} className="App-logo" alt="logo" />
                    <div className="App-intro">
                        <h2>Coins</h2>
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Symbol</th>
                                <th>Price 24h</th>
                                <th>Volume 24h</th>
                                <th>Last Trade Price</th>
                            </tr>
                            </thead>
                            <tbody>

                                {coins.map(coin =>
                                    <tr>
                                        <td key={coin.id}>
                                            {coin.id}
                                        </td>
                                    <td key={coin.id}>
                                        {coin.symbol}
                                    </td>
                                        <td key={coin.id}>
                                            {coin.price_24h}
                                        </td>
                                        <td key={coin.id}>
                                            {coin.volume_24h}
                                        </td>
                                        <td key={coin.id}>
                                            {coin.last_trade_price}
                                        </td>
                                    </tr>
                                )}

                            </tbody>
                        </Table>
                    </div>
                </header>
            </div>


        );
    }
}

export default App;