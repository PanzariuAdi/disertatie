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
    correct: number;
    actual: { [key: string]: number };
    duration: number;
    memory: number;
    expected: { [key: string]: number };
    expectedDuration: number;
    expectedMemory: number;
}

const ResultComponent: React.FC<Props> = ({ data }) => {
    const [openIndex, setOpenIndex] = useState<number | null>(null);

    const handleAccordionClick = (index: number) => {
        setOpenIndex((prevIndex) => (prevIndex === index ? null : index));
    };

    return (
        <div className="w-full p-4">
            { data.caseResultList.map((result, index) => (
                <div key={index} className="border border-indigo-500/50 rounded-lg p-4 mb-4 transition-all duration-300 ease-in-out">
                    <div
                        className="flex justify-between items-center cursor-pointer"
                        onClick={() => handleAccordionClick(index)}
                    >
                        <h2 className="text-xl font-semibold">Case Number: {index}</h2>
                        <span>{openIndex === index ? '-' : '+'}</span>
                    </div>
                    {openIndex === index && (
                        <div className="mt-4">
                            <p>Correct: {result.correct.toString()}</p>
                            <div className="grid grid-cols-2 gap-4">
                                <div className="metric">
                                    <h3 className="text-lg font-semibold">Duration:</h3>
                                    <p>{result.duration} ms</p>
                                </div>
                                <div className="metric">
                                    <h3 className="text-lg font-semibold">Memory:</h3>
                                    <p>{result.memory} MB</p>
                                </div>
                            </div>
                            <h3 className="text-lg font-semibold mt-4">Actual Values:</h3>
                            <ul className="list-disc list-inside">
                                {Object.entries(result.actual).map(([key, value]) => (
                                    <li key={key} className="ml-4">
                                        {key}: {value}
                                    </li>
                                ))}
                            </ul>

                            <h3 className="text-lg font-semibold mt-4">Expected Values:</h3>
                            <ul className="list-disc list-inside">
                                {Object.entries(result.expected).map(([key, value]) => (
                                    <li key={key} className="ml-4">
                                        {key}: {value}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
};

export default ResultComponent;