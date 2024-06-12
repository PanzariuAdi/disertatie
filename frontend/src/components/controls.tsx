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
        <div className="h-1/2 rounded-lg bg-dracula-background flex-1 mb-2 p-2 ">
            <div className="p-4">
                <div className="m-2">
                    <select
                        id="algorithmTypes"
                        value={selectedAlgorithm}
                        onChange={handleSelectChange}
                        className="w-full bg-dracula-currentLine border border-dracula-purple text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
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
                        className="w-full bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option selected>Choose dataset size</option>
                        <option key="verySmall" value="verySmall">very small (5 - 50 nodes)</option>
                        <option key="small" value="small">small (350 - 1900 nodes)</option>
                        <option key="medium" value="mid">mid (3500 - 4000 nodes)</option>
                        <option key="large" value="large">large (4000 - 7000 nodes)</option>
                        <option key="veryLarge" value="veryLarge">very large (11500 - 14000 nodes)</option>
                    </select>
                </div>
            </div>

            <div className="p-4 m-2">
                <button className="w-full text-gray-900 bg-dracula-purple px-4 py-2 rounded-lg shadow hover:bg-dracula-pink transition duration-300 ease-in-out" onClick={handleSubmit}>Submit</button>
            </div>
        </div>
    );
}

export default Controls;
