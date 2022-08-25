import Jumbotron from "../../jumbotron/Jumbotron";
import BillingDetail from "./billing/BillingDetail";
import PaymentDetail from "./payment/PaymentDetail";
import PaymentOption from "./payment/PaymentOption";
import Cart from "./cart/Cart";
import React from "react";


class Checkout extends React.Component {

    constructor(props) {
        super(props)
        this.title = "Checkout";
        this.description = "Place your order ...";
    }


    render() {
        return (
            <section className="container">

            <div className="row">
                
                {/* <!-- checkout section --> */}
                <Jumbotron title={this.title} description={this.description} />
                <hr />

                {/* <!-- section cart --> */}
                <section className="col-md-4 order-md-2 mb-4">
                    <Cart />
                </section>

                {/* <!-- section billing and payment --> */}
                <div className="col-md-8 order-md-1">
                    <form>
                        
                        {/* <!-- payment details. name on card, card number, expiration / ccv row --> */}                
                        <BillingDetail />
                        <PaymentOption />       
                        <PaymentDetail />      

                    </form>

                </div>
            </div>
{/*             
            <footer className="my-5 pt-5 text-muted text-center text-small">
                    <p className="mb-1">© 2022 Drink Frenzy</p>
            </footer> */}

        </section>

        )
    }
}

export default Checkout;