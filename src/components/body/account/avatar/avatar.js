import React from "react";

function Avatar(props) {
    return (
        <React.Fragment>
            <div className="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                <div className="card h-100">
                    <div className="card-body">
                        <div className="account-settings">
                            <div className="user-profile">
                                <div className="user-avatar">
                                    <img src="https://api.lorem.space/image/face?w=150&h=150&hash=4F32C4CF" alt="" />
                                </div>
                                <h5 className="user-name">username</h5>
                                <h6 className="user-email">username@example.com</h6>
                            </div>
                            <div className="about">
                                <h5 className="mb-2 text-primary">About</h5>
                                <p>This is about me ..</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </React.Fragment>
    )
}

export default Avatar;