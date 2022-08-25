import React from "react"
import InputFieldWithLabel from "../../../common/input-fields/InputFieldWithLabel";

function BillingDetail(props) {

    return (
        <React.Fragment>

            <h4 className="mb-3">Billing address</h4>

            {/* <!-- first name, last name--> */}
            <div className="row">
                <div className="col-md-6 mb-3">
                    <InputFieldWithLabel 
                        htmlFor={"firstName"}
                        label={"First name"}
                        type={"text"}
                        clazz={"form-control"}
                        id={"firstName"}
                        maxLength={"20"}
                        pattern={"[A-Za-z]{20}+$"} />
                </div>
                <div className="col-md-6 mb-3">
                    <InputFieldWithLabel 
                        htmlFor={"lastName"}
                        label={"Last name"}
                        type={"text"}
                        clazz={"form-control"}
                        id={"lastName"}
                        maxLength={"20"}
                        pattern={"[A-Za-z]{20}+$"} />
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

        </React.Fragment>
    )


}

export default BillingDetail;