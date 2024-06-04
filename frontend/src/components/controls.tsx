import React from "react";

interface Algorithm {
    id: number;
    name: string;
}

interface ControlsProps {
    algorithmTypes: Algorithm[];
    selectedAlgorithm: string;
    selectedDataset: string;
    handleRequest: () => void;
    handleSelectChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    handleSelectDatasetChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

const Controls: React.FC<ControlsProps> = ({ algorithmTypes, selectedAlgorithm, selectedDataset, handleRequest, handleSelectChange, handleSelectDatasetChange }) => {
    const handleSubmit = () => {
        handleRequest();
    };

    return (
        <div className="h-1/2 rounded-lg bg-blue-300 flex-1 mb-2 p-2 ">
            <div className="p-4">
                <div className="m-2">
                    <select
                        id="algorithmTypes"
                        value={selectedAlgorithm}
                        onChange={handleSelectChange}
                        className="w-full bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option selected>Choose algorithm type</option>
                        {
                            algorithmTypes.map(algorithm => (
                                <option key={algorithm.id} value={algorithm.name}>
                                    {algorithm.name}
                                </option>
                            ))
                        }
                    </select>
                </div>

                <div className="m-2">
                    <select
                        id="datasetCategory"
                        value={selectedDataset}
                        onChange={handleSelectDatasetChange}
                        className="w-full bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option selected>Choose dataset size</option>
                        <option key="verySmall" value="verySmall">very small</option>
                        <option key="small" value="small">small</option>
                        <option key="medium" value="mid">mid</option>
                        <option key="large" value="large">large</option>
                        <option key="veryLarge" value="veryLarge">very large</option>
                    </select>
                </div>
            </div>

            <div className="p-4 m-2">
                <button className="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mt-4" onClick={handleSubmit}>Submit</button>
            </div>
        </div>
    );
}

export default Controls;
