import { Toast } from "react-bootstrap";

interface RestaurantToastProps {
  show: boolean;
  onClose: (e?: React.MouseEvent | React.KeyboardEvent) => void;
  message: string
}

const RestaurantToast = (props: RestaurantToastProps) => {
  const { show, onClose, message } = props;

  return (
    <Toast show={show} onClose={onClose}>
      <Toast.Body>{message}</Toast.Body>
    </Toast>
  );
};

export default RestaurantToast;
