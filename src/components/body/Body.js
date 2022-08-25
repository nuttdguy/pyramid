import Home from "./home/Home";
import Checkout from "./checkout/Checkout";
import Account from "./account/Account";
import {Routes, Route} from "react-router-dom";

function Section() {

    return (
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/account" element={<Account />} />
          <Route path="/checkout" element={<Checkout />} />
        </Routes>
    )
    
}

export default Section;