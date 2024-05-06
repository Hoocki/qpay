import React from 'react';
import "../../../../../../../common/styles/container.css";
import "../styles.css";
import "../../balance/styles.css";
import {Pie} from "react-chartjs-2";
import {getGraphData} from "./utils";
import {IOutcome} from "../../../../../../../types/transactions";


const PieChart: React.FC<IOutcome> = ({total, transactionGroups}) => {

    if (total <= 0) {
        return <></>
    }

    return (
        <Pie options={{
            responsive: true,
            plugins: {
                legend: {
                    display: true,
                    position: 'left',
                    align: "center",
                    labels: {
                        usePointStyle: true,
                        font: {
                            size: 15,
                        }
                    }
                }
            }
        }} data={getGraphData(transactionGroups)} className="pie-chart"/>
    );
};

export default PieChart;