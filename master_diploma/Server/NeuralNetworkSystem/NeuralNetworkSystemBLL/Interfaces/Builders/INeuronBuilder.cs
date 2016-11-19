using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.Interfaces.Builders
{
    public interface INeuronBuilder
    {
        INeuronBuilder WithNeuronType(INeuron neuron);

        INeuronBuilder WithActivationFunctionType(IActivationFunction activationFunctionTime);

        INeuronBuilder WithInductedFunctionType(IInductedLocalFieldFunction inductedFunction);

        INeuronBuilder WithLayerIndex(int layerIndex);

        INeuronBuilder WithElementIndex(int elementIndex);

        INeuron CreateNeuron();
    }
}
