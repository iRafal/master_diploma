using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces.Components
{
    public interface INeuron
    {
        float Threshold { get; set; }

        Func<IEnumerable<INeuron>, IEnumerable<Weight>, float> InductedLocalFieldFunction { get; set; }
        Func<float, float> ActivationFunction { get; set; }

        int LayerIndex { get; set; }
        int ElementIndex { get; set; }
        float InductedField { get; set; }
        float Value { get; set; }

        float CalculateOutput(IEnumerable<INeuron> inputLayer, IEnumerable<Weight> weights);
    }
}
