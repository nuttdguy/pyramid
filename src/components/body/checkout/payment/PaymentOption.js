import React from "react"

function PaymentOption() {
    return (
        <React.Fragment>
            {/* <!-- payment type, payment options --> */}
            <div className="row" >

                <hr className="mb-4" />
                <h4 className="mb-3">Payment</h4>

                <div className="d-block my-3">
                    <div className="custom-control custom-radio">
                        <input id="credit" 
                        name="paymentMethod" 
                        type="radio" 
                        className="custom-control-input" 
                        required />
                        <label className="custom-control-label" htmlFor="credit">Credit card</label>
                    </div>

                    <div className="custom-control custom-radio">
                        <input id="debit" 
                        name="paymentMethod" 
                        type="radio" 
                        className="custom-control-input" 
                        required />
                        <label className="custom-control-label" htmlFor="debit">Debit card</label>
                    </div>


                    <div className="custom-control custom-radio">
                        <input id="paypal" 
                        name="paymentMethod" 
                        type="radio" 
                        className="custom-control-input" 
                        required />
                        <label className="custom-control-label" htmlFor="paypal">PayPal</label>
                    </div>
                </div>
                <hr />
            </div>
        </React.Fragment>
    )
}

export default PaymentOption;