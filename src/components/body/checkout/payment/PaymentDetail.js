import React from "react";

function PaymentDetail(props) {
    return (
        <React.Fragment>
            <div className="row">
                <div className="col-md-6 mb-3">
                    <label htmlFor="cc-name">Name on card</label>
                    <input type="text" 
                        className="form-control" 
                        id="cc-name" 
                        maxLength="40"
                        required />
                    <small className="text-muted">Full name as displayed on card</small>
                    <div className="invalid-feedback"> Name on card is required </div>
                </div>

                <div className="col-md-6 mb-3">
                    <label htmlFor="cc-number">Credit card number</label>
                    <input type="text" 
                        className="form-control" 
                        id="cc-number" 
                        pattern="[0-9]{16}"
                        maxLength="16"
                        placeholder="1111 2222 3333 4444"
                        required />
                    <div className="invalid-feedback"> Credit card number is required </div>
                </div>
            </div>

            {/* <!-- expiration / ccv row --> */}
            <div className="row">

                <div className="col-md-3 mb-3">
                    <label htmlFor="cc-expiration">Expiration</label>
                    <input type="text" 
                        className="form-control" 
                        id="cc-expiration" 
                        pattern="[0-9]{4}"
                        maxLength="4"
                        required />
                    <div className="invalid-feedback"> Expiration date required </div>
                </div>

                <div className="col-md-3 mb-3">
                    <label htmlFor="cc-cvv">CVV</label>
                    <input type="text" 
                        className="form-control" 
                        id="cc-cvv" 
                        pattern="[0-9]{3}"
                        maxLength="3"
                        required />
                    <div className="invalid-feedback"> Security code required </div>
                </div>

            </div>

            {/* <!-- submit --> */}
            <div className="row" >
                <hr className="mb-4"/>
                <button className="btn btn-info btn-lg btn-block" type="submit">Continue to checkout</button>
            </div>

        </React.Fragment>
    )
}

export default PaymentDetail;