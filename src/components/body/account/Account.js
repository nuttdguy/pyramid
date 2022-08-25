import React from "react";
import Jumbotron from "../../jumbotron/Jumbotron";
import Avatar from "./avatar/avatar";
import Detail from "./detail/Detail";

class Account extends React.Component {

    constructor(props) {
        super(props);
        this.title = "Account Settings";
    }

    render() {
        return (
            <div className="container">
                <div className="row gutters">

                    <Jumbotron title={this.title} />
                    <hr />
                    <Avatar />
                    <Detail />

                </div>
            </div>
        )
    }
}

export default Account;