import { Card, CardBody, CardHeader, Form, Col, Container, FormGroup, Input, Label, Row, Button } from "reactstrap";
import Base from "../components/Base";

const Login=()=>{
    return(
        <Base>
            <Container>
                <Row>
                    <Col sm={{size:6, offset:3}}>
                        <Card className="mt-5" color="secondary" inverse>
                            <CardHeader>
                                <h3>Login Here</h3>
                            </CardHeader>
                            <CardBody>
                                <Form>
                                    <FormGroup>
                                        <Label for="email">Enter Email</Label>
                                        <Input placeholder="Enter here" type="text" id="password" />
                                    </FormGroup>

                                    <FormGroup>
                                        <Label for="password">Enter Password</Label>
                                        <Input placeholder="Enter here" type="password" id="password" />
                                    </FormGroup>

                                    <Container className="text-center">
                                        <Button outline color="dark">Login</Button>
                                        <Button outline className="ms-2" color="warning">Reset</Button>
                                    </Container>
                                </Form>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </Base>

        
    );
};

export default Login;