import './App.css'
import './css/dashboard.css'
import './css/buttons.css'
import './css/table.css'
import './css/footer.css'
import './css/form.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { SideBarComponent } from './components/SideBarComponent'
import ViewEmployeeComponent from './components/ViewEmployeeComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <SideBarComponent/>
        <Routes>
          <Route path="/" element={<ListEmployeeComponent/>}></Route>
          <Route path="/employees" element={<ListEmployeeComponent/>}></Route>
          <Route path="/add-employee" element={<EmployeeComponent/>}></Route>
          <Route path="/edit-employee/:id" element={<EmployeeComponent/>}></Route>
          <Route path="/view-employee/:id" element={<ViewEmployeeComponent/>}></Route>
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
