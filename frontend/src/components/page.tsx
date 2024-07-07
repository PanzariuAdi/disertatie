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
    signature: string;
}

interface Errors {
    line: number;
    message: string;
}

interface Dataset {
    id: number;
    fileName: string;
    category: string;
    directed: boolean;
    weighted: boolean;
}

interface CodeRequest {
    datasets: string[];
    code: string;
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
    const [datasets, setDatasets] = useState<Dataset[]>([]);
    const [checkedStates, setCheckedStates] = useState<{ [key: number]: boolean }>({});
    const [filteredDatasets, setFilteredDatasets] = useState<Dataset[]>([]);

    useEffect(() => {
        loadAlgorithmTypes();
    }, []);

    useEffect(() => {
        if (datasets.length > 0) {
          const filtered = datasets.filter(dataset => checkedStates[dataset.id]);
          setFilteredDatasets(filtered);
        }
      }, [datasets, checkedStates]);

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
        const request: CodeRequest = {
            datasets: filteredDatasets.map(dataset => dataset.fileName),
            code: code
        };

        setIsFetching(true);
        axios.post(runCodeUrl, request, {
            params: { algorithmType: selectedAlgorithm },
        })
        .then(res => {
            setResponse(res.data);
            setIsFetching(false);
            setError(false);
        })
        .catch(error => {
            if(axios.isAxiosError(error) && error.response?.status === 400) {
                setErrorMessage(error.response?.data || 'Bad request');
            } else {
                setErrorMessage(error.message);
            }
            setIsFetching(false);
            setError(true);
        })
        .finally(() => {
            setCheckedStates({});
        });
    };

    const handleCheckboxChange = (id: number) => (event: React.ChangeEvent<HTMLInputElement>) => {
        setCheckedStates({
            ...checkedStates,
            [id]: event.target.checked,
        });

    };

    const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>): void => {
        setSelectedAlgorithm(event.target.value);

        const datasetsUrl = backendUrl + "/datasets";

        axios.get(datasetsUrl, {
            params: { algorithmName: event.target.value }
        })
        .then(res => {
            setDatasets(res.data);
            setCheckedStates({});
        })
        .catch(error => {
            console.log(error);
        });
    };

    function getReturnType(algorithmName: string): string {
        const type: string | undefined = algorithmTypes.find(algorithm => algorithm.name === algorithmName)?.signature;

        if (type == undefined) return "void";
        return type;
    }

    return (
            <div className="h-screen w-screen overflow-hidden m-0 p-0">
                <div className="bg-[#21222c] flex h-screen w-screen flex justify-between ">
                    <div className="w-1/2 p-1 overflow-hidden shadow-lg rounded-lg m-1">
                        <Editor
                            value={code}
                            selectedAlgorithm={selectedAlgorithm}
                            returnType={getReturnType(selectedAlgorithm)}
                            onChange={setCode} />
                    </div>

                    <div className="w-1/2 p-1 flex flex-col shadow-lg rounded-lg m-1">
                        <Controls
                            algorithmTypes={algorithmTypes}
                            selectedAlgorithm={selectedAlgorithm}
                            datasets={datasets}
                            checkedStates={checkedStates}
                            onCheckboxChange={handleCheckboxChange}
                            handleRequest={handleRequest}
                            handleSelectChange={handleSelectChange}
                        />

                        <div className="rounded-lg flex-1 bg-dracula-background text-dracula-foreground flex overflow-y-auto box-border pb-5 mb-20">
                            {error ? <ErrorComponent errors={errorMessage} /> : fetching ? <Loader /> : response && <ResultComponent data={response} />}
                        </div>
                    </div>
                </div>
            </div>
    );
}