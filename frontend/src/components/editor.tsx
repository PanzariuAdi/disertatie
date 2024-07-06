import React, { useEffect } from "react";
import CodeMirror from "@uiw/react-codemirror";
import { javaLanguage } from "@codemirror/lang-java";
import { dracula } from "thememirror";

interface EditorProps {
    value: string
    selectedAlgorithm: string;
    returnType: string 
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


const Editor: React.FC<EditorProps> = ({value, selectedAlgorithm, returnType, onChange }) => {
    useEffect(() => {
        onChange(getBaseCode(returnType));
    }, [selectedAlgorithm])

    return (
        <div style={{ height: '95vh'}}>
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
