import React from "react";

interface Algorithm {
    id: number;
    name: string;
}

interface ControlsProps {
    algorithmTypes: Algorithm[];
    selectedAlgorithm: string;
    handleRequest: (isRun: boolean) => void;
    handleSelectChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

const Controls: React.FC<ControlsProps> = ({ algorithmTypes, selectedAlgorithm, handleRequest, handleSelectChange }) => {
    const handleSubmitClick = () => {
        handleRequest(false);
    };

    const handleRunClick = () => {
        handleRequest(true);
    };

    return (
        <div className="h-1/2 rounded-lg bg-blue-300 flex-1 mb-2 p-2">
            <select
                id="algorithmTypes"
                value={selectedAlgorithm}
                onChange={handleSelectChange}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                <option selected>Choose an algorithm type</option>
                {
                    algorithmTypes.map(algorithm => (
                        <option key={algorithm.id} value={algorithm.name}>
                            {algorithm.name}
                        </option>
                    ))
                }
            </select>

            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mt-4" onClick={handleSubmitClick}>
                Submit
            </button>

            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mt-4" onClick={handleRunClick}>
                Run
            </button>
        </div>
    );
}

export default Controls;
