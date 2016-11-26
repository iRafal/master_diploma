using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions
{
    public class SigmoidalLearningFunctions : ILearningFunctions
    {
        public double LearningSpeed { get; set; }
        private readonly double _slopeParameter;

        public SigmoidalLearningFunctions()
        {
            _slopeParameter = ConstantsHelper.SlopeParameter;
            LearningSpeed = ConstantsHelper.LearningSpeed;
        }

        public double GetOutPutGradient(INeuron neuron)
        {
            var result = neuron.Error * _slopeParameter * neuron.Value * (1 - neuron.Value);
            neuron.Gradient = result;

            return result;
        }

        public double GetHiddenGradient(INeuron neuron, List<Weight> weights, List<INeuron> nextLyaerNeurons)
        {
            var sum = (nextLyaerNeurons.Select
            (
                nextLayerNeuron => new
                {
                    nextLayerNeuron,
                    neededWeight = weights
                        .Single(
                            w =>
                                w.InputNeuronIndex == neuron.ElementIndex &&
                                w.OutputNeuronIndex == nextLayerNeuron.ElementIndex)
                }).Select(@t => @t.nextLayerNeuron.Gradient*@t.neededWeight.WeightValue)).Sum();


            var result = _slopeParameter * neuron.Value * (1 - neuron.Value) * sum;

            neuron.Gradient = result;

            return result;
        }
    }
}
