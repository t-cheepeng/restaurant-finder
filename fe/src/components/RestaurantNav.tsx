import { Container, Navbar } from "react-bootstrap";

interface RestaurantNavProps {
  children: React.ReactNode;
}

const RestaurantNav = (props: RestaurantNavProps) => {
  return (
    <div>
      <Navbar expand="sm" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/">Restaurant Finder</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
        </Container>
      </Navbar>
      {props.children}
    </div>
  );
}

export default RestaurantNav;