import Jumbotron from "../../jumbotron/Jumbotron";


function Home() {
    let context = {
        title: "Drink Frenzy",
        description: "Welcome to the place where drinks are fresh, fabulous and frezlicious!"
    }

    return (
        <div>
            <Jumbotron title={context.title} description={context.description}/>
            <section class="col col-md-9">

                <section class="row">
                    <nav class="navbar navbar-expand-lg ">
                        <div class="container-fluid">
                            <form class="d-flex" role="search">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </nav>
                </section>


                <section class="row">
                    <div class="col col-md-4 g-2">
                        <article class="card border-dark">
                            <img src="https://api.lorem.space/image/drink?w=150&h=150&hash=4F32C4CF"
                                class="card-img-top" alt="#" />
                            <dl class="card-body">
                                <dt class="card-title">Product title</dt>
                                <dd class="card-text">Product description</dd>
                                <dt class="card-title">Price</dt>
                                <dd class="card-text">$5.00</dd>
                            </dl>
                        </article>
                    </div>
                    <div class="col col-md-4 g-2">
                        <article class="card border-dark">
                            <img src="https://api.lorem.space/image/drink?w=150&h=150&hash=8B7BCDC2"
                                class="card-img-top" alt="#" />
                            <dl class="card-body">
                                <dt class="card-title">Product title</dt>
                                <dd class="card-text">Product description</dd>
                                <dt class="card-title">Price</dt>
                                <dd class="card-text">$6.99</dd>
                            </dl>
                        </article>
                    </div>
                    <div class="col col-md-4 g-2">
                        <article class="card border-dark">
                            <img src="https://api.lorem.space/image/drink?w=150&h=150" class="card-img-top" alt="#" />
                            <dl class="card-body">
                                <dt class="card-title">Product title</dt>
                                <dd class="card-text">Product description</dd>
                                <dt class="card-title">Price</dt>
                                <dd class="card-text">$4.74</dd>
                            </dl>
                        </article>
                    </div>
                </section>


            </section>
        </div>
    )
}

export default Home;