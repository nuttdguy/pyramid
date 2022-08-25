
const Jumbotron = (props) => {

    return (
        <section className="row pt-3">
            <div className="jumbotron jumbotron-fluid g-0">
                <div className="container">
                    <h1 className="display-4">{props.title}</h1>
                    <p className="lead">{props.description}</p>
                </div>
            </div>
        </section>
    )
}

export default Jumbotron;