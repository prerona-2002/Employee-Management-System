import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/employees/"

export const listEmployees = () => {
    return axios.get(REST_API_BASE_URL+"getAllEmployees");
}

export const creatEmployee = (employee) =>{
    return axios.post(REST_API_BASE_URL+"createEmployee",employee)
}

export const getEmployee = (employeeId) =>{
    return axios.get(REST_API_BASE_URL+"getEmployee/"+employeeId)
}

export const updateEmployee = (employeeId,employee) =>{
    return axios.put(REST_API_BASE_URL+"editEmployee/"+employeeId,employee)
}

export const deleteEmployee = (employeeId) =>{
    return axios.delete(REST_API_BASE_URL+"deleteEmployee/"+employeeId)
}