import React, { useState } from "react";

interface Props {
    isLogged: boolean;
    onLogin: (logged: boolean) => void;
}

const Login: React.FC<Props> = ({ isLogged, onLogin }) => {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setPassword(event.target.value);
    };

    const onLoginHandler = () => {
        if (username === 'admin' && password === 'admin') {
            localStorage.setItem('isLogged', 'true');
            onLogin(true);
        }
    }

    return (
        <div className="h-screen w-screen overflow-hidden bg-[#21222c] p-4">
            <div className="min-h-screen flex items-center justify-center">
                <div className="w-2/5 bg-dracula-background p-8 rounded-lg shadow-md ">
                    <h2 className="text-2xl font-bold mb-6 text-center text-white">Login</h2>
                    <form>
                        <div className="mb-4">
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="algorithm-name">
                                Username 
                            </label>
                            <input
                                className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                id="username"
                                type="text"
                                value={username}
                                onChange={handleUsernameChange}
                                placeholder="Username"
                            />
                        </div>

                        <div className="mb-4">
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="algorithm-name">
                                Password
                            </label>
                            <input
                                className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                id="password"
                                type="password"
                                value={password}
                                onChange={handlePasswordChange}
                                placeholder="Password"
                            />
                        </div>

                        <div className="mt-10">
                            <button 
                            className="w-full text-black bg-dracula-pink px-4 py-2 rounded-lg shadow hover:bg-dracula-purple transition duration-300 ease-in-out"
                            onClick={onLoginHandler}>
                                Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;