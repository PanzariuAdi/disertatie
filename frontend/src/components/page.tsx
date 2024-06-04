import React, { useEffect, useState } from "react";
import axios from "axios";
import ResultComponent from "./result";
import Editor from "./editor";
import Controls from "./controls";
import Loader from "./loader";
import ErrorComponent from "./errors";

interface Algorithm {
    id: number;
    name: string;
}

interface Errors {
    line: number;
    message: string;
}

export function Page() {
    const backendUrl = "http://localhost:8080";

    const [code, setCode] = useState<string>('');
    const [response, setResponse] = useState<any>();
    const [fetching, setIsFetching] = useState<boolean>(false);
    const [error, setError] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<Errors[]>([]);
    const [algorithmTypes, setAlgorithmTypes] = useState<Algorithm[]>([]);
    const [selectedAlgorithm, setSelectedAlgorithm] = useState<string>('');
    const [selectedDataset, setSelectedDataset] = useState<string>('');

    useEffect(() => {
        loadAlgorithmTypes();
    }, []);

    const loadAlgorithmTypes = () => {
        const algorithmTypeUrl = backendUrl + "/algorithms/all";
        axios.get(algorithmTypeUrl)
            .then(res => {
                setAlgorithmTypes(res.data);
            })
            .catch(error => {
                console.log("Error " + error);
            });
    };

    const handleRequest = () => {
        if (selectedAlgorithm === '') return;

        const runCodeUrl = backendUrl + "/code/run";
        setIsFetching(true);
        axios.post(runCodeUrl, code, {
            params: { algorithmType: selectedAlgorithm, dataset: selectedDataset },
            headers: { 'Content-Type': 'text/plain' }
        })
        .then(res => {
            setResponse(res.data);
            setIsFetching(false);
            setError(false);
        })
        .catch(error => {
            if(axios.isAxiosError(error) && error.response?.status === 400) {
                const errorBody = error.response?.data;
                setErrorMessage(error.response?.data || 'Bad request');
            } else {
                setErrorMessage(error.message);
            }
            setIsFetching(false);
            setError(true);
        });
    };

    const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>): void => {
        setSelectedAlgorithm(event.target.value);
    };

    const handleSelectDatasetChange = (event: React.ChangeEvent<HTMLSelectElement>): void => {
        setSelectedDataset(event.target.value);
    };

    return (
        <div className="bg-gray-200 flex h-screen w-screen flex justify-between ">
            <div className="w-1/2 p-1 overflow-auto shadow-lg rounded-lg m-1">
                <Editor
                    value={code}
                    selectedAlgorithm={selectedAlgorithm}
                    onChange={setCode} />
            </div>

            <div className="w-1/2 p-1 flex flex-col shadow-lg rounded-lg m-1">
                <Controls
                    algorithmTypes={algorithmTypes}
                    selectedAlgorithm={selectedAlgorithm}
                    selectedDataset={selectedDataset}
                    handleRequest={handleRequest}
                    handleSelectChange={handleSelectChange}
                    handleSelectDatasetChange={handleSelectDatasetChange}
                />

                <div className="rounded-lg flex-1 bg-yellow-300 flex overflow-y-auto">
                    {error ? <ErrorComponent errors={errorMessage} /> : fetching ? <Loader /> : response && <ResultComponent data={response} />}
                </div>
            </div>
        </div>
    );
}
