using System;
using NeuralNetworkSystemBLL.Interfaces.Builders;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.Builders
{
    public class NeuronBuilder : INeuronBuilder
    { 
        private INeuron _neuron;
        private IActivationFunction _activationFunctionType;
        private IInductedLocalFieldFunction _inductedFunctionType;
        private int _layerIndex;
        private int _elementIndex;

        public INeuronBuilder WithNeuronType(INeuron neuron)
        {
            _neuron = neuron;
            return this;
        }

        public INeuronBuilder WithActivationFunctionType(IActivationFunction activationFunctionTime)
        {
            _activationFunctionType = activationFunctionTime;
            return this;
        }

        public INeuronBuilder WithInductedFunctionType(IInductedLocalFieldFunction inductedFunction)
        {
            _inductedFunctionType = inductedFunction;
            return this;
        }

        public INeuronBuilder WithLayerIndex(int layerIndex)
        {
            _layerIndex = layerIndex;
            return this;
        }

        public INeuronBuilder WithElementIndex(int elementIndex)
        {
            _elementIndex = elementIndex;
            return this;
        }

        public INeuron CreateNeuron()
        {
            var newNeuron = Activator.CreateInstance(_neuron.GetType()) as INeuron;
            newNeuron.ActivationFunction = _activationFunctionType.GetActivationFunction();

            newNeuron.InductedLocalFieldFunction = _inductedFunctionType.GetInductedLocalFieldFunction();

            newNeuron.ElementIndex = _elementIndex;
            newNeuron.LayerIndex = _layerIndex;

            return newNeuron;
        }
    }
}
