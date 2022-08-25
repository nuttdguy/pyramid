
const Jumbotron = (props) => {

    return (
        <section class="row pt-3">
            <div class="jumbotron jumbotron-fluid g-0">
                <div class="container">
                    <h1 class="display-4">{props.title}</h1>
                    <p class="lead">{props.description}</p>
                </div>
            </div>
        </section>
    )
}

export default Jumbotron;