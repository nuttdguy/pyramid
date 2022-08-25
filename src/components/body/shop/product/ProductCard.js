import React from "react";
import SearchBar from "../search/SearchBar";
import SideBar from "../sidebar/SideBar";

function ProductCard() {
    return (
        <React.Fragment>

            <section className="row justify-content-end">
                <div className="col col-md-3 g-2">
                    <SearchBar />
                </div>
            </section>

            <section className="row">

                <SideBar />

                <div className="col col-md-3 g-2">
                    <article className="card border-dark">
                        <img src="https://api.lorem.space/image/drink?w=150&h=150&hash=4F32C4CF"
                            className="card-img-top" alt="#" />
                        <dl className="card-body">
                            <dt className="card-title">Product title</dt>
                            <dd className="card-text">Product description</dd>
                            <dt className="card-title">Price</dt>
                            <dd className="card-text">$5.00</dd>
                        </dl>
                    </article>
                </div>
                <div className="col col-md-3 g-2">
                    <article className="card border-dark">
                        <img src="https://api.lorem.space/image/drink?w=150&h=150&hash=8B7BCDC2"
                            className="card-img-top" alt="#" />
                        <dl className="card-body">
                            <dt className="card-title">Product title</dt>
                            <dd className="card-text">Product description</dd>
                            <dt className="card-title">Price</dt>
                            <dd className="card-text">$6.99</dd>
                        </dl>
                    </article>
                </div>
                <div className="col col-md-3 g-2">
                    <article className="card border-dark">
                        <img src="https://api.lorem.space/image/drink?w=150&h=150" className="card-img-top" alt="#" />
                        <dl className="card-body">
                            <dt className="card-title">Product title</dt>
                            <dd className="card-text">Product description</dd>
                            <dt className="card-title">Price</dt>
                            <dd className="card-text">$4.74</dd>
                        </dl>
                    </article>
                </div>
            </section>
        </React.Fragment>
    )
}

export default ProductCard;