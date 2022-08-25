import React from "react";
import Jumbotron from "../../jumbotron/Jumbotron";
import ProductCard from "./product/ProductCard";

class Shop extends React.Component {
    
    constructor(props) {
        super(props);
        this.title = "Drink Frenzy";
        this.description = "Welcome to the place where drinks are fresh, fabulous and frezlicious!";
    }   

    render() {
    return (
            <div>
                <Jumbotron title={this.title} description={this.description}/>
                <section className="col col-md-9">

                    <section className="row">
                        <ProductCard />
                    </section>


                </section>
            </div>
        )
    }
}

export default Shop;