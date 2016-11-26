using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces.Components
{
    public interface INeuron
    {
        bool IsThreshold { get; set; }

        Func<List<INeuron>, List<Weight>, double> InductedLocalFieldFunction { get; set; }
        Func<double, double> ActivationFunction { get; set; }

        int LayerIndex { get; set; }
        int ElementIndex { get; set; }

        double InductedField { get; set; }
        double Value { get; set; }

        double Error { get; set; }
        double Gradient { get; set; }

        double CalculateOutput(List<INeuron> inputLayer, List<Weight> weights);
    }
}
