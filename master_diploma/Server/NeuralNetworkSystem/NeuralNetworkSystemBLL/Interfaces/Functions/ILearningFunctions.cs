using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces.Functions
{
    public interface ILearningFunctions
    {
        double GetOutPutGradient(INeuron neuron);
        double GetHiddenGradient(INeuron neuron, List<Weight> weights, List<INeuron> nextLyaerNeurons);

        double LearningSpeed { get; set; }

    }
}
