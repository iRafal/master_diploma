using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.Interfaces.Builders;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;

namespace NeuralNetworkSystemBLL.Builders
{
    public class NeuralNetworkBuilder<T> : INeuralNetworkBuilder<T> where T: INeuralNetwork, new()
    {
        private readonly INeuralNetwork _neuralNetwork;

        private INeuronBuilder _neuronBuilderType;
        private INeuron _neuronType;
        private IActivationFunction _activationFunctionType;
        private IInductedLocalFieldFunction _inductedFunctionType;
        private ILayer _layerType;
        private IWeightRepository _weightRepository;

        private int _inputCount;
        private int _hiddenLayersCount;
        private int _hiddenLayerLength;
        private int _outputLength;

        public NeuralNetworkBuilder()
        {
            _neuralNetwork = new T(); 
        }

        public INeuralNetworkBuilder<T> WithLayerType(ILayer layerType)
        {
            _layerType = layerType;
            return this;
        }

        public INeuralNetworkBuilder<T> WithActivationFunctionType(IActivationFunction activationFunctionType)
        {
            _activationFunctionType = activationFunctionType;
            return this;
        }

        public INeuralNetworkBuilder<T> WithInductedFunctionType(IInductedLocalFieldFunction inductedFunctionType)
        {
            _inductedFunctionType = inductedFunctionType;
            return this;
        }

        public INeuralNetworkBuilder<T> WithNeuronBuilerType(INeuronBuilder neuronBuilderType, INeuron neuronType )
        {
            _neuronType = neuronType;
            _neuronBuilderType = neuronBuilderType;
            return this;
        }

        public INeuralNetworkBuilder<T> WithWeightRepositoryType(IWeightRepository repositoryType)
        {
            _weightRepository = repositoryType;
            return this;
        }

        public INeuralNetworkBuilder<T> WithInputCount(int inputCount)
        {
            _inputCount = inputCount;
            return this;
        }

        public INeuralNetworkBuilder<T> WithHiddenLayersCount(int hiddenLayersCount)
        {
            _hiddenLayersCount = hiddenLayersCount;
            return this;
        }

        public INeuralNetworkBuilder<T> WithHiddenLayersLength(int hiddenLayersLength)
        {
            _hiddenLayerLength = hiddenLayersLength;
            return this;
        }

        public INeuralNetworkBuilder<T> WithOutputCount(int outputLength)
        {
            _outputLength = outputLength;
            return this;
        }

        public INeuralNetwork CreateNetwork(bool isNewWeights)
        {
            #region InputLayer

            var layersList = new List<ILayer>();
            var inputLayer = CreateLayer(0, _inputCount, true, false);
            layersList.Add(inputLayer);

            #endregion

            #region NeuronLayers

            for (var i = 1; i <= _hiddenLayersCount; i++)
            {
                var newLayer = CreateLayer(i, _hiddenLayerLength, false, false);
                layersList.Add(newLayer);
            }

            #endregion

            #region OutputLayer

            var outPut = CreateLayer(_hiddenLayersCount +1 , _outputLength, false, true);
            layersList.Add(outPut);
            #endregion

            _neuralNetwork.Layers = layersList;

            if (isNewWeights)
            {
                _neuralNetwork.WeightRepository = _weightRepository.CreateStartUpRepository(_neuralNetwork);
            }
            else
            {
                _neuralNetwork.WeightRepository = _weightRepository.PopulateRepository();
            }

            return _neuralNetwork;
        }

        private ILayer CreateLayer(int layerIndex, int layerLength, bool isInput, bool isOutput)
        {
            var newLayer = Activator.CreateInstance(_layerType.GetType()) as ILayer;
            newLayer.LayerIndex = layerIndex;
            newLayer.IsInputLayer = isInput;
            newLayer.IsOutputLayer = isOutput;

            var neuronsList = new List<INeuron>();

            for (var j = 0; j < layerLength; j++)
            {

                var neuronBuilder = Activator.CreateInstance(_neuronBuilderType.GetType()) as INeuronBuilder;

                var newNeuron = neuronBuilder
                    .WithNeuronType(_neuronType)
                    .WithActivationFunctionType(_activationFunctionType)
                    .WithInductedFunctionType(_inductedFunctionType)
                    .WithElementIndex(j)
                    .WithLayerIndex(layerIndex)
                    .CreateNeuron();

                neuronsList.Add(newNeuron);
            }

            newLayer.Neurons = neuronsList;

            return newLayer;
        }
    }
}