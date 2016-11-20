using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL.LearningSamples
{
    public class MemoryLearningSamplesRepository : ILearningSamplesRepository
    {
        public List<ILearningSample> LearningSamples { get; set; }

        private Random _random;

        public MemoryLearningSamplesRepository()
        {
            var learningSamples = new List<ILearningSample>();
            _random = new Random();

            for (var i = 0; i < 200; i++)
            {
                var zeroNeuron = new Neuron
                {
                    ElementIndex = 0,
                    LayerIndex = 0,
                    Value = 1
                };

                var firstInputNeuron = new Neuron
                {
                    ElementIndex = 1,
                    LayerIndex = 0,
                    Value = _random.Next(1, 20)
                };

                var secondInputNeuron = new Neuron
                {
                    ElementIndex = 2,
                    LayerIndex = 0,
                    Value = _random.Next(1, 20)
                };


                var firstOutputNeuron = new Neuron
                {
                    ElementIndex = 0,
                    LayerIndex = 0,
                    Value = 0
                };

                var secondOutputNeuron = new Neuron
                {
                    ElementIndex = 1,
                    LayerIndex = 0,
                    Value = 0
                };

                var sum = firstInputNeuron.Value + secondInputNeuron.Value;

                if (sum >= 10)
                {
                    firstOutputNeuron.Value = 1;
                }
                else
                {
                    secondOutputNeuron.Value = 1;
                }

                var newLearningSample = new LearningSample
                {
                    InputLayer = new NeuralLayer
                    {
                        IsInputLayer = true,
                        Neurons = new List<INeuron>
                        {
                            zeroNeuron,firstInputNeuron,secondInputNeuron
                        }
                    },
                    OutputLayer = new NeuralLayer
                    {
                        IsOutputLayer = true,
                        Neurons = new List<INeuron>()
                        {
                            firstOutputNeuron,
                            secondOutputNeuron
                        }
                    }
                    
                };

                learningSamples.Add(newLearningSample);

            }

            LearningSamples = learningSamples;
        }
    }
}
