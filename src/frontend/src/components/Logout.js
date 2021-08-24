import Card from "./cards/Card";

const Logout = () => {
    return (
        <div className='centered-content' style={{paddingTop: '80px'}}>
            <Card>
                <h2 style={{margin: '32px 160px', textAlign: 'center'}}>Успешно излязохте от системата!</h2>
            </Card>
        </div>
    )
}

export default Logout;