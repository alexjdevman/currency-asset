import * as React from 'react';

const API_RATE = "https://api.exchangeratesapi.io/";

export default class CurrencyAssetForm extends React.Component {
    state = {
        rateDate: "",
        amount: 0.0,
        profit: 0.0
    };

    onChangeRateDate = (evt) => {
        this.setState({
            rateDate: evt.target.value
        });
    };

    onChangeAmount = (evt) => {
        this.setState({
            amount: evt.target.value
        });
    };

    recalculate = () => {
        let currentRate;
        const currentRateURL = API_RATE + 'latest?base=USD&symbols=RUB';
        const dateRateURL = API_RATE + this.state.rateDate + '?base=USD&symbols=RUB';
        fetch(currentRateURL)
            .then(data => data.json())
            .then((data) => {
                currentRate = data.rates.RUB;
                return fetch(dateRateURL);
            })
            .then(data => data.json())
            .then((data) => {
                const rate = data.rates.RUB;
                return fetch('/profit?amount=' + this.state.amount + '&rate=' + rate + '&currentRate=' + currentRate)
            })
            .then(data => data.json())
            .then((data) => {
                this.setState({profit: data})
            });
    };

    render() {
        return (
            <form>
                <h3>Currency asset calculator</h3>
                <div className="form-row">
                    <div className="col">
                        <label>Date:</label>
                        <input type="date" className="form-control" id="date" value={this.state.rateDate}
                               onChange={this.onChangeRateDate}/>
                    </div>
                    <div className="col">
                        <label>Amount (USD):</label>
                        <input type="number" className="form-control" id="amount" value={this.state.amount}
                               onChange={this.onChangeAmount}/>
                    </div>
                    <div className="col">
                        <label>Profit (RUB):</label>
                        <input readOnly type="number" className="form-control" id="profit" value={this.state.profit}/>
                    </div>
                </div>
                <button style={{marginTop: '10px'}} type="button" className="btn btn-primary"
                        disabled={this.state.amount <= 0.0 || !this.state.rateDate || new Date(this.state.rateDate).getTime() >= new Date().getTime()}
                        onClick={this.recalculate}>
                    Recalculate
                </button>
            </form>
        );
    }
}