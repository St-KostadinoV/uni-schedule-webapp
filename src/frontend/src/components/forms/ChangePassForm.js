import Form from './Form'

const ChangePassForm = () => {
    return (
        <Form>
          <div className='form-fields'>
            <p>Текуща парола: <input type="text" /></p>
            <p>Нова парола: <input type="text" /></p>
            <p>Потвърдете паролата: <input type="text" /></p>
          </div>
          <input type="submit"/>
        </Form>
    )
}

export default ChangePassForm