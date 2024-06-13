import React, { useEffect, useState } from "react";
import axios from "axios";
import config from "../config";

interface Test {
    id: number;
    algorithmType: {
        id: number;
        name: string;
        signature: string;
    };

    duration: number;
    memory: number;
    dataset: string;
    datasetCategory: string;
    expected: string
}

interface TestProps {
    tests: Test[];
}

const Tests = () => {

    const [testList, setTestList] = useState<Test[]>([]);
    const [filteredTests, setFilteredTests] = useState<Test[]>([]);
    const [datasets, setDatasets] = useState<String[]>([]);
    const [selectedDataset, setSelectedDataset] = useState<string>('all');
    const [selectedAlgorithm, setSelectedAlgorithm] = useState<string>('all');
    const [selectedDatasetCategory, setSelectedDatasetCategory] = useState<string>('all');
    const [algorithmNames, setAlgorithmNames] = useState<String[]>([]);
    const [datasetCategories, setDatasetCategories] = useState<String[]>([]);

    useEffect(() => {
        loadTests();
    }, []);

    useEffect(() => {
        filterTests();

        const datasets = new Set<string>();
        const algorithmNames = new Set<string>();
        const datasetCategories = new Set<string>();

        testList.forEach(test => {
            datasets.add(test.dataset);
            algorithmNames.add(test.algorithmType.name);
            datasetCategories.add(test.datasetCategory);
        });

        setDatasets(Array.from(datasets));
        setAlgorithmNames(Array.from(algorithmNames));
        setDatasetCategories(Array.from(datasetCategories));
    }, [selectedDataset, selectedAlgorithm, selectedDatasetCategory, testList]);

    const loadTests = () => {
        const url = config.BACKEND_URL + "/test-cases/all";
        axios.get(url)
            .then(res => {
                setTestList(res.data);
                setFilteredTests(res.data);
            })
            .catch(error => {
                alert(error);
            });
    };

    const filterTests = () => {
        let filtered = [...testList];

        if (selectedDataset !== 'all') {
            filtered = filtered.filter(test => test.dataset === selectedDataset);
        }

        if (selectedAlgorithm !== 'all') {
            filtered = filtered.filter(test => test.algorithmType.name === selectedAlgorithm);
        }

        if (selectedDatasetCategory != 'all') {
            filtered = filtered.filter(test => test.datasetCategory === selectedDatasetCategory);
        }

        setFilteredTests(filtered);
    };

    const handleDatasetChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedDataset(event.target.value);
    };

    const handleAlgorithmChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedAlgorithm(event.target.value);
    };

    const handleDatasetCategoryChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedDatasetCategory(event.target.value);
    };

    return (
        <div className="flex justify-center p-3 bg-dracula-background w-full h-full">
            <div className="bg-[#21222c] w-4/5 h-4/5 overflow-y-auto mx-auto mt-8 p-4 rounded-lg shadow-md">
                <div className="flex gap-4">
                    <select
                        id="selectAlgorithm"
                        name="selectAlgorithm"
                        onChange={handleAlgorithmChange}
                        className="w-full bg-dracula-background border border-dracula-purple text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    >
                        <option value="all">All algorithms</option>
                        {
                            algorithmNames.map((algorithmName, index) => (
                                <option key={index} value={algorithmName.toString()}>{algorithmName}</option>
                            ))
                        }
                    </select>
                    <select
                        id="selectDatasetCategory"
                        name="selectDatasetCategory"
                        onChange={handleDatasetCategoryChange}
                        className="w-full bg-dracula-background border border-dracula-purple text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    >
                        <option value="all">All dataset categories</option>
                        {
                            datasetCategories.map((datasetCategory, index) => (
                                <option key={index} value={datasetCategory.toString()}>{datasetCategory}</option>
                            ))
                        }
                    </select>

                    <select
                        id="selectDataset"
                        name="selectDataset"
                        onChange={handleDatasetChange}
                        className="w-full bg-dracula-background border border-dracula-purple text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    >
                        <option value="all">All datasets</option>
                        {
                            datasets.map((dataset, index) => (
                                <option key={index} value={dataset.toString()}>{dataset}</option>
                            ))
                        }
                    </select>
                </div>

                <table className="mt-4 w-full table-auto text-sm text-left rtl:text-right text-gray-400">
                    <thead className="text-xs uppercase bg-dracula-currentLine text-dracula-purple">
                        <tr>
                            <th scope="col" className="px-6 py-3">ID</th>
                            <th scope="col" className="px-6 py-3">Algorithm Name</th>
                            <th scope="col" className="px-6 py-3">Algorithm Signature</th>
                            <th scope="col" className="px-6 py-3">Duration</th>
                            <th scope="col" className="px-6 py-3">Memory</th>
                            <th scope="col" className="px-6 py-3">Dataset</th>
                            <th scope="col" className="px-6 py-3">Dataset Category</th>
                        </tr>
                    </thead>
                    <tbody className="border-b bg-dracula-background border-gray-700">
                        {
                            filteredTests.map(test => (
                                <tr key={test.id}>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.id}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.algorithmType.name}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.algorithmType.signature}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.duration}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.memory}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.dataset}</td>
                                    <td scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap text-white">{test.datasetCategory}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
      );

}

export default Tests;