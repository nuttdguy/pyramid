import Shop from "./shop/Shop";
import Checkout from "./checkout/Checkout";
import Account from "./account/Account";
import {Routes, Route} from "react-router-dom";

function Body() {

    return (
        <Routes>
          <Route path="/" element={<Shop />} />
          <Route path="/account" element={<Account />} />
          <Route path="/checkout" element={<Checkout />} />
        </Routes>
    )
    
}

export default Body;