import React, { useEffect, useState } from "react";
import CodeMirror from "@uiw/react-codemirror";
import axios from "axios";
import { javaLanguage } from "@codemirror/lang-java";
import { dracula } from "thememirror";

interface Algorithm {
    id: number;
    name: string;
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

interface Props {
    data: Data;
}

const ResultComponent: React.FC<Props> = ({ data }) => {
    return (
        <div className="px-4 py-8">
            <h1 className="text-2xl font-bold mb-4">Total cases: {data.totalCases} </h1>

            <h1 className="text-2xl font-bold mb-4">Test Results</h1>
            {
                data.caseResultList.map((result, index) => (
                    <div key={index} className="bg-white rounded-lg shadow-md mb-4 p-6">
                        <h2 className="text-xl font-semibold mb-4">Case Number: {result.caseNumber}</h2>
                        <h2 className="text-xl font-semibold mb-4">Correct: {result.correct.toString()}</h2>
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
                ))
            }
        </div>

    );
}

export function CodeEditor() {
    const backendUrl = "http://localhost:8080";

    const [value, setValue] = useState<string>(initialCode);
    const [response, setResponse] = useState<any>(null);
    const [algorithmTypes, setAlgorithmTypes] = useState<Algorithm[]>([]);
    const [selectedAlgorithm, setSelectedAlgorithm] = useState<any>(null);

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
                console.log("Error" + error);
            });
    };

    const handleClick = () => {
        const runCodeUrl = backendUrl + "/code/run";
        axios.post(runCodeUrl, value, {
             params: { algorithmType: selectedAlgorithm },
             headers: { 'Content-Type': 'text/plain' }
            })
            .then(res => {
                setResponse(res.data);
            })
            .catch(error => {
                console.log("Error" + error);
                setResponse(error);
            });
    };

    const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>): void => {
        setSelectedAlgorithm(event.target.value);
    };

    return (
        <div className="bg-gray-200 flex h-screen flex justify-between bg-black">
            <div className="w-1/2 p-1 overflow-auto">
                <CodeMirror value={initialCode}
                    onChange={(value, viewUpdate) => { setValue(value); }}
                    theme={dracula}
                    extensions={[javaLanguage]}
                />
            </div>

            <div className="w-1/2 p-1">
                <div className="h-screen w-screen flex flex-col">
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

                        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mt-4" onClick={handleClick}>
                            {selectedAlgorithm ? 'Submit' : 'Select an algorithm first'}
                        </button>
                    </div>

                    <div className="rounded-lg flex-1 bg-yellow-300 flex overflow-y-auto">
                        {response && <ResultComponent data={response} />}
                    </div>
                </div>
            </div>
        </div>
    );
}

const initialCode = 
`import org.graph4j.Graph;
import org.graph4j.Edge;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution {
    public Map<Integer, Double> calculate(Graph<?, ?> graph) {
        Map<Integer, Double> Cb = new HashMap<>();

        for (int vertex : graph.vertices()) {
            Cb.put(vertex, 0.0);
        }

        for (int s : graph.vertices()) {
            Stack<Integer> S = new Stack<>();
            Map<Integer, List<Integer>> P = new HashMap<>();
            Map<Integer, Integer> sigma = new HashMap<>();
            Map<Integer, Double> delta = new HashMap<>();
            LinkedList<Integer> Q = new LinkedList<>();

            for (int t : graph.vertices()) {
                P.put(t, new ArrayList<>());
                sigma.put(t, 0);
                delta.put(t, Double.POSITIVE_INFINITY);
            }

            sigma.put(s, 1);
            delta.put(s, 0.0);
            Q.add(s);

            while(!Q.isEmpty()) {
                int v = Q.poll();
                S.push(v);
                for (int w : graph.neighbors(v)) {
                    if (delta.get(w) == Double.POSITIVE_INFINITY) { // w found for the first time ?
                        Q.add(w);
                        delta.put(w, delta.get(v) + 1);
                    }

                    if (delta.get(w) == delta.get(v) + 1) { // shortest path to w via v ?
                        sigma.put(w, sigma.get(w) + sigma.get(v));
                        P.get(w).add(v);
                    }
                }
            }

            Map<Integer, Double> dependency = new HashMap<>();
            for (int v : graph.vertices()) dependency.put(v, 0.0);

            while(!S.isEmpty()) {
                int w = S.pop();

                for (int v : P.get(w)) {
                    double contribution = (sigma.get(v).doubleValue() / sigma.get(w).doubleValue()) * (1 + dependency.get(w));
                    dependency.put(v, dependency.get(v) + contribution);
                }

                if (w != s) {
                    Cb.put(w, Cb.get(w) + dependency.get(w));
                }
            }
        }
        Cb.replaceAll((k, v) -> v / 2); // for undirected graphs only

        int n = graph.numVertices() - 1;
        int normalizationFactor = (n - 1) * (n - 2);
        if (normalizationFactor != 0) {
            Cb.replaceAll((k, v) -> {
                System.out.println(v + "/" + normalizationFactor);
                BigDecimal result = BigDecimal.valueOf(v / normalizationFactor);
                return result.setScale(3, RoundingMode.HALF_UP).doubleValue();
            });
        }

        return Cb;
    }
}
`;
