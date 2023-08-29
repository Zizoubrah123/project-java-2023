import React, { useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';
import Logo from "../assets/bazaarlogo.png"
import { useNavigate } from 'react-router-dom';
const Register = () => {
  const navigate = useNavigate();
  const [logedUser, setLogedUser] = useState("");
  const [register, setRegister] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phNum: "",
    password: "",
    confirm: "",
  });  
    
  const [firstName,setFirstName]= useState("")
  const [lastName, setLastName]= useState("")
  const [email, setEmail]= useState("")
  const [phNum ,setPhNum]= useState("")
  const [password, setPassword]= useState("")
  const [confirm,setConfirm]= useState("")
    

  // handel onChange htmlFor register inputs
  const handleRegisterChange = (e) => {
    e.preventDefault();
    setRegister({
      ...register,
      [e.target.name]: e.target.value,
    });
  };

  // handel onSubmit htmlFor register inputs
  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/v1/signup", register)
      .then((res) => {
        console.log(res.data);
        setRegister({
          firstName: "",
          lastName: "",
          email: "",
          phNum: "",
          password: "",
          confirm: "",
        });
        navigate("/product/list");
      })
      .catch((err) => {
        console.log("❌❌❌", err.response.data);
        err.response.data.map(({field,defaultMessage}, index) =>{
        if(field==="firstName"){
          setFirstName("First Name must be between 3 and 30 characters")
        }
        if(field==="lastName"){
          setLastName("Last Name must be between 3 and 30 characters")
        }if(field==="email"){
          setEmail("Please enter a valid email!")
        }if(field==="phNum"){
          setPhNum("Please enter a valid Phone number!")
        }if(field==="password"){
          setPassword("Password must be between 8 and 128 characters")
        }if(field==="confirm"){
          setConfirm("Confirm password does not match !!")
        }
      });
      });
  };

  return (
    <>
      <section className="bg-gray-50 dark:bg-gray-900 h-[1200px]">
        <div className="flex flex-col items-center  px-6 py-8 mx-auto md:h-screen lg:py-0">
          <a
            href="/"
            className="flex items-center  text-2xl font-semibold text-gray-900 dark:text-white"
          >
            <img className="w-13 h-40 mr-2 mb-" src={Logo} alt="logo" />
          </a>
          <div className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
            <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
              <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                Sign in to your account
              </h1>
              {/*--------------------------------------------- error messages ------------------------*/}
              {/* {errors.register
                ? Object.entries(errors.register).map(([key, value], index) =>
                    value ? (
                      <p key={index} style={{ color: "red" }}>{value.message}</p>
                    ) : <p key={index} >{key} {value.message} </p>
                  )
                : null} */}
              <form
                className="space-y-4 md:space-y-6"
                onSubmit={handleRegisterSubmit}
              >
                <div>
                  {/* ----------------------------------- first name input -------------------------------- */}
                  <label
                    htmlFor="firstName"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    First Name
                  </label>
                  <input
                    type="text"
                    name="firstName"
                    value={register.firstName}
                    onChange={handleRegisterChange}
                    placeholder="first name"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required=""
                  />
                  {firstName?<p style={{ color: "red" }}>{firstName}</p>:null}
                </div>
                <div>
                  <label
                    htmlFor="lastName"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Last Name
                  </label>
                  {/* ----------------------------------- last name input -------------------------------- */}
                  <input
                    type="text"
                    name="lastName"
                    value={register.lastName}
                    onChange={handleRegisterChange}
                    placeholder="last name"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required=""
                  />
                  {lastName?<p style={{ color: "red" }}>{lastName}</p>:null}
                </div>
                <div>
                  <label
                    htmlFor="email"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Your email
                  </label>
                  {/* ----------------------------------- email input ---------------------------------------------------------------*/}
                  <input
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    type="email"
                    name="email"
                    value={register.email}
                    onChange={handleRegisterChange}
                    placeholder="name@gmail.com"
                  />{email?<p style={{ color: "red" }}>{email}</p>:null}
                </div>
                {/* ----------------------------------- phone number input -------------------------------- */}
                <div>
                  <label
                    htmlFor="phNum"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Phone Number
                  </label>
                  <input
                    type="number"
                    name="phNum"
                    value={register.phNum}
                    onChange={handleRegisterChange}
                    placeholder="+216 00 000 000"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required=""
                  />
                  {phNum?<p style={{ color: "red" }}>{phNum}</p>:null}
                </div>
                {/* ----------------------------------- password input -------------------------------- */}
                <div>
                  <label
                    htmlFor="password"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Password
                  </label>
                  <input
                    type="password"
                    name="password"
                    value={register.password}
                    onChange={handleRegisterChange}
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required=""
                  />
                </div>{password?<p style={{ color: "red" }}>{password}</p>:null}

                {/* ----------------------------------- confirm password input -------------------------------- */}

                <div>
                  <label
                    htmlFor="confirm"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Confirm Password
                  </label>
                  <input
                    type="password"
                    name="confirm"
                    value={register.confirm}
                    onChange={handleRegisterChange}
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required=""
                  />{confirm?<p style={{ color: "red" }}>{confirm}</p>:null}
                </div>
                <div className="flex items-center justify-between">
                  <div className="flex items-start">
                    <div className="flex items-center h-5">
                      <input
                        id="remember"
                        aria-describedby="remember"
                        type="checkbox"
                        className="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800"
                        required=""
                      />
                    </div>
                    <div className="ml-3 text-sm">
                      <label
                        htmlFor="remember"
                        className="text-gray-500 dark:text-gray-300"
                      >
                        Remember me
                      </label>
                    </div>
                  </div>
                </div>
                <button
                  type="submit"
                  className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                >
                  Sign up
                </button>
                <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                  You already have an account ?{" "}
                  <Link
                    to="/login"
                    className="font-medium text-primary-600 hover:underline dark:text-primary-500"
                  >
                    log in
                  </Link>
                </p>
              </form>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Register