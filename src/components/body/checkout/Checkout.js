import Jumbotron from "../../jumbotron/Jumbotron";

function Checkout() {
    let context = {
        title: "Checkout",
        description: "Place your order ..."
    }

    return (

        <section className="container">
            <div className="row">
                
                {/* <!-- checkout section --> */}
                <Jumbotron title={context.title} description={context.description} />
                <hr />

                {/* <!-- section cart --> */}
                <section className="col-md-4 order-md-2 mb-4">

                    <h4 className="d-flex justify-content-between align-items-center mb-3">
                        <span className="text-muted">Your cart</span>
                        <span className="badge badge-secondary badge-pill">3</span>
                    </h4>
                    <ul className="list-group mb-3 sticky-top">
                        <li className="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 className="my-0">Product name</h6>
                                <small className="text-muted">Brief description</small>
                            </div>
                            <span className="text-muted">$12</span>
                        </li>
                        <li className="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 className="my-0">Second product</h6>
                                <small className="text-muted">Brief description</small>
                            </div>
                            <span className="text-muted">$8</span>
                        </li>
                        <li className="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 className="my-0">Third item</h6>
                                <small className="text-muted">Brief description</small>
                            </div>
                            <span className="text-muted">$5</span>
                        </li>
                        <li className="list-group-item d-flex justify-content-between bg-light">
                            <div className="text-success">
                                <h6 className="my-0">Promo code</h6>
                                <small>EXAMPLECODE</small>
                            </div>
                            <span className="text-success">-$5</span>
                        </li>
                        <li className="list-group-item d-flex justify-content-between">
                            <span>Total (USD)</span>
                            <strong>$20</strong>
                        </li>
                    </ul>
                    <form className="card p-2">
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder="Promo code" />
                            <div className="input-group-append">
                                <button type="submit" className="btn btn-secondary">Redeem</button>
                            </div>
                        </div>
                    </form>
                </section>

                {/* <!-- section billing and payment --> */}

                <div className="col-md-8 order-md-1">
                    <h4 className="mb-3">Billing address</h4>

                    <form>
                        {/* <!-- first name, last name--> */}
                        <div className="row">
                            <div className="col-md-6 mb-3">
                                <label htmlFor="firstName">First name</label>
                                <input type="text" 
                                    className="form-control" 
                                    id="firstName" 
                                    maxLength="20"
                                    pattern="\^[A-Za-z]{20}+$"
                                    placeholder="" 
                                    required />
                                <div className="invalid-feedback"> Valid first name is required. </div>
                            </div>
                            <div className="col-md-6 mb-3">
                                <label htmlFor="lastName">Last name</label>
                                <input type="text" 
                                    className="form-control" 
                                    id="lastName" 
                                    maxLength="20"
                                    pattern="\^[A-Za-z]{20}+$"
                                    placeholder="" 
                                    required />
                                <p className="invalid-feedback"> Valid last name is required. </p>
                            </div>
                        </div>

                        {/* username */}
                        <div className="row" >
                            <div className="mb-3">
                                <label htmlFor="username">Username</label>
                                <div className="input-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text">@</span>
                                    </div>
                                    <input type="text" 
                                        className="form-control" 
                                        id="username" 
                                        maxLength="20"
                                        placeholder="Username" 
                                        required  />
                                </div>
                            </div>
                        </div>

                        {/* <!-- email address --> */}
                        <div className="row" >
                            <div className="mb-3">
                                <div className="mb-3">
                                    <label htmlFor="email">Email</label>
                                    <input type="email" 
                                        className="form-control" 
                                        id="email" 
                                        placeholder="you@example.com"
                                        pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"
                                        required />
                                    <div className="invalid-feedback"> Please enter a valid email address for shipping updates. </div>
                                </div>
                            </div>
                        </div>

                        {/* <!-- address, address 2--> */}
                        <div className="row">
                            <div className="mb-3">
                                <label htmlFor="address">Address</label>
                                <input type="text" 
                                    className="form-control" 
                                    id="address" 
                                    placeholder="1234 Main St" 
                                    required />
                                <div className="invalid-feedback"> Please enter your address. </div>
                            </div>

                            <div className="mb-3">
                                <label htmlFor="address2">Address 2 <span className="text-muted">(Optional)</span></label>
                                <input type="text" 
                                    className="form-control" 
                                    id="address2" 
                                    placeholder="Apartment or suite" />
                            </div>
                        </div>

                        {/* <!-- country, country options, state, state options, zip --> */}
                        <div className="row">

                             <div className="col-md-5 mb-3">
                                <label htmlFor="country">Country</label>
                                <select className="custom-select d-block w-100" 
                                    id="country" 
                                    required >

                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                                <div className="invalid-feedback"> Please select a valid country. </div>
                            </div>

                            <div className="col-md-4 mb-3">
                                <label htmlFor="state">State</label>
                                <select className="custom-select d-block w-100" 
                                    id="state" 
                                    required>
                                    
                                    <option value="">Choose...</option>
                                    <option>California</option>
                                    <option>Minnesota</option>
                                </select>
                                <div className="invalid-feedback"> Please provide a valid state. </div>
                            </div>

                            <div className="col-md-3 mb-3">
                                <label htmlFor="zip">Zip</label>
                                <input type="text" 
                                    className="form-control" 
                                    id="zip" 
                                    pattern="[0-9]{5}"
                                    maxLength="5"
                                    placeholder="55555"
                                    required />
                                <div className="invalid-feedback"> Zip code required. </div>
                            </div>
                        </div>

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

                        </div>

                        {/* <!-- payment details. name on card, card number --> */}
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

                    </form>

                </ div>
            </div>
            
            <footer className="my-5 pt-5 text-muted text-center text-small">
                    <p className="mb-1">© 2022 Drink Frenzy</p>
            </footer>

        </section>

    )
}

export default Checkout;