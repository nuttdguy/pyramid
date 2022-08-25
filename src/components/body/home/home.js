import Jumbotron from "../../jumbotron/Jumbotron";


function Home() {
    let context = {
        title: "Drink Frenzy",
        description: "Welcome to the place where drinks are fresh, fabulous and frezlicious!"
    }

    return (
        <div>
            <Jumbotron title={context.title} description={context.description}/>
            <section className="col col-md-9">

                <section className="row">
                    <nav className="navbar navbar-expand-lg ">
                        <div className="container-fluid">
                            <form className="d-flex" role="search">
                                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </nav>
                </section>


                <section className="row">
                    <div className="col col-md-4 g-2">
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
                    <div className="col col-md-4 g-2">
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
                    <div className="col col-md-4 g-2">
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


            </section>
        </div>
    )
}

export default Home;