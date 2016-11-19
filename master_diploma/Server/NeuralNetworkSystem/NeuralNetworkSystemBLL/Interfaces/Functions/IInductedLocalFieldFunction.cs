using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces.Functions
{
    public interface IInductedLocalFieldFunction
    {
        Func<IEnumerable<INeuron>,IEnumerable<Weight>,  float> GetInductedLocalFieldFunction();
    }
}
