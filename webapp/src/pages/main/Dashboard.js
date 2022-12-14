import React  from "react";
import { Link } from 'react-router-dom'
import axios from 'axios';
import base_url from '../../Components/base_url';
import { useSelector } from "react-redux";
const Home = () => {
    const[fname, setFname] = React.useState("") ;
    const user = useSelector(state => state.user);
    React.useEffect(
        () => {
            axios.get(`${base_url}/customer/id/${user.customerId}`).then(
                (response) => {
                    setFname(response.data.fname);
                },
                (error) => {
                    console.log(error);
                }
            )
    },[fname])
    return (
        <>
        <h1 className="mb-5">Welcome {fname}!</h1>
        <div style={{display: "grid", gridTemplateColumns: "1fr 1fr",gridGap: "20px", padding: "10px"}}>
            <div className="main-card">
                <div className="card-title" ><h4>Apply for Loan</h4></div>
                <div className="card-body" ><p>
                    Easily apply for loans at our registered branches! Simply enter your amount and click apply! <br />
                    <Link to="/loan">Apply</Link>
                </p></div>
            </div>
            <div className="main-card">
                <div className="card-title" ><h4>Transact</h4></div>
                <div className="card-body" ><p>
                    With our easy menu, deposits and withdrawals are easier than ever! <br />
                    <Link to="/transaction">Transact</Link>
                </p></div>
            </div>
            <div className="main-card">
                <div className="card-title" ><h4>Generate statement</h4></div>
                <div className="card-body" ><p>
                    Just enter the time period and leave the rest to us! <br />
                    <Link to="/statement">Generate</Link>
                </p></div>
            </div>

        </div>
        </>
    );
}

export default Home;