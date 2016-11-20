using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;
using NeuralNetworkSystemBLL.Interfaces.Learning;

namespace NeuralNetworkSystemBLL.Interfaces
{
    public interface INeuralNetwork
    {
        IWeightRepository WeightRepository { get; set; }
        ILearningSamplesRepository LeariningSamplesRepository { get; set; }
        List<ILayer> Layers { get; set; }
        ILearningFunctions LearningFunctions { get; set; }

        ILayer GetOutputLayer();
        ILayer GetInputLayer();

        void Calculate(ILayer inputLauer);
        INeuralNetwork LearnNetwork();

        int MaximumEpochCunt { get; set; }
    } 
}
