using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;
using NeuralNetworkSystemBLL.Interfaces.Learning;

namespace NeuralNetworkSystemBLL.Interfaces.Builders
{
    public interface INeuralNetworkBuilder<T>
    {
        INeuralNetworkBuilder<T> WithLayerType(ILayer layerType);
        INeuralNetworkBuilder<T> WithActivationFunctionType(IActivationFunction activationFunctionType);
        INeuralNetworkBuilder<T> WithInductedFunctionType(IInductedLocalFieldFunction inductedFunctionType);
        INeuralNetworkBuilder<T> WithLearningFunctionsType(ILearningFunctions learningFunctionsFunctionType);
        INeuralNetworkBuilder<T> WithNeuronBuilerType(INeuronBuilder neuronBuilderType, INeuron neuronType);
        INeuralNetworkBuilder<T> WithWeightRepositoryType(IWeightRepository repositoryType);
        INeuralNetworkBuilder<T> WithLearningSamplesRepositoryType(ILearningSamplesRepository learningSamplesRepositoryrepositoryType);

        INeuralNetworkBuilder<T> WithInputCount(int inputCount);
        INeuralNetworkBuilder<T> WithHiddenLayersCount(int hiddenLayersCount);
        INeuralNetworkBuilder<T> WithHiddenLayersLength(int hiddenLayersLength);
        INeuralNetworkBuilder<T> WithOutputCount(int outputCount);
        INeuralNetworkBuilder<T> WithMaximumEpochCount(int maxEpochCount);

        INeuralNetwork CreateNetwork(bool isNewWeights);
    }
}