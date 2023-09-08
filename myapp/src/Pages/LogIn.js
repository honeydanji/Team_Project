import axios from "axios";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import jwtDecode from "jwt-decode";
import { useRecoilState } from "recoil";
import { nameState, loginEmailState, passwordState, isLoggedInState } from "../Hook/LoginRecoil";
import Nav from "../Components/Nav";

export default function LogIn() {

  // 로그인 상태 여부 상태 관리, 인증 상태
  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInState);
  // id/ pw 상태관리
  const navigate = useNavigate();

  // 로그인 필요 정보
  const [loginEmail, setLoginEmail] = useRecoilState(loginEmailState);
  const [password, setPassword] = useRecoilState(passwordState);

  // token -> name
  const [name, setName] = useRecoilState(nameState);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      setIsLoggedIn(token);
      console.log("isLoggedIn: ", isLoggedIn)
    }
  }, [isLoggedIn, setIsLoggedIn]);

  const handelLoginButtonClick = async () => {
    const data = {
      loginEmail,
      password
    };

    axios.post('http://10.125.121.183:8080/login', data)
      .then((res) => {
        const token = res.headers['authorization']; // 서버에서 받은 토큰 가져오기  
        console.log("token:", token);

        if (token != null) {
          // 토큰을 로컬 스토리지에 저장
          localStorage.setItem('token', token);
          setIsLoggedIn(localStorage.getItem('token'));
          console.log("isLoggedIn2222: ", isLoggedIn);

          // 요청 헤더에 토큰을 저장
          // axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

          // 토큰 추출 및 이름 저장
          // token decoding
          const splitToken = token.split(" ")[1];
          const decodedToken = jwtDecode(splitToken);
          const extractedName = decodedToken.name;
          console.log("extractedName: ", extractedName);

          // name statement
          setName(extractedName);
          console.log("name: ", name);

          alert(extractedName + '님, 환영합니다.');

          // 회원가입이 완료되면 로그인 페이지로 이동
          navigate("/dragdrop");
        }

      })
      .catch((err) => {
        alert('잘못된 아이디 혹은 패스워드 입니다')
      });
  };
  const handelLogoutButtonClick = () => {
    alert('로그아웃 완료');
    // 로컬 스토리지에서 토큰 삭제
    localStorage.removeItem('token');
    setIsLoggedIn(''); // 로그아웃 상태로 변경
    delete axios.defaults.headers.common['Authorization']; // 로그아웃 시 토큰 삭제
    // 시작페이지로 이동
    navigate('/')
  }

  return (
    <main>
      <Nav />
      <section className="bg-white dark:bg-gray-900">
        <div className="container flex items-center justify-center min-h-screen px-6 mx-auto">
          <div className="w-full max-w-md">
            <img className="w-auto h-7 sm:h-8" src="https://merakiui.com/images/logo.svg" alt="" />

            <h1 className="mt-3 text-2xl font-semibold text-gray-800 capitalize sm:text-3xl dark:text-white">sign In</h1>

            <div className="relative flex items-center mt-8">
              <span className="absolute">
                <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6 mx-3 text-gray-300 dark:text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                  <path strokeinecap="round" strokeLinejoin="round" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
              </span>

              <input type="email" className="block w-full py-3 text-gray-700 bg-white border rounded-lg px-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Email address" onChange={(e) => setLoginEmail(e.target.value)} required />
            </div>

            <div className="relative flex items-center mt-4">
              <span className="absolute">
                <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6 mx-3 text-gray-300 dark:text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                  <path strokeLinecap="round" strokeLinejoin="round" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
                </svg>
              </span>

              <input type="password" className="block w-full px-10 py-3 text-gray-700 bg-white border rounded-lg dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Password" onChange={(e) => setPassword(e.target.value)} required />
            </div>

            <div className="mt-6">
              {isLoggedIn ? (<button className="w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-500 rounded-lg hover:bg-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50" onClick={handelLogoutButtonClick}>Log out</button>)
                : (<button className="w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-500 rounded-lg hover:bg-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50" onClick={handelLoginButtonClick}>
                  Sign in
                </button>)}
              {/* 
            <p className="mt-4 text-center text-gray-600 dark:text-gray-400">or sign in with</p>
            
            <a href="#" className="flex items-center justify-center px-6 py-3 mt-4 text-gray-600 transition-colors duration-300 transform border rounded-lg dark:border-gray-700 dark:text-gray-200 hover:bg-gray-50 dark:hover:bg-gray-600">
            <button className="mx-2" onClick={handelLogoutButtonClick}>Log out</button>
          </a> */}

              <div className="mt-6 text-center ">
                <a href="signup" className="text-sm text-blue-500 hover:underline dark:text-blue-400">
                  Don’t have an account yet? Sign up
                </a>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  )
}
