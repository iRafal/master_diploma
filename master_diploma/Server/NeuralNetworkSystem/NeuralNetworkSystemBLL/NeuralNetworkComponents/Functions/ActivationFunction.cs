using System;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions
{
    public class ActivationFunction : IActivationFunction
    {
        private readonly float _slopeParameter;

        public ActivationFunction()
        {
            _slopeParameter = 0.01f;
        }

        public Func<float, float> GetActivationFunction()
        {
            Func<float, float> activationFunc = f =>
            {
                var result = 1/(1 + Math.Exp(Convert.ToDouble(-1*_slopeParameter*f)));
                return Convert.ToSingle(result);
            };

            return activationFunc;
        }
    }
}
