using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;

namespace NeuralNetworkSystemConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetwork>();
            var neuralNetwork = neuralNetworkBuilder
                .WithLayerType(new NeuralLayer())
                .WithActivationFunctionType(new SigmoidalActivationFunction())
                .WithInductedFunctionType(new InductedLocalFieldFunction())
                .WithLearningFunctionsType(new SigmoidalLearningFunctions())
                .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                .WithInputCount(21)
                .WithHiddenLayersCount(2)
                .WithHiddenLayersLength(42)
                .WithOutputCount(2)
                .WithMaximumEpochCount(0)
                .WithWeightRepositoryType(new WeightRepository())
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository())
                .CreateNetwork(true)
                .LearnNetwork();





            //var neuralNetwork = neuralNetworkBuilder
            //    .WithLayerType(new NeuralLayer())
            //    .WithActivationFunctionType(new SigmoidalActivationFunction())
            //    .WithInductedFunctionType(new InductedLocalFieldFunction())
            //    .WithLearningFunctionsType(new SigmoidalLearningFunctions())
            //    .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
            //    .WithInputCount(1)
            //    .WithHiddenLayersCount(1)
            //    .WithHiddenLayersLength(4)
            //    .WithOutputCount(2)
            //    .WithMaximumEpochCount(0)
            //    .WithWeightRepositoryType(new WeightRepository())
            //    .WithLearningSamplesRepositoryType(new MemoryLearningSamplesRepository())
            //    .CreateNetwork(true)
            //    .LearnNetwork();

            //while (true)
            //{
            //    Console.WriteLine("\nEnter firstNum: ");
            //    var firstUm = int.Parse(Console.ReadLine());

            //   // Console.WriteLine("\nEnter SecondNum: ");
            //   // var secondNUm = int.Parse(Console.ReadLine());

            //    var layer = new NeuralLayer();

            //    var neuronsList = new List<INeuron>();

            //    var zeroNeuron = new Neuron
            //    {
            //        LayerIndex = 0,
            //        ElementIndex = 0,
            //        Value = 1
            //    };

            //    var neuron1 = new Neuron
            //    {
            //        LayerIndex = 0,
            //        ElementIndex = 1,
            //        Value = firstUm
            //    };

            //    //var neuron2 = new Neuron
            //    //{
            //    //    LayerIndex = 0,
            //    //    ElementIndex = 2,
            //    //    Value = secondNUm
            //    //};

            //    neuronsList.Add(zeroNeuron);
            //    neuronsList.Add(neuron1);
            //    //neuronsList.Add(neuron2);

            //    layer.Neurons = neuronsList;

            //    layer = neuralNetwork.Normalize(layer) as NeuralLayer;
            //    neuralNetwork.Calculate(layer);

            //    var output = neuralNetwork.GetOutputLayer().Neurons.Select(n => n.Value).ToList();
            //    Console.WriteLine($"Output: {output[0]} - {output[1]}\n");
            //    string msg = string.Empty;

            //    if (output[0] > output[1])
            //    {
            //        msg = "More that 10\n";
            //    }
            //    else
            //    {
            //        msg = "less then 10\n";
            //    }

            //    Console.WriteLine(msg);
            //}
        }
    }
}
