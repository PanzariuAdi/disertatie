import { RotatingLines } from "react-loader-spinner";

export default function Loader() {
    return (
        <div className="w-full h-full flex items-center justify-center">
            <RotatingLines
                // strokeColor="#21222c"
                strokeColor="purple"
                strokeWidth="5"
                animationDuration="0.75"
                width="96"
                visible={true}
            />
        </div>
    )
}