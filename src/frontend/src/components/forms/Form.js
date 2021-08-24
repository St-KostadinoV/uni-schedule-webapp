import '../../styles/form.css'

const Form = ({ children, style, onSubmit }) => {
    return (
        <div className='form' style={style}>
            <form onSubmit={onSubmit}>
                {children}
            </form>
        </div>
    )
}

export default Form
