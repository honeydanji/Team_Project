import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPhone, faBuilding, faLock, faEnvelope, faUser } from "@fortawesome/free-solid-svg-icons";

export default function SignUp() {

    return (
        <section className="bg-white dark:bg-gray-900">
            <div className="container flex items-center justify-center min-h-screen px-6 mx-auto">
                <form className="w-full max-w-md">
                    <div className="flex justify-center mx-auto">
                        <img className="w-auto h-7 sm:h-8" src="https://merakiui.com/images/logo.svg" alt="" />
                    </div>

                    <div className="flex items-center justify-center mt-6">
                        <a href="login" className="w-1/3 pb-4 font-medium text-center text-gray-500 capitalize border-b dark:border-gray-400 dark:text-gray-300">
                            sign in
                        </a>

                        <a href="#" className="w-1/3 pb-4 font-medium text-center text-gray-800 capitalize border-b-2 border-blue-500 dark:border-blue-400 dark:text-white">
                            sign up
                        </a>
                    </div>

                    <div className="relative flex items-center mt-8">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faUser} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="text" class="block w-full py-3 text-gray-700 bg-white border rounded-lg px-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Username" />
                    </div>

                    <div className="relative flex items-center mt-6">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faEnvelope} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="email" class="block w-full py-3 text-gray-700 bg-white border rounded-lg px-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Email address" />
                    </div>

                    <div className="relative flex items-center mt-4">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faLock} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="password" className="block w-full px-10 py-3 text-gray-700 bg-white border rounded-lg dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Password" />
                    </div>

                    <div className="relative flex items-center mt-4">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faLock} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="password" className="block w-full px-10 py-3 text-gray-700 bg-white border rounded-lg dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Confirm Password" />
                    </div>
                    <div className="relative flex items-center mt-6">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faPhone} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="text" className="block w-full py-3 text-gray-700 bg-white border rounded-lg px-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Phone Number" />
                    </div>
                    <div className="relative flex items-center mt-6">
                        <span className="absolute">
                            <FontAwesomeIcon icon={faBuilding} style={{ color: '#888', position: 'relative', left: '15px' }} />
                        </span>

                        <input type="text" className="block w-full py-3 text-gray-700 bg-white border rounded-lg px-11 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 dark:focus:border-blue-300 focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40" placeholder="Company Name" />
                    </div>

                    <div className="mt-6">
                        <button className="w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-500 rounded-lg hover:bg-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50">
                            Sign Up
                        </button>

                        <div className="mt-6 text-center ">
                            <a href="/login" className="text-sm text-blue-500 hover:underline dark:text-blue-400">
                                Already have an account?
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    )
}