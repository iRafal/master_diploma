using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL.Interfaces
{
    public interface IWeightRepository
    {
        Weight GetWeight(int inputLayerIndex, int outputLayerIndex,  int inputNeuronIndex, int outPutNeuronIndex);
        void UpdateWeights();
        IWeightRepository PopulateRepository();

        IWeightRepository CreateStartUpRepository(INeuralNetwork neuralNetwork);

        void ChangeWeightsAfterIteration();
    }
}