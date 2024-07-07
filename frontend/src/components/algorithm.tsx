import React, { useEffect, useState } from "react";
import config from "../config";
import axios from "axios";
import { Bounce, ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface Algorithm {
    name: string,
    signature: string,
    code: string,
    datasets: string[],
}

const Algorithm = () => {

    const [algorithmName, setAlgorithmName] = useState<string>('');
    const [signature, setSignature] = useState<string>('');
    const [code, setCode] = useState<string>('');

    const [directed1, setDirected1] = useState<boolean>(false);
    const [directed2, setDirected2] = useState<boolean>(false);
    const [directed3, setDirected3] = useState<boolean>(false);
    const [directed4, setDirected4] = useState<boolean>(false);
    const [directed5, setDirected5] = useState<boolean>(false);

    const [weighted1, setWeighted1] = useState<boolean>(false);
    const [weighted2, setWeighted2] = useState<boolean>(false);
    const [weighted3, setWeighted3] = useState<boolean>(false);
    const [weighted4, setWeighted4] = useState<boolean>(false);
    const [weighted5, setWeighted5] = useState<boolean>(false);

    const [undirected1, setUndirected1] = useState<boolean>(false);
    const [undirected2, setUndirected2] = useState<boolean>(false);
    const [undirected3, setUndirected3] = useState<boolean>(false);
    const [undirected4, setUndirected4] = useState<boolean>(false);
    const [undirected5, setUndirected5] = useState<boolean>(false);
    const [undirected6, setUndirected6] = useState<boolean>(false);
    const [undirected7, setUndirected7] = useState<boolean>(false);
    const [undirected8, setUndirected8] = useState<boolean>(false);
    const [undirected9, setUndirected9] = useState<boolean>(false);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        const algorithmUrl = `${config.BACKEND_URL}/algorithms`;

        const datasets = [
            { name: 'directed1', value: directed1 },
            { name: 'directed2', value: directed2 },
            { name: 'directed3', value: directed3 },
            { name: 'directed4', value: directed4 },
            { name: 'directed5', value: directed5 },
            { name: 'weighted1', value: weighted1 },
            { name: 'weighted2', value: weighted2 },
            { name: 'weighted3', value: weighted3 },
            { name: 'weighted4', value: weighted4 },
            { name: 'weighted5', value: weighted5 },
            { name: 'undirected1', value: undirected1 },
            { name: 'undirected2', value: undirected2 },
            { name: 'undirected3', value: undirected3 },
            { name: 'undirected4', value: undirected4 },
            { name: 'undirected5', value: undirected5 },
            { name: 'undirected6', value: undirected6 },
            { name: 'undirected7', value: undirected7 },
            { name: 'undirected8', value: undirected8 },
            { name: 'undirected9', value: undirected9 },
          ];
        
        const selectedDatasets = datasets 
            .filter(dataset => dataset.value)
            .map(dataset => dataset.name);

        
        const request = {
            algorithm: {
                name: algorithmName,
                signature: signature,
                code: code,
                datasets: selectedDatasets,
            },
            datasets: selectedDatasets,
        }

        try {
            await toast.promise(
                axios.post(algorithmUrl, request, {
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
            setDirected1(false) 
            setDirected2(false) 
            setDirected3(false) 
            setDirected4(false) 
            setDirected5(false) 
            setUndirected1(false) 
            setUndirected2(false) 
            setUndirected3(false) 
            setUndirected4(false) 
            setUndirected5(false) 
            setUndirected6(false) 
            setUndirected7(false) 
            setUndirected8(false) 
            setUndirected9(false) 
            setWeighted1(false) 
            setWeighted2(false) 
            setWeighted3(false) 
            setWeighted4(false) 
            setWeighted5(false) 


        } catch (error) {
            console.log("Error " + error);
        }


    }

    return (
        <div className="h-screen w-screen overflow-hidden bg-[#21222c] p-4 overflow-y-auto">
            <ToastContainer />
            <div className="min-h-screen flex items-center justify-center ">
                <div className="w-2/5 bg-dracula-background p-8 rounded-lg shadow-md mb-4">
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
                            <label className="block text-white text-sm font-bold mb-2" htmlFor="code">
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

                        <div className="container mx-auto p-4">
                            <h2 className="mb-4 text-xl font-extrabold dark:text-white text-center">Weighted</h2>

                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div>
                                    <input id="weighted1"
                                        type="checkbox"
                                        value="weighted1"
                                        name="weighted1"
                                        checked={weighted1}
                                        onChange={(e) => setWeighted1(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted 1</label>
                                </div>
                                <div>
                                    <input id="weighted2"
                                        type="checkbox"
                                        value="weighted2"
                                        name="weighted2"
                                        checked={weighted2}
                                        onChange={(e) => setWeighted2(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted 2</label>
                                </div>
                                <div>
                                    <input id="weighted3"
                                        type="checkbox"
                                        value="weighted3"
                                        name="weighted3"
                                        checked={weighted3}
                                        onChange={(e) => setWeighted3(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted 3</label>
                                </div>
                                <div>
                                    <input id="weighted4"
                                        type="checkbox"
                                        value="weighted4"
                                        name="weighted4"
                                        checked={weighted4}
                                        onChange={(e) => setWeighted4(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted 4</label>
                                </div>
                                <div>
                                    <input id="weighted5"
                                        type="checkbox"
                                        value="weighted5"
                                        name="weighted5"
                                        checked={weighted5}
                                        onChange={(e) => setWeighted5(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Weighted 5</label>
                                </div>
                            </div>
                        </div>

                        <div className="container mx-auto p-4">
                            <h2 className="mb-4 text-xl font-extrabold dark:text-white text-center">Directed</h2>

                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div>
                                    <input id="directed1"
                                        type="checkbox"
                                        value="directed1"
                                        name="directed1"
                                        checked={directed1}
                                        onChange={(e) => setDirected1(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed 1</label>
                                </div>
                                <div>
                                    <input id="directed2"
                                        type="checkbox"
                                        value="directed2"
                                        name="directed2"
                                        checked={directed2}
                                        onChange={(e) => setDirected2(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed 2</label>
                                </div>
                                <div>
                                    <input id="directed3"
                                        type="checkbox"
                                        value="directed3"
                                        name="directed3"
                                        checked={directed3}
                                        onChange={(e) => setDirected3(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed 3</label>
                                </div>
                                <div>
                                    <input id="directed4"
                                        type="checkbox"
                                        value="directed4"
                                        name="directed4"
                                        checked={directed4}
                                        onChange={(e) => setDirected4(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed 4</label>
                                </div>
                                <div>
                                    <input id="directed5"
                                        type="checkbox"
                                        value="directed5"
                                        name="directed5"
                                        checked={directed5}
                                        onChange={(e) => setDirected5(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Directed 5</label>
                                </div>
                            </div>
                        </div>

                        <div className="container mx-auto p-4">
                            <h2 className="mb-4 text-xl font-extrabold dark:text-white text-center">Undirected</h2>

                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div>
                                    <input id="undirected1"
                                        type="checkbox"
                                        value="undirected1"
                                        name="undirected1"
                                        checked={undirected1}
                                        onChange={(e) => setUndirected1(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 1</label>
                                </div>
                                <div>
                                    <input id="undirected2"
                                        type="checkbox"
                                        value="undirected2"
                                        name="undirected2"
                                        checked={undirected2}
                                        onChange={(e) => setUndirected2(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 2</label>
                                </div>
                                <div>
                                    <input id="undirected3"
                                        type="checkbox"
                                        value="undirected3"
                                        name="undirected3"
                                        checked={undirected3}
                                        onChange={(e) => setUndirected3(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 3</label>
                                </div>
                                <div>
                                    <input id="undirected4"
                                        type="checkbox"
                                        value="undirected4"
                                        name="undirected4"
                                        checked={undirected4}
                                        onChange={(e) => setUndirected4(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 4</label>
                                </div>
                                <div>
                                    <input id="undirected5"
                                        type="checkbox"
                                        value="undirected5"
                                        name="undirected5"
                                        checked={undirected5}
                                        onChange={(e) => setUndirected5(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 5</label>
                                </div>
                                <div>
                                    <input id="undirected6"
                                        type="checkbox"
                                        value="undirected6"
                                        name="undirected6"
                                        checked={undirected6}
                                        onChange={(e) => setUndirected6(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 6</label>
                                </div>
                                <div>
                                    <input id="undirected7"
                                        type="checkbox"
                                        value="undirected7"
                                        name="undirected7"
                                        checked={undirected7}
                                        onChange={(e) => setUndirected7(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 7</label>
                                </div>
                                <div>
                                    <input id="undirected8"
                                        type="checkbox"
                                        value="undirected8"
                                        name="undirected8"
                                        checked={undirected8}
                                        onChange={(e) => setUndirected8(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 8</label>
                                </div>
                                <div>
                                    <input id="undirected9"
                                        type="checkbox"
                                        value="undirected9"
                                        name="undirected9"
                                        checked={undirected9}
                                        onChange={(e) => setUndirected9(e.target.checked)}
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                    />
                                    <label className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Undirected 9</label>
                                </div>
                            </div>
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