import { useEffect, useState } from "react"
import { deleteEmployee, listEmployees } from "../services/EmployeeService"
import { useNavigate } from "react-router-dom"
import 'bootstrap-icons/font/bootstrap-icons.css'

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([])
    const navigator = useNavigate()

    function getAllEmployees() {
        listEmployees().then((response) => {
            setEmployees(response.data)
        }).catch(error => {
            console.error(error)
        })
    }

    useEffect(() => {
        getAllEmployees()
    }, [])

    function addNewEmployee() {
        navigator('/add-employee')
    }

    function updateEmployee(id) {
        navigator(`/edit-employee/${id}`)
    }

    function viewEmployee(id) {
        navigator(`/view-employee/${id}`)
    }

    function removeEmployee(id) {
        console.log(id)
        deleteEmployee(id).then((response) => {
            console.log(response.data)
            getAllEmployees()
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4"  style={{paddingBottom:50+"px"}}>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 className="h2">Manage Employees</h1>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <div className="card">
                        <div className="card-body">
                            <button className="btn btn-primary mb-2 btn-lg rounded-pill shadow-sm" onClick={addNewEmployee}
                                style={{
                                    backgroundColor: '#4CAF50', // Primary theme color
                                    borderColor: '#4CAF50',
                                    color: '#FFFFFF',
                                    transition: 'all 0.3s ease'
                                }}>
                                <i className="bi bi-plus-lg"></i> Add Employee
                            </button>
                            <hr></hr>
                            <div className="table-responsive">
                                <table className="table table-striped " style={{ borderColor: "#ffffff" }}>
                                    <thead>
                                        <tr>
                                            <th>Employee Id</th>
                                            <th>Employee First Name</th>
                                            <th>Employee Last Name</th>
                                            <th>Employee Email Id</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            employees.map(employee =>
                                                <tr key={employee.id}>
                                                    <td>{employee.id}</td>
                                                    <td>{employee.firstName}</td>
                                                    <td>{employee.lastName}</td>
                                                    <td>{employee.email}</td>
                                                    <td>
                                                        <button className="btn btn-custom btn-custom-update" onClick={() => updateEmployee(employee.id)}>
                                                            Update
                                                        </button>
                                                        <button className="btn btn-custom btn-custom-delete" onClick={() => removeEmployee(employee.id)}>
                                                            Delete
                                                        </button>
                                                        <button className="btn btn-custom btn-custom-view" onClick={() => viewEmployee(employee.id)}>
                                                            View
                                                        </button>
                                                    </td>
                                                </tr>
                                            )
                                        }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    )
}

export default ListEmployeeComponent