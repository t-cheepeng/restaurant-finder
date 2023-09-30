import { Spinner } from "react-bootstrap";

export interface LoadingSpinnerProps {
  type: "button" | "normal";
}

const LoadingSpinner = (props: LoadingSpinnerProps) => {
  if (props.type === "button") {
    return (
      <>
        <Spinner
          as="span"
          animation="border"
          size="sm"
          role="status"
          aria-hidden="true"
        />
        <span className="visually-hidden">Loading...</span>
      </>
    );
  } else {
    return (
      <Spinner animation="border" role="status">
        <span className="visually-hidden">Loading...</span>
      </Spinner>
    );
  }
};

export default LoadingSpinner;
