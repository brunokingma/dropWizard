
import React,{Component} from "react";
import { Button, Modal, ModalHeader, ModalBody, Row, Col, Label } from 'reactstrap';
import { Control, LocalForm, Errors } from 'react-redux-form';


const required = (val) => val && val.length;
const maxLength = (len) => (val) => !(val) || (val.length <= len);
const minLength = (len) => (val) => val && (val.length >= len);


class StockForm extends Component{

    constructor(props) {
        super(props);
        this.toggleModal = this.toggleModal.bind(this);
        this.state = {
          isModalOpen: false
        };
      }

      toggleModal() {
        this.setState({
          isModalOpen: !this.state.isModalOpen
        });
      }


      handleSubmit(values) {
        this.props.postComment(this.props.dishId, values.rating, values.author, values.comment);
        }



    render(){
        return(
            <React.Fragment>
            <Button onClick={this.toggleModal}  color="primary">
            add comment
            </Button>
            <Modal isOpen={this.state.isModalOpen} toggle={this.toggleModal}>
                    <ModalHeader toggle={this.toggleModal}>Add Comment</ModalHeader>
                    <ModalBody>
                    <LocalForm onSubmit={(values) => this.handleSubmit(values)}>
                            <Row className="form-group">
                            <Col md={10}>
                                <Label htmlFor="author"  md={2}>Your name</Label>
                                <Control.text model=".author" id="author" name="author"
                                        placeholder="Your name"
                                        className="form-control"
                                        validators={{
                                            required, minLength: minLength(3), maxLength: maxLength(15)
                                        }}
                                         />
                                    <Errors
                                        className="text-danger"
                                        model=".firstname"
                                        show="touched"
                                        messages={{
                                            required: 'Required',
                                            minLength: 'Must be greater than 2 characters',
                                            maxLength: 'Must be 15 characters or less'
                                        }}
                                     />
                                     </Col>
                            </Row>
                            <Row className="form-group">
                            <Col md={10}>
                                <Label htmlFor="rating"  md={2}>Rating</Label>
                                <Control.select model=".rating" id="rating" name="rating" className="form-control">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </Control.select>
                                </Col>
                            </Row>
                            <Row className="form-group">
                            <Col md={10}>
                                <Label htmlFor="comment"  md={2}>Comment</Label>
                                    <Control.textarea model=".comment" name="comment" row="12" className="form-control"></Control.textarea>
                            </Col>
                            </Row>
                            <Row className="form-group">
                            <Button type="submit" value="submit"  color="primary">Add Comment</Button>
                            </Row>
                            
                        </LocalForm>
                    </ModalBody>
                </Modal>
        </React.Fragment>
        
    )
};
}

export default StockForm;