import React, { useEffect } from "react";
import CodeMirror from "@uiw/react-codemirror";
import { javaLanguage } from "@codemirror/lang-java";
import { dracula } from "thememirror";

interface EditorProps {
    value: string
    selectedAlgorithm: string;
    onChange: (value: string) => void;
}

function getBaseCode(returnType: string): string {
    return `import org.graph4j.*;
import java.util.*;
import java.math.*;

public class Solution {
    public ${returnType} calculate(Graph<?, ?> graph) {
        // TODO: write code here
    }
}
`;
}


const Editor: React.FC<EditorProps> = ({value, selectedAlgorithm, onChange }) => {
    const algorithmReturnTypesMap: Map<string, string> = new Map([
        ["betweenness_centrality", "Map<Integer, Double>"],
        ["katz_centrality", "Map<Integer, Double>"],
        ["another_type", "int"],
    ]);

    useEffect(() => {
        const returnType = algorithmReturnTypesMap.get(selectedAlgorithm) || 'void';
        onChange(getBaseCode(returnType));
    }, [selectedAlgorithm])

    return (
        <div style={{ height: '100vh'}}>
            <CodeMirror 
                value={value}
                theme={dracula}
                onChange={(value, _) => { onChange(value) } }
                extensions={[javaLanguage]}
                height="100%"
                style={{ height: '100%' }}
            />
        </div>
    );
}

export default Editor;
