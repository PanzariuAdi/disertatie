import React from "react";

interface Props {
    errors: Errors[];
}

interface Errors {
    line: number;
    message: string;
}

const ErrorComponent: React.FC<Props> = ({ errors }) => {
    return (
        <div className="w-full p-4">
            {
                errors.map((error, index) => (
                    <div key={index} className="border border-dracula-pink rounded-lg p-4 mb-4 overflow-auto">
                        <div className="">
                            <pre>
                                Error {index}: {error.message} on line {error.line}
                            </pre>
                        </div>
                    </div>
                )) 
            }

        </div>
    );
}

export default ErrorComponent;