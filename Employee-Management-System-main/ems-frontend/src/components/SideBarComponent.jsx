import FeatherIcon from 'feather-icons-react';
import { Link } from "react-router-dom"

export const SideBarComponent = () => {

    return (
        <div className="container-fluid">
            <div className="row">
                <nav id="sidebarMenu" className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                    <div className="position-sticky pt-3">
                        <ul className="nav flex-column">
                            <li className="nav-item" style={{ border: "none" }}>
                                <Link className="nav-link" aria-current="page" to="/">
                                    <FeatherIcon icon="home" />
                                    Dashboard
                                </Link>
                            </li>
                        </ul>
                        <h6 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>Employess</span>
                            <FeatherIcon icon='chevron-right' />
                        </h6>
                        <ul className="nav flex-column">
                            <li className="nav-item" style={{ border: "none" }}>
                                <Link className="nav-link" aria-current="page" to="/add-employee">
                                    <FeatherIcon icon="user-plus" />
                                    Add Employees
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    )
}
