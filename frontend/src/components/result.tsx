import React, { useState } from "react";

interface Props {
    data: Data;
}

interface Data {
    caseResultList: Result[];
    totalCases: number;
}

interface Result {
    caseNumber: number;
    correct: boolean;
    duration: number;
    memory: number;
    actual: { [key: string]: number };
    expected: { [key: string]: number };
    expectedDuration: number;
    expectedMemory: number;
}

const ResultComponent: React.FC<Props> = ({ data }) => {
    const [openIndex, setOpenIndex] = useState<number | null>(null);
    const [openActualIndex, setOpenActualIndex] = useState<number | null>(null);
    const [openExpectedIndex, setOpenExpectedIndex] = useState<number | null>(null);

    const handleAccordionClick = (index: number) => {
        setOpenIndex((prevIndex) => (prevIndex === index ? null : index));
    };

    const handleActualClick = (index: number) => {
        setOpenActualIndex((prevIndex) => (prevIndex === index ? null : index));
    };

    const handleExpectedClick = (index: number) => {
        setOpenExpectedIndex((prevIndex) => (prevIndex === index ? null : index));
    };

    const totalDuration = data.caseResultList.reduce((acc, result) => acc + result.duration, 0);
    const totalMemory = data.caseResultList.reduce((acc, result) => acc + result.memory, 0);
    const totalExpectedDuration = data.caseResultList.reduce((acc, result) => acc + result.expectedDuration, 0);
    const totalExpectedMemory = data.caseResultList.reduce((acc, result) => acc + result.expectedMemory, 0);
    const count = data.caseResultList.length;

    const averageDuration = totalDuration / count;
    const averageMemory = totalMemory / count;
    const averageExpectedDuration = totalExpectedDuration / count;
    const averageExpectedMemory = totalExpectedMemory / count;

    return (
        <div className="w-full p-4">
            {
                <div className="border border-dracula-purple rounded-lg p-4 mb-4 transition-all duration-300 ease-in-out">
                    <h3 className="text-lg font-semibold">Correct/Total: { data.caseResultList.filter(result => result.correct === true).length }/{ data.caseResultList.length } </h3>

                    <div className="grid grid-cols-2 gap-4">
                        <div className="metric">
                            <h3 className="text-lg font-semibold">Actual average duration:</h3>
                            <p>{averageDuration.toFixed(2)}ms</p>
                        </div>

                        <div className="metric">
                            <h3 className="text-lg font-semibold">Actual average memory:  </h3>
                            <p>{averageMemory.toFixed(2)}MB</p>
                        </div>

                        <div className="metric">
                            <h3 className="text-lg font-semibold">Expected average duration:  </h3>
                            <p>{averageExpectedDuration.toFixed(2)}ms</p>
                        </div>

                        <div className="metric">
                            <h3 className="text-lg font-semibold">Expected average memory:</h3>
                            <p>{averageExpectedMemory.toFixed(2)}MB</p>
                        </div>
                    </div>
                </div>
            }

            {
                data.caseResultList.map((result, index) => (
                    <div key={index} className="border border-dracula-purple rounded-lg p-4 mb-4 transition-all duration-300 ease-in-out">
                        <div
                            className="flex justify-between items-center cursor-pointer"
                            onClick={() => handleAccordionClick(index)} >
                            <h2 className="text-xl font-semibold">Case Number: {index}</h2>
                            <span>{openIndex === index ? '-' : '+'}</span>
                        </div>

                        {
                            openIndex === index && (
                                <div className="mt-4">
                                    <h3 className="text-lg font-semibold">Correct: </h3> <p>{ result.correct.toString() }</p>
                                    <div className="grid grid-cols-2 gap-4">
                                        <div className="metric">
                                            <h3 className="text-lg font-semibold">Actual duration:</h3>
                                            <p>{result.duration}ms</p>
                                        </div>
                                        <div className="metric">
                                            <h3 className="text-lg font-semibold">Actual memory:</h3>
                                            <p>{result.memory} MB</p>
                                        </div>

                                        <div className="metric">
                                            <h3 className="text-lg font-semibold">Expected duration:</h3>
                                            <p>{result.expectedDuration}ms</p>
                                        </div>
                                        <div className="metric">
                                            <h3 className="text-lg font-semibold">Expected memory:</h3>
                                            <p>{result.expectedMemory} MB</p>
                                        </div>
                                    </div>

                                    <div className="flex">
                                        <div className="w-1/2">
                                            <div className="cursor-pointer mt-4" onClick={() => handleActualClick(index)}>
                                                <h3 className="text-lg font-semibold mt-4"> Actual Values <span>{openActualIndex === index ? '-' : '+'}</span> </h3>
                                                {
                                                    openActualIndex === index && (
                                                        <ul className="list-disc list-inside">
                                                            {
                                                                Object.entries(result.actual).map(([key, value]) => (
                                                                    <li key={key} className="ml-4">
                                                                        {key}: {value}
                                                                    </li>
                                                                ))
                                                            }
                                                        </ul>
                                                    )
                                                }
                                            </div>
                                        </div>

                                        <div className="w-1/2">
                                            <div className="cursor-pointer mt-4" onClick={() => handleExpectedClick(index)}>
                                                <h3 className="text-lg font-semibold mt-4"> Expected Values <span>{openExpectedIndex === index ? '-' : '+'}</span> </h3>
                                                {
                                                    openExpectedIndex === index && (

                                                        <ul className="list-disc list-inside">
                                                            {
                                                                Object.entries(result.expected).map(([key, value]) => (
                                                                    <li key={key} className="ml-4">
                                                                        {key}: {value}
                                                                    </li>
                                                                ))
                                                            }
                                                        </ul>
                                                    )
                                                }
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            )
                        }
                    </div>
                ))
            }
        </div>
    );
};

export default ResultComponent;