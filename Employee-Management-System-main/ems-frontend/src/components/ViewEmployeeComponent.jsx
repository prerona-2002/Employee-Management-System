import { useEffect, useState } from "react"
import { getEmployee } from "../services/EmployeeService"
import { useParams } from "react-router-dom"


const ViewEmployeeComponent = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')

    const { id } = useParams()

    useEffect(() => {
        if (id) {
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName)
                setLastName(response.data.lastName)
                setEmail(response.data.email)
            }).catch(error => {
                console.error(error)
            })
        }
    }, [id])

    function pageTitle() {
        return <h3 className="text-center"> Viewing Employee : {firstName + ` ` + lastName}</h3>
    }

    return (
        <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div className="row" style={{ paddingTop: 10 + "vh" }}>
                <div className="card col-md-6 offset-md-3 offset-md-3" style={{padding:10+"px"}} >
                    {
                        pageTitle()
                    }
                    <hr />
                    <div className="card-body">
                        <div className="row">
                            <div className="col" style={{display:"flex", justifyContent:"center", alignContent:"center"}}>
                            <img src={"/user.png"} style={{ height: 200 + "px", width: 200 + "px", marginRight: 4 + "px", marginTop: -2 + "px", borderRradius: 50 + "%" }} />
                            </div>
                            <div className="col" style={{fontSize: 1.5+"rem"}}>
                                First Name : {firstName}<br />
                                Last Name : {lastName}<br />
                                Email : {email}
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </main>
    )
}

export default ViewEmployeeComponent