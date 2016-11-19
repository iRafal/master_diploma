using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;

namespace NeuralNetworkSystemConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetwork>();

            var neuralNetwork = neuralNetworkBuilder
                .WithLayerType(new NeuralLayer())
                .WithActivationFunctionType(new ActivationFunction())
                .WithInductedFunctionType(new InductedLocalFieldFunction())
                .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                .WithInputCount(2)
                .WithHiddenLayersCount(1)
                .WithHiddenLayersLength(3)
                .WithOutputCount(2)
                .WithWeightRepositoryType(new WeightRepository())
                .CreateNetwork(true);


            var neuron1 = new Neuron
            {
                LayerIndex = 0,
                ElementIndex = 0,
                Value = 50
            };

            var neuron2 = new Neuron
            {
                LayerIndex = 0,
                ElementIndex = 1,
                Value = 100
            };

            var neuronList = new List<INeuron> {neuron1, neuron2};

            var layer = new NeuralLayer {Neurons = neuronList};

            neuralNetwork.Calculate(layer);
            var outputLayer = neuralNetwork.GetOutputLayer();

            Console.WriteLine($"Result is: 1 inducted field - {outputLayer.Neurons.ToArray()[0].InductedField}, output - {outputLayer.Neurons.ToArray()[0].Value}, 2 inducted field - {outputLayer.Neurons.ToArray()[1].InductedField}, output - {outputLayer.Neurons.ToArray()[1].Value}");


        }
    }
}
