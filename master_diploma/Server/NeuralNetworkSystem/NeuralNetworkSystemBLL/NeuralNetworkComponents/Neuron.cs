using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents
{
    public class Neuron : INeuron
    {
        public int LayerIndex { get; set; }
        public int ElementIndex { get; set; }
        public float Threshold { get; set; } = 1;
        public float InductedField { get; set; }
        public float Value { get; set; }

        public Func<IEnumerable<INeuron>, IEnumerable<Weight>, float> InductedLocalFieldFunction { get; set; }
        public Func<float, float> ActivationFunction { get; set; }

        public float CalculateOutput(IEnumerable<INeuron> inputLayer, IEnumerable<Weight> weights)
        {
            if (inputLayer.Count() != weights.Count())
            {
                throw new Exception("Invalid number of weights in dictionary");
            }

            InductedField = InductedLocalFieldFunction(inputLayer, weights);

            Value = ActivationFunction(InductedField + Threshold);

            return Value;
        }

        public override string ToString()
        {
            return LayerIndex + "-" + ElementIndex;
        }
    }
}