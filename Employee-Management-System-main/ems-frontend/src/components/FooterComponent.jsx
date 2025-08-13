import FeatherIcon from 'feather-icons-react';

const FooterComponent = () => {
    return (

        <div className="footer" style={{ padding: "10px", width: "100%", display: "flex", justifyContent: "center", alignItems: "center" }}>
            <span style={{ position: "absolute", left: "60%", transform: "translateX(-60%)" }}>
                Made with <span style={{ color: "#FF0000" }}> &hearts;</span> by Biparnak Roy
            </span>
            <span style={{ position: "absolute", left: "67%", transform: "translateX(-67%)" }}>
                <a href="https://github.com/biparnakroy" style={{ color: "#FFFFFF", marginLeft: 20 + "px" }}><FeatherIcon icon="github" size={16} /></a>
                <a href="https://www.linkedin.com/in/biparnakroy" style={{ color: "#FFFFFF", marginLeft: 20 + "px" }}><FeatherIcon icon="linkedin" size={16} /></a>
            </span>
            <span id="version" style={{ marginLeft: "auto" }}>v1.0.0</span>
        </div>
    )
}

export default FooterComponent