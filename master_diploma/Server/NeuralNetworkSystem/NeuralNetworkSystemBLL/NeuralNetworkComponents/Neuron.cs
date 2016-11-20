using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents
{
    public class Neuron : INeuron
    {
        public bool IsThreshold { get; set; }

        public int LayerIndex { get; set; }
        public int ElementIndex { get; set; }
        
        public double InductedField { get; set; }
        public double Value { get; set; }
        public double Error { get; set; }
        public double Gradient { get; set; }

        public Func<List<INeuron>, List<Weight>, double> InductedLocalFieldFunction { get; set; }
        public Func<double, double> ActivationFunction { get; set; }

        public double CalculateOutput(List<INeuron> inputLayer, List<Weight> weights)
        {
            if (inputLayer.Count() != weights.Count())
            {
                throw new Exception("Invalid number of weights in dictionary");
            }

            InductedField = InductedLocalFieldFunction(inputLayer, weights);

            Value = ActivationFunction(InductedField);

            return Value;
        }

        public override string ToString()
        {
            return LayerIndex + "-" + ElementIndex;
        }
    }
}