import React, { useEffect, useState } from "react";

const Navbar: React.FC = () => {

    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('isLogged') === 'true');

    useEffect(() => {
        const handleStorageChange = () => {
            setIsLoggedIn(localStorage.getItem('isLogged') === 'true');
        };

        window.addEventListener('storage', handleStorageChange);

        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []);

    const handleLogout = () => {
        localStorage.setItem('isLogged', 'false');
        setIsLoggedIn(false);
        window.location.href = "/";
    };

    return (
        <nav className="bg-[#21222c]">
            <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                <a href="/" className="flex items-center space-x-3 rtl:space-x-reverse">
                    <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white hover:text-dracula-pink">Graph4j algorithm competion</span>
                </a>
                <div className="items-center justify-between hidden w-full md:flex md:w-auto md:order-1">
                    <ul className="flex flex-col font-medium p-4 md:p-0 mt-4 border border-gray-100 rounded-lg md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 md:bg-white md:dark:bg-[#21222c] dark:border-gray-700">
                        <li>
                            <a href="/" className="block py-2 px-3 md:p-0 text-white bg-[#21222c] rounded md:bg-transparent md:dark:text-dracula-white md:dark:hover:text-dracula-pink" aria-current="page">Home</a>
                        </li>
                        <li>
                            <a href="/info" className="block py-2 px-3 md:p-0 text-white bg-[#21222c] rounded md:bg-transparent md:dark:text-dracula-white hover:text-dracula-pink">Info</a>
                        </li>
                        <li>
                            <a href="/admin" className="block py-2 px-3 md:p-0 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:dark:hover:text-dracula-pink dark:text-white dark:hover:bg-dracula-background dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Admin</a>
                        </li>
                        <li>
                            {
                                (
                                    isLoggedIn && (
                                        <a href="/tests" className="block py-2 px-3 md:p-0 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:dark:hover:text-dracula-pink dark:text-white dark:hover:bg-dracula-background dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Tests</a>
                                    )
                                )
                            }
                        </li>
                        <li>
                            {
                                (
                                    isLoggedIn && (
                                        <a href="/logout" onClick={(e) => { e.preventDefault(); handleLogout(); }} className="block py-2 px-3 md:p-0 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:dark:hover:text-dracula-pink dark:text-white dark:hover:bg-dracula-background dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Logout</a>
                                    )
                                )
                            }
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;