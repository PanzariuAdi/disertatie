import React from "react";

interface Algorithm {
    id: number;
    name: string;
}

interface Dataset {
    id: number;
    fileName: string;
    category: string;
    directed: boolean;
    weighted: boolean;
}

interface ControlsProps {
    algorithmTypes: Algorithm[];
    selectedAlgorithm: string;
    datasets: Dataset[],
    checkedStates: { [key: number]: boolean };
    handleRequest: () => void;
    handleSelectChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    onCheckboxChange: (id: number) => (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const Controls: React.FC<ControlsProps> = ({ algorithmTypes, selectedAlgorithm, datasets, checkedStates, onCheckboxChange, handleRequest, handleSelectChange }) => {

    const handleSubmit = () => {
        handleRequest();
    };

    const categorizedDatasets = datasets.reduce((acc, dataset) => {
        if (!acc[dataset.category]) {
            acc[dataset.category] = [];
        }
        acc[dataset.category].push(dataset);
        return acc;
    }, {} as { [key: string]: Dataset[] });

    return (
        <div className="h-1/2 rounded-lg bg-dracula-background flex-1 mb-2 p-2 overflow-auto">
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

                <div className="container mx-auto p-2">
                    {Object.keys(categorizedDatasets).map((category) => (
                        <div key={category} className="category border border-dracula-purple rounded-lg p-4 mb-4">
                            <h2 className="mb-4 text-xl font-extrabold dark:text-white text-center">{category}</h2>
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                {categorizedDatasets[category].map((dataset) => (
                                    <div key={dataset.id}>
                                        <input
                                            id={`${category}${dataset.id}`}
                                            type="checkbox"
                                            value={`${category}${dataset.id}`}
                                            name={`${category}${dataset.id}`}
                                            checked={checkedStates[dataset.id] || false}
                                            onChange={onCheckboxChange(dataset.id)}
                                            className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                        />
                                        <label htmlFor={`${category}${dataset.id}`} className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">
                                            {dataset.fileName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>

            </div>

            <div className="p-4 m-2">
                <button className="w-full text-gray-900 bg-dracula-purple px-4 py-2 rounded-lg shadow hover:bg-dracula-pink transition duration-300 ease-in-out" onClick={handleSubmit}>Submit</button>
            </div>
        </div>
    );
}

export default Controls;
