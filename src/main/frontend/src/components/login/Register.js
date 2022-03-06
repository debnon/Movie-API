import React from "react";
import './Register.css';

const Register = ({ isShowRegister }) => {
  return (
    <div className={`${isShowRegister ? "active" : ""} show`}>
      <div className="register-form">
        <div className="form-box solid">
          <form>
            <h1 className="register-text">Sign In</h1>
            <label>Username</label>
            <br></br>
            <input type="text" name="username" className="register-box" />
            <br></br>
            <label>Password</label>
            <br></br>
            <input type="password" name="password" className="register-box" />
            <br></br>
            <input type="submit" value="register" className="register-btn" />
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;