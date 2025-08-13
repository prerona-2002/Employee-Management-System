import { Link } from "react-router-dom"

const HeaderComponent = () => {
  return (
    <header className="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
      <Link className="navbar-brand col-md-3 col-lg-2 me-0 px-3" to="/">
        <img src={"/logo.png"} style={{ height: "40px", width: "40px", marginRight: "4px", marginTop: "-2px" }} />
        EmpManage
      </Link>

      <button className="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="navbar-nav" style={{ display: "flex", alignItems: "center", flexDirection:"row"}}>
        <div className="nav-item text-nowrap">
          <Link className="nav-link px-3" to="/">
            <img src={"/user.png"} style={{ height: "40px", width: "40px", marginRight: "4px", marginTop: "-2px", borderRadius: "50%" }} />
            Admin User
          </Link>
        </div>
        <div className="nav-item text-nowrap">
          <Link className="nav-link px-3" to="/">Sign out</Link>
        </div>
      </div>
    </header>

  )
}

export default HeaderComponent