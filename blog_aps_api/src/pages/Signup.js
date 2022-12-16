import { useEffect } from "react";
import { useState } from "react";
import { signUp } from "../services/user-service";
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormFeedback, FormGroup, Input, Label, Row } from "reactstrap";
import Base from "../components/Base";
import {toast} from "react-toastify"

const Signup=()=>{
    const [data, setData]=useState({
        name:'',
        email:'',
        password:'',
        about:''
    })

    const[error, setError]=useState({
        errors:{},
        isError:true
    })

    const handleChange=(event, property)=>{
        setData({...data,[property]:event.target.value});
    }

    const submitForm=(event)=>{
        event.preventDefault();
        console.log(data);

        // if(error.isError){
        //     toast.error("Form data is invalid, correct all the details then submit");
        //     return;
        // }

        // call server api for sending data
        signUp(data).then((resp)=>{
            console.log(resp);
            console.log("sucess log");
            toast.success("User register successfully!!! user id " +resp.userId);
            setData({
                name:'',
                email:'',
                password:'',
                about:''
            });
        }).catch((error)=>{
            console.log(error);
            console.log("Error log");
            setError({
                errors:error,
                isError:true
            });
        });
    }

    const resetData=()=>{
        setData({
            name:'',
            email:'',
            password:'',
            about:''
        })
    }

    return(
        <Base>
            <Container>
                <Row className="mt-4">
                    <Col sm={{size:6, offset:3}}>
                    <Card color="dark" inverse>
                    <CardHeader>
                        <h3>Fill Information to Register !!</h3>
                    </CardHeader>
                    <CardBody>
                        
                        <Form onSubmit={submitForm}>
                            <FormGroup>
                                <Label for="name">Enter Name</Label>
                                <Input type="text" placeholder="Enter here" id="name" onChange={(e)=>handleChange(e, 'name')}
                                invalid={  error.errors?.response?.data?.errors ?  true : false }
                                 value={data.name} />

                                 <FormFeedback>
                                    { error.errors?.response?.data?.errors?.defaultMessage }
                                </FormFeedback>
                            </FormGroup>

                            <FormGroup>
                                <Label for="email">Enter Email</Label>
                                <Input type="email" placeholder="Enter here" id="email" onChange={(e)=>handleChange(e, 'email')}
                                    invalid={  error.errors?.response?.data?.errors ?  true : false } value={data.email}></Input>
                            </FormGroup>

                            <FormGroup>
                                <Label for="password">Enter Password</Label>
                                <Input type="password" placeholder="Enter here" id="password" onChange={(e)=>handleChange(e,'password')} value={data.password}
                                invalid={  error.errors?.response?.data?.errors ?  true : false }></Input>
                            </FormGroup>

                            {/* <FormFeedback>
                                { error.errors?.response?.data.errors.defaultMessage }
                            </FormFeedback> */}
                            <FormGroup>
                                <Label for="textarea">About</Label>
                                <Input type="textarea" placeholder="Enter here" id="about" onChange={(e)=>handleChange(e,'about')} value={data.about}
                                    invalid={  error.errors?.response?.data?.errors ?  true : false } style={{height:"150px"}}></Input>
                                
                            </FormGroup>

                            <Container className="text-center">
                                <Button outline color="light">Register</Button>
                                <Button onClick={resetData} outline type="reset" className="ms-2" color="secondary">Reset</Button>
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

export default Signup;