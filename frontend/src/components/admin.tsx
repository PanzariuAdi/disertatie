import React, { useEffect, useState } from "react";
import Algorithm from "./algorithm";
import Login from "./login";

interface Props {
    isLogged: boolean
}

const Admin: React.FC<Props> = () => {

    const [isLogged, setIsLogged] = useState<boolean>(() => {
        const storedIsLogged = localStorage.getItem('isLogged');
        return storedIsLogged ? JSON.parse(storedIsLogged) : false;
    });

    const handleLogin = (logged: boolean) => {
        setIsLogged(logged);
        localStorage.setItem('isLogged', JSON.stringify(logged));
        window.location.reload();
    }

    return (
        <div>
            { isLogged ? <Algorithm /> : <Login isLogged={isLogged} onLogin={handleLogin} /> }
        </div>
    );
}

export default Admin;