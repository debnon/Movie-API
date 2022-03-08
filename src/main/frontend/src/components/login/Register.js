import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './Register.css';

// async function registerUser(credentials) {
//     console.log("yo");
//     return fetch('http://localhost:8080/api/v1/user/registration', {
//         method: 'POST',
//         headers: {
//         'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(credentials)
//     })
//         .then(data => data.json())
// }

const Register = ({ isShowRegister, setrUserName, setrPassword, setFirstname, setLastname, setEmail, handleRegistration }) => {

    // const [username, setUserName] = useState();
    // const [password, setPassword] = useState();
    // const [firstname, setFirstname] = useState();
    // const [lastname, setLastname] = useState();
    // const [emailID, setEmail] = useState();
   
    // const handleSubmit = async e => {
    //     console.log("yo");
    //     e.preventDefault();
    //     const token = await registerUser({
    //         "id": "25",
    //         "username": username,
    //         "password": password,
    //         "firstname": firstname,
    //         "lastname": lastname,
    //         "emailID": emailID,
    //         "contactnumber": "02825632423"
    //     });

    //     console.log("yo");
        
    // }

  return (
    <div className={`${isShowRegister ? "active" : ""} show`}>
        
    <div class="form_wrapper">
  <div class="form_container">
    <div class="title_container">
      <h2>Sign up to movieroll now</h2>
    </div>
    <div class="row clearfix">
      <div class="">
        <form>

        
          <div class="input_field"> <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
            <input type="email" name="email" placeholder="Email" required onChange={e => setEmail(e.target.value)}/>
          </div>
          <div class="input_field"> <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
            <input type="email" name="username" placeholder="Username" required onChange={e => setrUserName(e.target.value)}/>
          </div>
        
        {/* <div class="col_half">
          <div class="input_field"> <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
            <input type="email" name="username" placeholder="Username" required />
          </div>
        </div> */}

          <div class="input_field"> <span><i aria-hidden="true" class="fa fa-lock"></i></span>
            <input type="password" name="password" placeholder="Password" required onChange={e => setrPassword(e.target.value)}/>
          </div>
          <div class="input_field"> <span><i aria-hidden="true" class="fa fa-lock"></i></span>
            <input type="password" name="password" placeholder="Re-type Password" required />
          </div>
          <div class="row clearfix">
            <div class="col_half">
              <div class="input_field"> <span><i aria-hidden="true" class="fa fa-user"></i></span>
                <input type="text" name="name" placeholder="First Name" onChange={e => setFirstname(e.target.value)}/>
              </div>
            </div>
            <div class="col_half">
              <div class="input_field"> <span><i aria-hidden="true" class="fa fa-user"></i></span>
                <input type="text" name="name" placeholder="Last Name" required onChange={e => setLastname(e.target.value)}/>
              </div>
              
            </div>
            
          </div>
            	{/* <div class="input_field radio_option">
              <input type="radio" name="radiogroup1" id="rd1"/>
              <label for="rd1">Male</label>
              <input type="radio" name="radiogroup1" id="rd2"/>
              <label for="rd2">Female</label>
              </div> */}
              <div class="input_field select_option">
                <select>
                  <option>Select a country</option>
                  <option>Option 1</option>
                  <option>Option 2</option>
                </select>
                <div class="select_arrow"></div>
              </div>
            <div class="input_field checkbox_option">
            	<input type="checkbox" id="cb1"/>
    			<label for="cb1">I agree with terms and conditions</label>
            </div>
            <div class="input_field checkbox_option">
            	<input type="checkbox" id="cb2"/>
    			<label for="cb2">I want to receive the newsletter</label>
            </div>
          <input class="button" type="submit" value="Register" onClick={handleRegistration}/>
        </form>
      </div>
    </div>
  </div>
</div>
</div>
  );
};

Register.propTypes = {
    handleRegistration: PropTypes.func.isRequired
}

export default Register;