import React, { useEffect, useState } from "react";
import config from "../config";
import axios from "axios";
import { Bounce, ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface Algorithm {
    name: string,
    signature: string,
    code: string,
    weighted: boolean,
    directed: boolean
}

const Algorithm = () => {

    const [algorithmName, setAlgorithmName] = useState<string>('');
    const [signature, setSignature] = useState<string>('');
    const [code, setCode] = useState<string>('');
    const [weighted, setWeighted] = useState<boolean>(false);
    const [directed, setDirected] = useState<boolean>(false);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        const algorithmUrl = `${config.BACKEND_URL}/algorithms`;

        const newAlgorithm = {
            name: algorithmName,
            signature: signature,
            code: code,
            weighted: weighted,
            directed: directed
        }


        try {
            await toast.promise(
                axios.post(algorithmUrl, newAlgorithm, {
                    headers: { 'Content-Type': 'application/json' }
                }),
                {
                    pending: 'Adding algorithm...',
                    success: 'Algorithm successfully added! ✅',
                    error: 'Error adding algorithm! ❌'
                },
                {
                    position: "top-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "dark",
                    transition: Bounce
                }
            );

            setAlgorithmName('');
            setSignature('');
            setCode('');
            setWeighted(false);
            setDirected(false);
        } catch (error) {
            console.log("Error " + error);
        }


    }

    return (
        <div className="h-screen w-screen overflow-hidden bg-[#21222c] p-4">
            <ToastContainer />
            <div className="min-h-screen flex items-center justify-center">
                <div className="w-2/5 bg-dracula-background p-8 rounded-lg shadow-md">
                    <h2 className="text-2xl font-bold mb-6 text-center text-white">Add new algorithm</h2>
                    <form>
                        <div className="mb-4">
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="algorithm-name">
                                Algorithm name 
                            </label>
                            <input
                                className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                id="algorithm-name"
                                type="text"
                                placeholder="Algorithm name"
                                value={algorithmName}
                                onChange={(e) => setAlgorithmName(e.target.value)}
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="signature">
                                Signature 
                            </label>
                            <select
                                id="datasetCategory"
                                className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                value={signature}
                                onChange={(e) => setSignature(e.target.value)}
                                >
                                
                                <option selected>Choose algorithm signature</option>
                                <option key="map_int_double" value="Map<Integer, Double>">Map&lt;Integer, Double&gt;</option>
                                <option key="map_int_int" value="Map<Integer, Integer>">Map&lt;Integer, Integer&gt;</option>
                                <option key="list_int" value="List<Integer>">List&lt;Integer&gt;</option>
                                <option key="list_double" value="List<Double>">List&lt;Double&gt;</option>
                                <option key="integer" value="int">Integer</option>
                                <option key="double" value="double">Double</option>
                            </select>
                        </div>

                        <div className="mb-4">
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="email">
                                Code 
                            </label>
                            <textarea name="code"
                                      id="code"
                                      placeholder="Add your code here"
                                      className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                      value={code}
                                      onChange={(e) => setCode(e.target.value)}
                                      >
                            </textarea>
                        </div>

                        <div className="flex items-center mb-4">
                            <input id="weight"
                                   type="checkbox"
                                   value="weight"
                                   name="weight-option"
                                   checked={weighted}
                                   onChange={(e) => setWeighted(e.target.checked)}
                                   className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" 
                                   />
                                <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted</label>
                        </div>

                        <div className="flex items-center mb-4">
                            <input id="directed"
                                   type="checkbox"
                                   value="directed"
                                   name="directed-option"
                                   checked={directed}
                                   onChange={(e) => setDirected(e.target.checked)}
                                   className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                                <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed</label>
                        </div>

                        <div className="mb-4">
                            <button className="w-full text-black bg-dracula-pink px-4 py-2 rounded-lg shadow hover:bg-dracula-purple transition duration-300 ease-in-out" onClick={handleSubmit} >Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Algorithm;