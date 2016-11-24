using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions
{
    public class InductedLocalFieldFunction : IInductedLocalFieldFunction
    {
        public Func<List<INeuron>, List<Weight>,  double> GetInductedLocalFieldFunction()
        {
            Func<List<INeuron>, List<Weight>, double> function = (neurons, weights) =>
            {
                double field = 0;

                var neuronsCount = neurons.Count();
                var neuronsArray = neurons;
                var weightsArray = weights;

                for (var i = 0; i < neuronsCount; i++)
                {
                    field += neuronsArray[i].Value * weightsArray[i].WeightValue;
                }

                return field;
            };

            return function;
        }
    }
}
