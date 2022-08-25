import React from "react";

function SideBar(props) {
    return (
        <React.Fragment>
           {/* <!-- some side bar option --> */}
            <section className="col col-md-3">
                <nav className="nav flex-column">
                    <h6>Drink Category</h6>
                    <li className="nav-item">
                        <a className="nav-link active" href="index.html">Toxic</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link active" href="index.html">Rum</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="index.html">Vodka</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="index.html">Good</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="index.html">Yummy</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="index.html">Delicious</a>
                    </li>
                </nav>
            </section>
        </React.Fragment>
    )
}

export default SideBar;