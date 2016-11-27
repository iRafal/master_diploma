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
        void NormalizeSamples();

        INeuralNetwork LearnNetwork();

        int MaximumEpochCount { get; set; }
        double NormalizationMinValue { get; set; }
        double NormalizationMaxValue { get; set; }
        double ErrorThreshold { get; set; }
        double ErrorsCountThreshold { get; set; }


        ILayer Normalize(ILayer layer);
    } 
}
