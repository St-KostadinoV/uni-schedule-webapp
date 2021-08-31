import '../../styles/form.css'

const FilterForm = ({ children, style }) => {
    return (
        <div className='filter-form' style={style}>
            {children}
        </div>
    )
}

export default FilterForm