import {Link} from "react-router-dom";

function Nav() {

    return (
        <section className="row">
            <div className="col col-md-8 g-0"></div>
            <nav className="col col-md-4 g-0">
                <ul className="list-group list-group-horizontal">
                    <li className="list-group-item"><Link to="/" >Shop </Link></li>
                    <li className="list-group-item"><Link to="/checkout" >Checkout </Link></li>
                    <li className="list-group-item"><Link to="/account" >Account </Link></li>
                    <li className="list-group-item"><Link to="/" >Login / Logout </Link></li>
                </ul>
            </nav>
        </section>
    )
}

export default Nav;