import { useState } from "react";
import { Container, Grid, Paper, Tab, Tabs, Typography } from "@mui/material";

import LoginComponent from "./Login";
import RegisterComponent from "./Register";

const Sign = () => {
  const [isLogin, setIsLogin] = useState(true);

  const toggleForm = () => {
    setIsLogin(!isLogin);
  };

  return (
    <Container maxWidth="md" sx={{ mt: 5 }}>
      <Paper elevation={3}>
        <Grid container>
          <Grid item xs={12}>
            <Typography variant="h5" component="div" sx={{ p: 2 }}>
              Escapes
            </Typography>
            <Tabs value={isLogin ? 0 : 1} onChange={toggleForm} centered>
              <Tab label="Login" />
              <Tab label="Register" />
            </Tabs>
          </Grid>
          <Grid item xs={12}>
            <Paper elevation={0} sx={{ p: 2 }}>
              {isLogin ? <LoginComponent/> : <RegisterComponent />}
            </Paper>
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
};

export default Sign;
