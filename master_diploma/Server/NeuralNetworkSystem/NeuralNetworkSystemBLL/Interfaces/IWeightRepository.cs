using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces
{
    public interface IWeightRepository
    {
        Weight GetWeight(int inputLayerIndex, int outputLayerIndex,  int inputNeuronIndex, int outPutNeuronIndex);
        Weight UpdateWeight(Weight updatedWeight);
        IWeightRepository PopulateRepository();

        IWeightRepository CreateStartUpRepository(INeuralNetwork neuralNetwork);
    }
}