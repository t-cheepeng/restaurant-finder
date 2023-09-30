import { Button, Form, Stack } from "react-bootstrap";
import LoadingSpinner from "../../components/LoadingSpinner";

interface RestaurantFormProps {
  onAdd: React.FormEventHandler<HTMLFormElement>;
  formErrors: string[];
  isLoading: boolean;
}

const RestaurantForm = (props: RestaurantFormProps) => {
  const { formErrors, onAdd, isLoading } = props;

  return (
    <Stack>
      <h3>Add a restaurant</h3>
      <hr />
      <Form onSubmit={onAdd}>
        <Stack gap={2}>
          <Form.Group controlId="formName">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter name of restaurant"
              name="name"
            />
            {formErrors.length > 0 ? (
              <Form.Text className="text-danger">{formErrors[0]}</Form.Text>
            ) : null}
          </Form.Group>
          <Form.Group controlId="formAddress">
            <Form.Label>Address</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter address of restaurant"
              name="address"
            />
            {formErrors.length > 1 ? (
              <Form.Text className="text-danger">{formErrors[1]}</Form.Text>
            ) : null}
          </Form.Group>
          <Form.Group controlId="formCuisine">
            <Form.Label>Cuisine</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter cuisine of restaurant"
              name="cuisine"
            />
            {formErrors.length > 2 ? (
              <Form.Text className="text-danger">{formErrors[2]}</Form.Text>
            ) : null}
          </Form.Group>
          <Form.Group controlId="formDescription">
            <Form.Label>Description</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter description of restaurant"
              name="description"
            />
            {formErrors.length > 3 ? (
              <Form.Text className="text-danger">{formErrors[3]}</Form.Text>
            ) : null}
          </Form.Group>
          <Button variant="primary" type="submit" className="mt-2" disabled={isLoading}>
            {
              isLoading ? <LoadingSpinner type="button"/> : null
            }
            Add
          </Button>
        </Stack>
      </Form>
    </Stack>
  );
};

export default RestaurantForm;
