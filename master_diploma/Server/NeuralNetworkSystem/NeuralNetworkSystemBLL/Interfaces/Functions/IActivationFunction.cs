using System;

namespace NeuralNetworkSystemBLL.Interfaces.Functions
{
    public interface IActivationFunction
    {
        Func<double, double> GetActivationFunction();
        Func<double, double> GetActivationDerivativeFunction();
    }
}
