import Form from './Form'

const ChangePassForm = ({ onSubmit }) => {
    return (
        <Form onSubmit={onSubmit}>
          <div className='form-fields'>
            <p>Текуща парола: <input type="text" /></p>
            <p>Нова парола: <input type="text" /></p>
            <p>Потвърдете паролата: <input type="text" /></p>
          </div>
          <input type="submit" value="Потвърди"/>
        </Form>
    )
}

export default ChangePassForm