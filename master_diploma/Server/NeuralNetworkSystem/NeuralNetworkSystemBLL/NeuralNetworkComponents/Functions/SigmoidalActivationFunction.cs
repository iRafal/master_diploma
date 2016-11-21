using System;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions
{
    public class SigmoidalActivationFunction : IActivationFunction
    {
        private readonly double _slopeParameter;

        public SigmoidalActivationFunction()
        {
            _slopeParameter = ConstantsHelper.SlopeParameter;
        }

        public Func<double, double> GetActivationFunction()
        {
            Func<double, double> activationFunc = f =>
            {
                var result = 1/(1 + Math.Exp(Convert.ToDouble(-1*_slopeParameter*f)));
                if (result == 1)
                {
                    result = 0.9999;
                }
                else if (result == 0)
                {
                    result = 0.000001;
                }
                return result;
            };

            return activationFunc;
        }

        public Func<double, double> GetActivationDerivativeFunction()
        {
            throw new NotImplementedException();
        }
    }
}
