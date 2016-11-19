using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions
{
    public class InductedLocalFieldFunction : IInductedLocalFieldFunction
    {
        public Func<IEnumerable<INeuron>, IEnumerable<Weight>,  float> GetInductedLocalFieldFunction()
        {
            Func<IEnumerable<INeuron>, IEnumerable<Weight>, float> function = (neurons, weights) =>
            {
                float field = 0;

                var neuronsCount = neurons.Count();
                var neuronsArray = neurons.ToArray();
                var weightsArray = weights.ToArray();

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
