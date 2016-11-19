using System;

namespace NeuralNetworkSystemBLL.Interfaces.Functions
{
    public interface IActivationFunction
    {
        Func<float, float> GetActivationFunction();
    }
}
