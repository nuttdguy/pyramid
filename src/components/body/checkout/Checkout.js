import Jumbotron from "../../jumbotron/Jumbotron";

function Checkout() {
    let context = {
        title: "Checkout",
        description: "Place your order ..."
    }

    return (

        <section class="container">
            <div class="row">
                
                {/* <!-- checkout section --> */}
                <Jumbotron title={context.title} description={context.description} />
                <hr />

                {/* <!-- section cart --> */}
                <section class="col-md-4 order-md-2 mb-4">

                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Your cart</span>
                        <span class="badge badge-secondary badge-pill">3</span>
                    </h4>
                    <ul class="list-group mb-3 sticky-top">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 class="my-0">Product name</h6>
                                <small class="text-muted">Brief description</small>
                            </div>
                            <span class="text-muted">$12</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 class="my-0">Second product</h6>
                                <small class="text-muted">Brief description</small>
                            </div>
                            <span class="text-muted">$8</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 class="my-0">Third item</h6>
                                <small class="text-muted">Brief description</small>
                            </div>
                            <span class="text-muted">$5</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between bg-light">
                            <div class="text-success">
                                <h6 class="my-0">Promo code</h6>
                                <small>EXAMPLECODE</small>
                            </div>
                            <span class="text-success">-$5</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Total (USD)</span>
                            <strong>$20</strong>
                        </li>
                    </ul>
                    <form class="card p-2">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Promo code" />
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary">Redeem</button>
                            </div>
                        </div>
                    </form>
                </section>

                {/* <!-- section billing and payment --> */}

                <div class="col-md-8 order-md-1">
                    <h4 class="mb-3">Billing address</h4>

                    <form>
                        {/* <!-- first name, last name--> */}
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First name</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="firstName" 
                                    maxlength="20"
                                    pattern="\^[A-Za-z]{20}+$"
                                    placeholder="" 
                                    required />
                                <div class="invalid-feedback"> Valid first name is required. </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last name</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="lastName" 
                                    maxlength="20"
                                    pattern="\^[A-Za-z]{20}+$"
                                    placeholder="" 
                                    required />
                                <p class="invalid-feedback"> Valid last name is required. </p>
                            </div>
                        </div>

                        {/* username */}
                        <div class="row" >
                            <div class="mb-3">
                                <label for="username">Username</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">@</span>
                                    </div>
                                    <input type="text" 
                                        class="form-control" 
                                        id="username" 
                                        maxlength="20"
                                        placeholder="Username" 
                                        required  />
                                </div>
                            </div>
                        </div>

                        {/* <!-- email address --> */}
                        <div class="row" >
                            <div class="mb-3">
                                <div class="mb-3">
                                    <label for="email">Email</label>
                                    <input type="email" 
                                        class="form-control" 
                                        id="email" 
                                        placeholder="you@example.com"
                                        pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"
                                        required />
                                    <div class="invalid-feedback"> Please enter a valid email address for shipping updates. </div>
                                </div>
                            </div>
                        </div>

                        {/* <!-- address, address 2--> */}
                        <div class="row">
                            <div class="mb-3">
                                <label for="address">Address</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="address" 
                                    placeholder="1234 Main St" 
                                    required />
                                <div class="invalid-feedback"> Please enter your address. </div>
                            </div>

                            <div class="mb-3">
                                <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
                                <input type="text" 
                                    class="form-control" 
                                    id="address2" 
                                    placeholder="Apartment or suite" />
                            </div>
                        </div>

                        {/* <!-- country, country options, state, state options, zip --> */}
                        <div class="row">

                             <div class="col-md-5 mb-3">
                                <label for="country">Country</label>
                                <select class="custom-select d-block w-100" 
                                    id="country" 
                                    required >

                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                                <div class="invalid-feedback"> Please select a valid country. </div>
                            </div>

                            <div class="col-md-4 mb-3">
                                <label for="state">State</label>
                                <select class="custom-select d-block w-100" 
                                    id="state" 
                                    required>
                                    
                                    <option value="">Choose...</option>
                                    <option>California</option>
                                    <option>Minnesota</option>
                                </select>
                                <div class="invalid-feedback"> Please provide a valid state. </div>
                            </div>

                            <div class="col-md-3 mb-3">
                                <label for="zip">Zip</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="zip" 
                                    pattern="[0-9]{5}"
                                    maxlength="5"
                                    placeholder="55555"
                                    required />
                                <div class="invalid-feedback"> Zip code required. </div>
                            </div>
                        </div>

                        {/* <!-- payment type, payment options --> */}
                        <div class="row" >

                            <hr class="mb-4" />
                            <h4 class="mb-3">Payment</h4>

                            <div class="d-block my-3">
                                <div class="custom-control custom-radio">
                                    <input id="credit" 
                                        name="paymentMethod" 
                                        type="radio" 
                                        class="custom-control-input" 
                                        required />
                                    <label class="custom-control-label" for="credit">Credit card</label>
                                </div>

                                <div class="custom-control custom-radio">
                                    <input id="debit" 
                                        name="paymentMethod" 
                                        type="radio" 
                                        class="custom-control-input" 
                                        required />
                                    <label class="custom-control-label" for="debit">Debit card</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input id="paypal" 
                                        name="paymentMethod" 
                                        type="radio" 
                                        class="custom-control-input" 
                                        required />
                                    <label class="custom-control-label" for="paypal">PayPal</label>
                                </div>
                            </div>

                        </div>

                        {/* <!-- payment details. name on card, card number --> */}
                        <div class="row">

                            <div class="col-md-6 mb-3">
                                <label for="cc-name">Name on card</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="cc-name" 
                                    maxlength="40"
                                    required />
                                <small class="text-muted">Full name as displayed on card</small>
                                <div class="invalid-feedback"> Name on card is required </div>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="cc-number">Credit card number</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="cc-number" 
                                    pattern="[0-9]{16}"
                                    maxlength="16"
                                    placeholder="1111 2222 3333 4444"
                                    required />
                                <div class="invalid-feedback"> Credit card number is required </div>
                            </div>
                        </div>

                        {/* <!-- expiration / ccv row --> */}
                        <div class="row">

                            <div class="col-md-3 mb-3">
                                <label for="cc-expiration">Expiration</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="cc-expiration" 
                                    pattern="[0-9]{4}"
                                    maxlength="4"
                                    required />
                                <div class="invalid-feedback"> Expiration date required </div>
                            </div>

                            <div class="col-md-3 mb-3">
                                <label for="cc-cvv">CVV</label>
                                <input type="text" 
                                    class="form-control" 
                                    id="cc-cvv" 
                                    pattern="[0-9]{3}"
                                    maxlength="3"
                                    required />
                                <div class="invalid-feedback"> Security code required </div>
                            </div>

                        </div>

                        {/* <!-- submit --> */}
                        <div class="row" >
                            <hr class="mb-4"/>
                            <button class="btn btn-info btn-lg btn-block" type="submit">Continue to checkout</button>
                        </div>

                    </form>

                </ div>
            </div>
            
            <footer class="my-5 pt-5 text-muted text-center text-small">
                    <p class="mb-1">© 2022 Drink Frenzy</p>
            </footer>

        </section>

    )
}

export default Checkout;