const SidePanel = (props) => {
    return (
        <div className='side-menu'>
            <h3>МЕНЮ</h3>
            {props.children}
        </div>
    )
}

export default SidePanel
