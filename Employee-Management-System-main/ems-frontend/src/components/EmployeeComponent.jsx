import { useEffect, useState } from "react"
import { creatEmployee, getEmployee, updateEmployee } from "../services/EmployeeService"
import { useNavigate, useParams } from "react-router-dom"

const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')

    const { id } = useParams()

    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: ''
    })

    function validateForm() {
        let valid = true
        const errorCopy = { ...errors }
        if (firstName.trim()) {
            errorCopy.firstName = ''
        } else {
            errorCopy.firstName = "First name is required"
            valid = false
        }
        if (lastName.trim()) {
            errorCopy.lastName = ''
        } else {
            errorCopy.lastName = "Last name is required"
            valid = false
        }
        if (email.trim()) {
            errorCopy.email = ''
        } else {
            errorCopy.email = "Email is required"
            valid = false
        }
        setErrors(errorCopy)
        return valid;
    }

    const navigator = useNavigate();

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

    function saveOrUpdateEmployee(e) {
        e.preventDefault()
        if (validateForm()) {
            const employee = { firstName, lastName, email }
            console.log(employee)
            if (id) {
                updateEmployee(id, employee).then((response) => {
                    console.log(response.data)
                    navigator('/employees')
                }).catch(error => {
                    console.log(error)
                    console.log(error.data)
                })
            } else {
                creatEmployee(employee).then((response) => {
                    console.log(response.data)
                    navigator('/employees')
                }).catch(error => {
                    console.error(error)
                })
            }
        }

    }

    function pageTitle() {
        if (id) {
            return <h2 className="text-center"> Update Employee</h2>
        }
        else
            return <h2 className="text-center"> Add Employee</h2>
    }

    return (
        <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4" style={{padding: 10+"px"}} >
            <div className="row justify-content-center" style={{padding:'10px'}}>
                <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    {pageTitle()}
                </div>
                <div className="card col-md-6" style={{ marginTop: '10vh' , padding: '10px'}}>
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-3">
                                <label className="form-label">First Name:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Employee First Name"
                                    name="firstName"
                                    value={firstName}
                                    className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                    onChange={(e) => setFirstName(e.target.value)}
                                />
                                {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                            </div>
                            <div className="form-group mb-3">
                                <label className="form-label">Last Name:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Employee Last Name"
                                    name="lastName"
                                    value={lastName}
                                    className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                    onChange={(e) => setLastName(e.target.value)}
                                />
                                {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                            </div>
                            <div className="form-group mb-3">
                                <label className="form-label">Email:</label>
                                <input
                                    type="email"
                                    placeholder="Enter Employee Email"
                                    name="email"
                                    value={email}
                                    className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                            </div>
                            <button type="button" className="btn btn-success w-100" onClick={saveOrUpdateEmployee}
                                style={{
                                    backgroundColor: '#4CAF50', // Primary theme color
                                    borderColor: '#4CAF50',
                                    color: '#FFFFFF',
                                    transition: 'all 0.3s ease',
                                }}>
                                Submit
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    )
}

export default EmployeeComponent